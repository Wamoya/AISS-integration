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
                                        @RequestParam(name = "maxVideos", defaultValue = "10") @Min(1) @Max(100) Integer maxVideos, // TODO. Dailymotion's value for "limit" must be in [1..100] videos per page, but we should expect to get bigger numbers. Instead of rejecting those requests, we should implement some sort of paging logic in VideoService to get the desired number of videos
                                        @RequestParam(name = "maxComments", defaultValue = "2") Integer maxComments) {
        return channelService.getChannelFull(channelName, maxVideos, maxComments);
    }

}
