package aiss.videominer.controller;

import aiss.videominer.exception.CommentNotFoundException;
import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(name = "Video", description = "Video management API")
@RestController
@RequestMapping("/api/videominer/v1")
public class VideoController {

    @Autowired
    VideoRepository videoRepository;

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
}
