package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.model.dailymotion.Caption;
import aiss.dailymotionminer.model.dailymotion.Channel;
import aiss.dailymotionminer.model.dailymotion.Video;
import aiss.dailymotionminer.model.videominer.VM_Channel;
import aiss.dailymotionminer.model.videominer.*;
import aiss.dailymotionminer.service.ChannelService;
import aiss.dailymotionminer.service.VideoMinerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Tag(name="Dailymotion", description="Dailymotion management API")
@RestController
@RequestMapping("/api/dailymotionminer")
public class DailymotionController {

    @Autowired
    ChannelService channelService;
    @Autowired
    VideoMinerService videoMinerService;

    @Operation(
            summary="Retrieve channel by channel name",
            description="Get all video objects associated to a channel by channel name",
            tags={"channels", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Channel retrieved", content = {@Content(schema = @Schema(implementation = Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Channel not found", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{channelName}")
    public Channel getVideosFromChannel(@PathVariable String channelName,
                                        @RequestParam(name = "maxVideos", defaultValue = "10") @Min(0) @Max(1000) Integer maxVideos, //The Dailymotion API allows for a maximum of 10 pages with a maximum 100 videos per page, so our API will be able to request at most 1000 videos from a channel.
                                        @RequestParam(name = "maxComments", defaultValue = "2") @Min(0) Integer maxComments) {
        return channelService.getChannelFull(channelName, maxVideos, maxComments);
    }

    @Operation(
            summary="Create channel",
            description="Post VideoMiner channel object from DailyMotion channel object",
            tags={"channels", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Channel created", content = {@Content(schema = @Schema(implementation = VM_Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Channel not found", content = {@Content(schema = @Schema())})
    })
    @PostMapping("/{channelName}")
    public VM_Channel postVideosFromChannel(@PathVariable String channelName,
                                            @RequestParam(name = "maxVideos", defaultValue = "10") @Min(1) @Max(1000) Integer maxVideos,
                                            @RequestParam(name = "maxComments", defaultValue = "2") @Min(0) Integer maxComments) {


        Channel channel = channelService.getChannelFull(channelName, maxVideos, maxComments);


        List<VM_Video> vm_videos = new ArrayList<>();

        VM_Channel vm_channel = new VM_Channel(
                UUID.randomUUID().toString(),
                channel.getScreenname(),
                channel.getDescription(),
                String.valueOf(channel.getCreatedTime()),
                vm_videos
        );

        VM_User vm_user = new VM_User(
                UUID.randomUUID().toString(),
                channel.getScreenname(),
                channel.getUrl(),
                channel.getAvatar240url()
        );

        for (Video v : channel.getVideos()) {


            List<VM_Comment> vm_comments = new ArrayList<>();
            //Here we use tags as said in the project description
            if (v.getTags() != null) {
                int count = 0;
                for (String tag : v.getTags()) {
                    if (count >= maxComments) break; //maxComments
                    vm_comments.add(
                            new VM_Comment(
                                    UUID.randomUUID().toString(),
                                    tag, // tag = comment
                                    ""   // Does not have created time
                            )
                    );
                    count++;
                }
            }


            List<VM_Caption> vm_captions = new ArrayList<>();
            if (v.getCaptions() != null) {
                for (Caption s : v.getCaptions()) {
                    vm_captions.add(
                            new VM_Caption(
                                    UUID.randomUUID().toString(),
                                    s.getLanguage(),
                                    s.getUrl() ,
                                    "" // In DM does not have createdtime
                            )
                    );
                }
            }




            vm_videos.add(
                    new VM_Video(
                            UUID.randomUUID().toString(),
                            v.getTitle(),
                            v.getDescription(),
                            String.valueOf(v.getCreatedTime()),
                            vm_channel,
                            vm_user,
                            vm_comments,
                            vm_captions
                    )
            );
        }




        VM_Channel savedChannel = videoMinerService.sendChannel(vm_channel);


        return savedChannel;
    }

}
