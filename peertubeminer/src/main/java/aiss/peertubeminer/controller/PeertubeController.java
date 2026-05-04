package aiss.peertubeminer.controller;

import aiss.peertubeminer.etl.Transformer;
import aiss.peertubeminer.model.peertube.*;
import aiss.peertubeminer.model.videominer.*;
import aiss.peertubeminer.service.ChannelService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/peertubeminer")
public class PeertubeController {

    @Autowired
    ChannelService channelService;

    @GetMapping("/{channelName}")
    public VM_Channel getVideosFromChannel(@PathVariable String channelName,
                                        @RequestParam(name = "maxVideos", defaultValue = "10") @Min(0) Integer maxVideos,
                                        @RequestParam(name = "maxComments", defaultValue = "2") @Min(0) Integer maxComments) {
        Channel channel = channelService.getChannelFull(channelName, maxVideos, maxComments);
        return Transformer.toVMChannel(channel);
    }

    @PostMapping("/{channelName}")
    public VM_Channel postVideosFromChannel(@PathVariable String channelName,
                                            @RequestParam(name = "maxVideos", defaultValue = "10") @Min(0) Integer maxVideos,
                                            @RequestParam(name = "maxComments", defaultValue = "2") @Min(0) Integer maxComments) {
        Channel channel = channelService.getChannelFull(channelName, maxVideos, maxComments);
        VM_Channel vm_channel = Transformer.toVMChannel(channel);
        // TODO. Send the VM objects to VM Miner
        return vm_channel;
    }
}
