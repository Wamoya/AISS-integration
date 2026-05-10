package aiss.videominer.controller;

import aiss.videominer.exception.ChannelNotFoundException;
import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Channel;
import aiss.videominer.model.Video;
import aiss.videominer.repository.ChannelRepository;
import aiss.videominer.repository.VideoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Video", description = "Video management API")
@RestController
@RequestMapping("/api/videominer/v1")
public class VideoController {

    @Autowired
    VideoRepository videoRepository;
    @Autowired
    ChannelRepository channelRepository;

    // GET http://localhost:8080/api/videominer/v1/videos
    @Operation(
            summary = "Retrieve a list of videos",
            description = "Get a list of all available videos",
            tags = {"GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Video.class), mediaType = "application/json")})
    })
    @GetMapping("/videos")
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    // GET http://localhost:8080/api/videominer/v1/videos/{videoId}
    @Operation(
            summary = "Retrieve a video by ID",
            description = "Get a Video object by specifying its ID",
            tags = {"GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Video.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/videos/{videoId}")
    public Video findOne(@Parameter(description = "ID of the video to be searched") @PathVariable("videoId") Long videoId) throws VideoNotFoundException {
        Optional<Video> video = videoRepository.findById(videoId);
        if (!video.isPresent()) {
            throw new VideoNotFoundException();
        }
        return video.get();
    }

    // POST http://localhost:8080/api/videominer/v1/channels/{channelId}/videos
    @Operation(
            summary = "Insert a video in a channel",
            description = "Add a video whose data is passed in the body of the request in JSON format",
            tags = {"POST"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Video.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/channels/{channelId}/videos")
    public Video create(@Parameter(description = "Channel ID where the video will be created") @PathVariable("channelId") Long channelId,
                        @Valid @RequestBody Video video) throws ChannelNotFoundException {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (!channel.isPresent()) {
            throw new ChannelNotFoundException();
        }

        channel.get().getVideos().add(video);
        return videoRepository.save(video);
    }

    // PUT http://localhost:8080/api/videominer/v1/videos/{videoId}
    @Operation(
            summary = "Update a video",
            description = "Update a video whose data is passed in the body of the request in JSON format by specifying its ID",
            tags = {"PUT"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema(implementation = Video.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/videos/{videoId}")
    public void update(@Parameter(description = "ID of the video to be updated") @PathVariable("videoId") Long videoId,
                       @Valid @RequestBody Video updatedVideo) throws VideoNotFoundException {
        Optional<Video> videoData = videoRepository.findById(videoId);
        if(!videoData.isPresent()) {
            throw new VideoNotFoundException();
        }
        Video _video = videoData.get();
        _video.setName(updatedVideo.getName());
        _video.setDescription(updatedVideo.getDescription());
        _video.setReleaseTime(updatedVideo.getReleaseTime());
        _video.setUser(updatedVideo.getUser());
        _video.setComments(updatedVideo.getComments());
        _video.setCaptions(updatedVideo.getCaptions());
        videoRepository.save(_video);
    }

    // DELETE http://localhost:8080/api/videominer/v1/videos/{videoId}
    @Operation(
            summary = "Delete a video",
            description = "Delete a video by specifying its ID",
            tags = {"DELETE"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema(implementation = Video.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/videos/{videoId}")
    public void delete(@Parameter(description = "ID of the video to be deleted") @PathVariable("videoId") Long videoId) throws VideoNotFoundException {
        if(videoRepository.existsById(videoId)) {
            videoRepository.deleteById(videoId);
        } else {
            throw new VideoNotFoundException();
        }
    }
}
