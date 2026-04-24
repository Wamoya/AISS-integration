package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.service.ChannelService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/peertubeminer")
public class PeertubeController {

    @Autowired
    ChannelService channelService;

    @GetMapping("/{channelName}")
    public Channel getVideosFromChannel(@PathVariable String channelName,
                                        @RequestParam(name = "maxVideos", defaultValue = "10") @Min(1) @Max(100) Integer maxVideos, // TODO. PeerTube's value for "count" must be in [1..100], but we should expect to get bigger numbers. Instead of rejecting those requests, we should implement some sort of paging logic in VideoService to get the desired number of videos
                                        @RequestParam(name = "maxComments", defaultValue = "2") Integer maxComments) {
        return channelService.getChannelFull(channelName, maxVideos, maxComments);
    }

    @PostMapping("/{channelName}")
    public void postVideosFromChannel(@PathVariable String channelName, @RequestParam() @Min(1) @Max(100) Integer maxVideos, @RequestParam Integer maxComments) {
        Channel channel = channelService.getChannelFull(channelName, maxVideos, maxComments);

        // TODO. Make the necessary transformations from PT models to VM models

        // TODO. Send the VM objects to VM Miner
    }
}
