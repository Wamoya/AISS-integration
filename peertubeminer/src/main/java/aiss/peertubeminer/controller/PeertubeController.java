package aiss.peertubeminer.controller;

import aiss.peertubeminer.etl.Transformer;
import aiss.peertubeminer.model.peertube.*;
import aiss.peertubeminer.model.videominer.*;
import aiss.peertubeminer.service.ChannelService;
import aiss.peertubeminer.service.VideoMinerService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/peertubeminer/v1")
public class PeertubeController {

    @Autowired
    ChannelService channelService;
    @Autowired
    VideoMinerService videoMinerService;

    @GetMapping("/{channelName}")
    public VM_Channel getChannel(@PathVariable String channelName,
                                 @RequestParam(name = "maxVideos", defaultValue = "10") @Min(0) Integer maxVideos,
                                 @RequestParam(name = "maxComments", defaultValue = "2") @Min(0) Integer maxComments) {
        Channel channel = channelService.getChannelWithVideos(channelName, maxVideos, maxComments);
        return Transformer.toVMChannel(channel);
    }

    @PostMapping("/{channelName}")
    public VM_Channel postChannel(@PathVariable String channelName,
                                  @RequestParam(name = "maxVideos", defaultValue = "10") @Min(0) Integer maxVideos,
                                  @RequestParam(name = "maxComments", defaultValue = "2") @Min(0) Integer maxComments) {
        Channel channel = channelService.getChannelWithVideos(channelName, maxVideos, maxComments);
        VM_Channel vm_channel = Transformer.toVMChannel(channel);
        return videoMinerService.postChannel(vm_channel);
    }
}
