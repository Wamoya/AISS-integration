package aiss.videominer.controller;

import aiss.videominer.exception.CommentNotFoundException;
import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CommentRepository;
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


@Tag(name = "Comment", description = "Comment management API")
@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    VideoRepository videoRepository;


    // GET http://localhost:8080/apipath/comments
    @Operation(
            summary = "Retrieve a list of comments",
            description = "Get a list of all available comments",
            tags = {"GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")})
    })
    @GetMapping("/comments")
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    // GET http://localhost:8080/apipath/videos/{videoId}/comments
    @Operation(
            summary = "Retrieve all comments of a video using its ID",
            description = "Get all available comments of a video by specifying its ID",
            tags = {"GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("videos/{videoId}/comments")
    public List<Comment> getAllCommentsFromVideo(@Parameter(description = "Video ID to which the comments belong") @PathVariable("videoId") Long videoId) throws VideoNotFoundException {
        Optional<Video> video = videoRepository.findById(videoId);
        if (!video.isPresent()) {
            throw new VideoNotFoundException();
        }
        return video.get().getComments();
    }

    // GET http://localhost:8080/apipath/comments/{commentId}
    @Operation(
            summary = "Retrieve a comment by ID",
            description = "Get a Caption object by specifying its ID",
            tags = {"GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("comments/{commentId}")
    public Comment findOne(@Parameter(description = "ID of the comment to be searched") @PathVariable("commentId") Long commentId) throws CommentNotFoundException {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (!comment.isPresent()) {
            throw new CommentNotFoundException();
        }
        return comment.get();
    }

    // POST http://localhost:8080/apipath/videos/{videoId}/comments
    @Operation(
            summary = "Insert a comment in a video",
            description = "Add a comment whose data is passed in the body of the request in JSON format to a video by specifying its ID",
            tags = {"POST"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/video/{videoId}/comments")
    public Comment create(@Parameter(description = "Video ID where the comment will be created") @PathVariable("videoId") Long videoId,
                          @Valid @RequestBody Comment comment) throws VideoNotFoundException {
        Optional<Video> video = videoRepository.findById(videoId);
        if (!video.isPresent()) {
            throw new VideoNotFoundException();
        }

        video.get().getComments().add(comment);
        return commentRepository.save(comment);
    }

    // PUT http://localhost:8080/apipath/comments/{commentId}
    @Operation(
            summary = "Update a comment",
            description = "Update a comment whose data is passed in the body of the request in JSON format by specifying its ID",
            tags = {"PUT"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("comments/{commentId}")
    public void update(@Parameter(description = "ID of the comment to be updated") @PathVariable("commentId") Long commentId,
                       @Valid @RequestBody Comment updatedComment) throws CommentNotFoundException {
        Optional<Comment> commentData = commentRepository.findById(commentId);
        if (!commentData.isPresent()) {
            throw new CommentNotFoundException();
        }

        Comment _comment = commentData.get();
        _comment.setText(updatedComment.getText());
        _comment.setCreatedOn(updatedComment.getCreatedOn());
        commentRepository.save(_comment);
    }

    // DELETE http://localhost:8080/apipath/comments/{commentId}
    @Operation(
            summary = "Delete a comment",
            description = "Delete a comment by specifying its ID",
            tags = {"DELETE"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/comment/{commentId}")
    public void delete(@Parameter(description = "ID of the comment to be deleted") @PathVariable("commentId") Long commentId) {
        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
        }
    }

}
