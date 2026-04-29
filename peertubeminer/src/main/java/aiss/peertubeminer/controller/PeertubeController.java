package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.peertube.*;
import aiss.peertubeminer.model.videominer.*;
import aiss.peertubeminer.service.ChannelService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/peertubeminer")
public class PeertubeController {

    @Autowired
    ChannelService channelService;

    @GetMapping("/{channelName}")
    public Channel getVideosFromChannel(@PathVariable String channelName,
                                        @RequestParam(name = "maxVideos", defaultValue = "10") @Min(0) @Max(100) Integer maxVideos, // TODO. PeerTube's value for "count" must be in [1..100], but we should expect to get bigger numbers. Instead of rejecting those requests, we should implement some sort of paging logic in VideoService to get the desired number of videos
                                        @RequestParam(name = "maxComments", defaultValue = "2") @Min(0) Integer maxComments) {
        return channelService.getChannelFull(channelName, maxVideos, maxComments);
    }

    @PostMapping("/{channelName}")
    public VM_Channel postVideosFromChannel(@PathVariable String channelName,
                                            @RequestParam(name = "maxVideos", defaultValue = "10") @Min(1) @Max(100) Integer maxVideos, // TODO. PeerTube's value for "count" must be in [1..100], but we should expect to get bigger numbers. Instead of rejecting those requests, we should implement some sort of paging logic in VideoService to get the desired number of videos
                                            @RequestParam(name = "maxComments", defaultValue = "2") Integer maxComments) {
        Channel channel = channelService.getChannelFull(channelName, maxVideos, maxComments);

        // TODO. Delegate the translation responsibility to a different file
        List<VM_Video> vm_videos = new ArrayList<>(); // We need to translate the list of videos first because VM_Channel requires it as a parameter
        for (Video v : channel.getVideos()) {
            // For each video, we need to translate comments, captions and user first for the same reason as before
            List<VM_Comment> vm_comments = new ArrayList<>(); // Translate comments
            for (Comment c : v.getComments()) {
                vm_comments.add(
                        new VM_Comment(
                                UUID.randomUUID().toString(), // TODO. VideoMiner should be the one assigning IDs to every object
                                c.getText(),
                                c.getCreatedAt()
                        )
                );
            }

            List<VM_Caption> vm_captions = new ArrayList<>(); // Translate captions
            for (Caption c : v.getCaptions()) {
                vm_captions.add(
                        new VM_Caption(
                                UUID.randomUUID().toString(),
                                c.getLanguage().getLabel(),
                                c.getFileUrl()
                        )
                );
            }

            VM_User vm_user = new VM_User( // Translate user
                    UUID.randomUUID().toString(),
                    v.getAccount().getName(),
                    v.getAccount().getUrl(),
                    v.getAccount().getAvatars().stream().findFirst().isPresent()
                            ? v.getAccount().getAvatars().stream().findFirst().get().getFileUrl()
                            : ""
            );

            vm_videos.add( // Append video to the accumulator
                    new VM_Video(
                        UUID.randomUUID().toString(),
                        v.getName(),
                        v.getDescription(),
                        v.getPublishedAt(),
                        vm_user,
                        vm_comments,
                        vm_captions
                )
            );
        }

        VM_Channel vm_channel = new VM_Channel( // Finally, create the channel
                UUID.randomUUID().toString(),
                channel.getName(),
                channel.getDescription(),
                channel.getCreatedAt(),
                vm_videos
        );

        // TODO. Send the VM objects to VM Miner
        return vm_channel;
    }
}
