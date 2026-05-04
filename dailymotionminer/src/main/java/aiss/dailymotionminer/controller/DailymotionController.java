package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.etl.Transformer;
import aiss.dailymotionminer.model.dailymotion.Channel;
import aiss.dailymotionminer.model.videominer.VM_Channel;
import aiss.dailymotionminer.service.ChannelService;
import aiss.dailymotionminer.service.VideoMinerService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dailymotionminer/v1")
public class DailymotionController {

    @Autowired
    ChannelService channelService;
    @Autowired
    VideoMinerService videoMinerService;

    @GetMapping("/{channelName}")
    public VM_Channel getChannel(@PathVariable String channelName,
                                 @RequestParam(name = "maxVideos", defaultValue = "10") @Min(0) @Max(1000) Integer maxVideos, //The Dailymotion API allows for a maximum of 10 pages with a maximum 100 videos per page, so our API will be able to request at most 1000 videos from a channel.
                                 @RequestParam(name = "maxComments", defaultValue = "2") @Min(0) Integer maxComments) {
        Channel channel = channelService.getChannelWithVideos(channelName, maxVideos, maxComments);
        return Transformer.toVMChannel(channel);
    }

    @PostMapping("/{channelName}")
    public VM_Channel postChannel(@PathVariable String channelName,
                                  @RequestParam(name = "maxVideos", defaultValue = "10") @Min(0) @Max(1000) Integer maxVideos,
                                  @RequestParam(name = "maxComments", defaultValue = "2") @Min(0) Integer maxComments) {
        Channel channel = channelService.getChannelWithVideos(channelName, maxVideos, maxComments);
        VM_Channel vm_channel = Transformer.toVMChannel(channel);
        return videoMinerService.postChannel(vm_channel);
    }

}
