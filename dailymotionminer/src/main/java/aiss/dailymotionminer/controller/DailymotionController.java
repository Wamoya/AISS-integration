package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.etl.Transformer;
import aiss.dailymotionminer.model.dailymotion.Channel;
import aiss.dailymotionminer.model.videominer.VM_Channel;
import aiss.dailymotionminer.service.ChannelService;
import aiss.dailymotionminer.service.VideoMinerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "DailyMotion Channel", description = "Channel management in DailyMotion")
@RestController
@RequestMapping("/api/dailymotionminer/v1")
public class DailymotionController {

    @Autowired
    ChannelService channelService;
    @Autowired
    VideoMinerService videoMinerService;

    @Operation(
            summary = "Retrieve a channel",
            description = "Get a channel from DailyMotion",
            tags = {"GET"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = VM_Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
    })
    @GetMapping("/{channelName}")
    public VM_Channel getChannel(@Parameter(description = "Name of the DailyMotion channel to be searched") @PathVariable String channelName,
                                 @RequestParam(name = "maxVideos", defaultValue = "10") @Min(0) @Max(1000) Integer maxVideos, //The Dailymotion API allows for a maximum of 10 pages with a maximum 100 videos per page, so our API will be able to request at most 1000 videos from a channel.
                                 @RequestParam(name = "maxPages", defaultValue = "2") @Min(0) Integer maxPages) {
        Channel channel = channelService.getChannelWithVideos(channelName, maxVideos, maxPages);
        return Transformer.toVMChannel(channel);
    }

    @Operation(
            summary = "Insert a channel into VideoMiner",
            description = "Insert a DailyMotion Channel object into VideoMiner",
            tags = {"POST"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = VM_Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{channelName}")
    public VM_Channel postChannel(@Parameter(description = "Name of the DailyMotion channel to be inserted into VideoMiner") @PathVariable String channelName,
                                  @RequestParam(name = "maxVideos", defaultValue = "10") @Min(0) @Max(1000) Integer maxVideos,
                                  @RequestParam(name = "maxComments", defaultValue = "2") @Min(0) Integer maxComments) {
        Channel channel = channelService.getChannelWithVideos(channelName, maxVideos, maxComments);
        VM_Channel vm_channel = Transformer.toVMChannel(channel);
        return videoMinerService.postChannel(vm_channel);
    }

}
