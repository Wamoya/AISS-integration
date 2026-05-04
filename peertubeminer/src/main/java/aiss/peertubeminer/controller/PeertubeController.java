package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.peertube.*;
import aiss.peertubeminer.model.videominer.*;
import aiss.peertubeminer.service.ChannelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Tag(name = "PeerTube", description = "PeerTune management")
@RestController
@RequestMapping("/api/peertubeminer")
public class PeertubeController {

    @Autowired
    ChannelService channelService;

    @Operation(
            summary = "Retrieve a channel by channel name",
            description = "Get all video objects associated to a channel from PeerTube by its channel name",
            tags = {"GET"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = VM_Channel.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    })
    @GetMapping("/{channelName}")
    public Channel getVideosFromChannel(@PathVariable String channelName,
                                        @RequestParam(name = "maxVideos", defaultValue = "10") @Min(0) Integer maxVideos,
                                        @RequestParam(name = "maxComments", defaultValue = "2") @Min(0) Integer maxComments) {
        return channelService.getChannelFull(channelName, maxVideos, maxComments);
    }

    @Operation(
            summary = "Insert a channel in Videominer",
            description = "Insert a channel from PeerTube into Videominer",
            tags = {"POST"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = VM_Channel.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{channelName}")
    public VM_Channel postVideosFromChannel(@PathVariable String channelName,
                                            @RequestParam(name = "maxVideos", defaultValue = "10") @Min(0) Integer maxVideos,
                                            @RequestParam(name = "maxComments", defaultValue = "2") @Min(0) Integer maxComments) {
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
