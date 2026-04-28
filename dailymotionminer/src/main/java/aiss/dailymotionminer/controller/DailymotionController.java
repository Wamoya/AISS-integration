package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.model.dailymotion.Channel;
import aiss.dailymotionminer.service.ChannelService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dailymotionminer")
public class DailymotionController {

    @Autowired
    ChannelService channelService;

    @GetMapping("/{channelName}")
    public Channel getVideosFromChannel(@PathVariable String channelName,
                                        @RequestParam(name = "maxVideos", defaultValue = "10") @Min(1) @Max(1000) Integer maxVideos, //The Dailymotion API allows for a maximum of 10 pages with a maximum 100 videos per page, so our API will be able to request at most 1000 videos from a channel.
                                        @RequestParam(name = "maxComments", defaultValue = "2") @Min(0) Integer maxComments) {
        return channelService.getChannelFull(channelName, maxVideos, maxComments);
    }

}
