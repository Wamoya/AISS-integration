package aiss.peertubeminer.controller;

import aiss.peertubeminer.etl.Transformer;
import aiss.peertubeminer.model.peertube.*;
import aiss.peertubeminer.model.videominer.*;
import aiss.peertubeminer.service.ChannelService;
import aiss.peertubeminer.service.VideoMinerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "PeerTube Channel", description = "Channel management in PeerTube")
@RestController
@RequestMapping("/api/peertubeminer/v1")
public class PeertubeController {

    @Autowired
    ChannelService channelService;
    @Autowired
    VideoMinerService videoMinerService;

    @Operation(
            summary = "Retrieve a channel",
            description = "Get a channel from PeerTube",
            tags = {"GET"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = VM_Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
    })
    @GetMapping("/{channelName}")
    public VM_Channel getChannel(@Parameter(description = "Name of the PeerTube channel to be searched") @PathVariable String channelName,
                                 @RequestParam(name = "maxVideos", defaultValue = "10") @Min(0) Integer maxVideos,
                                 @RequestParam(name = "maxComments", defaultValue = "2") @Min(0) Integer maxComments) {
        Channel channel = channelService.getChannelWithVideos(channelName, maxVideos, maxComments);
        return Transformer.toVMChannel(channel);
    }

    @Operation(
            summary = "Insert a channel into VideoMiner",
            description = "Insert a PeerTube Channel object into VideoMiner",
            tags = {"POST"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = VM_Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{channelName}")
    public VM_Channel postChannel(@Parameter(description = "Name of the PeerTube channel to be inserted into VideoMiner") @PathVariable String channelName,
                                  @RequestParam(name = "maxVideos", defaultValue = "10") @Min(0) Integer maxVideos,
                                  @RequestParam(name = "maxComments", defaultValue = "2") @Min(0) Integer maxComments) {
        Channel channel = channelService.getChannelWithVideos(channelName, maxVideos, maxComments);
        VM_Channel vm_channel = Transformer.toVMChannel(channel);
        return videoMinerService.postChannel(vm_channel);
    }
}
