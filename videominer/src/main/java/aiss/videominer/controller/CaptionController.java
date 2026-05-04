package aiss.videominer.controller;

import aiss.videominer.exception.CaptionNotFoundException;
import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CaptionRepository;
import aiss.videominer.repository.VideoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Caption", description = "Caption management API")
@RestController
@RequestMapping("/api")
public class CaptionController {

    @Autowired
    CaptionRepository captionRepository;

    @Autowired
    VideoRepository videoRepository;


    // GET http://localhost:8080/apipath/captions
    @Operation(
            summary = "Retrieve a list of captions",
            description = "Get a list of all available captions",
            tags = {"GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Caption.class), mediaType = "application/json")})
    })
    @GetMapping("/captions")
    public List<Caption> findAll() {
        return captionRepository.findAll();
    }

    // GET http://localhost:8080/apipath/videos/{videoId}/captions
    @Operation(
            summary = "Retrieve all captions of a video using its ID",
            description = "Get all available captions of a video by specifying its ID",
            tags = {"GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Caption.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("videos/{videoId}/captions")
    public List<Caption> getAllCaptionsFromVideo(@Parameter(description = "Video ID to which the captions belong") @PathVariable("videoId") Long videoId) throws VideoNotFoundException {
        Optional<Video> video = videoRepository.findById(videoId);
        if (!video.isPresent()) {
            throw new VideoNotFoundException();
        }
        return video.get().getCaptions();
    }

    // GET http://localhost:8080/apipath/captions/{captionId}
    @Operation(
            summary = "Retrieve a caption by ID",
            description = "Get a Caption object by specifying its ID",
            tags = {"GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Caption.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("captions/{captionId}")
    public Caption findOne(@Parameter(description = "ID of the caption to be searched") @PathVariable("captionId") Long captionId) throws CaptionNotFoundException {
        Optional<Caption> caption = captionRepository.findById(captionId);
        if (!caption.isPresent()) {
            throw new CaptionNotFoundException();
        }
        return caption.get();
    }

    // POST http://localhost:8080/apipath/videos/{videoId}/captions
    @Operation(
            summary = "Insert a caption in a video",
            description = "Add a caption whose data is passed in the body of the request in JSON format to a video by specifying its ID",
            tags = {"POST"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Caption.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/video/{videoId}/captions")
    public Caption create(@Parameter(description = "Video ID where the caption will be created") @PathVariable("videoId") Long videoId,
                          @Valid @RequestBody Caption caption) throws VideoNotFoundException {
        Optional<Video> video = videoRepository.findById(videoId);
        if(!video.isPresent()) {
            throw new VideoNotFoundException();
        }

        video.get().getCaptions().add(caption);
        return captionRepository.save(caption);
    }

    // PUT http://localhost:8080/apipath/captions/{captionId}
    @Operation(
            summary = "Update a caption",
            description = "Update a caption whose data is passed in the body of the request in JSON format by specifying its ID",
            tags = {"PUT"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema(implementation = Caption.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("captions/{captionId}")
    public void update(@Parameter(description = "ID of the caption to be updated") @PathVariable("captionId") Long captionId,
                       @Valid @RequestBody Caption updatedCaption) throws CaptionNotFoundException {
        Optional<Caption> captionData = captionRepository.findById(captionId);
        if(!captionData.isPresent()) {
            throw new CaptionNotFoundException();
        }

        Caption _caption = captionData.get();
        _caption.setName(updatedCaption.getName());
        _caption.setLanguage(updatedCaption.getLanguage());
        captionRepository.save(_caption);
    }

    // DELETE http://localhost:8080/apipath/captions/{captionId}
    @Operation(
            summary = "Delete a caption",
            description = "Delete a caption by specifying its ID",
            tags = {"DELETE"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema(implementation = Caption.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/caption/{captionId}")
    public void delete(@Parameter(description = "ID of the caption to be deleted") @PathVariable("captionId") Long captionId) {
        if(captionRepository.existsById(captionId)) {
            captionRepository.deleteById(captionId);
        }
    }

}
