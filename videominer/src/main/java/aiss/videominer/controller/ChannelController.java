package aiss.videominer.controller;

import aiss.videominer.exception.ChannelNotFoundException;
import aiss.videominer.model.Channel;
import aiss.videominer.repository.ChannelRepository;
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

@Tag(name = "Channel", description = "Channel management API")
@RestController
@RequestMapping("/api/videominer/v1")
public class ChannelController {
    @Autowired
    ChannelRepository channelRepository;

    // GET http://localhost:8080/api/videominer/v1/channels
    @Operation(
            summary = "Retrieve a list of channels",
            description = "Get a list of available channels",
            tags = {"GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Channel.class), mediaType = "application/json")})
    })
    @GetMapping("/channels")
    public List<Channel> findAll() {
        return channelRepository.findAll();
    }

    // GET http://localhost:8080/api/videominer/v1/channels/{channelId}
    @Operation(
            summary = "Retrieve a channel by it's ID",
            description = "Get a channel by specifying it's ID",
            tags = {"GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/channels/{channelId}")
    public Channel findOne(@Parameter(description = "ID of channel to retrieve") @PathVariable("channelId") Long channelId) throws ChannelNotFoundException {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if(!channel.isPresent()) {
            throw new ChannelNotFoundException();
        }
        return channel.get();
    }

    // POST http://localhost:8080/api/videominer/v1/channels
    @Operation(
            summary = "Insert a channel",
            description = "Add a channel whose data is passed in the body of the request in JSON format by specifying it's ID",
            tags = {"POST"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/channels")
    public Channel create(@Valid @RequestBody Channel channel) {
        Channel savedChannel = new Channel(
                channel.getName(),
                channel.getDescription(),
                channel.getCreatedTime()
        );
        savedChannel.setVideos(channel.getVideos());
        return channelRepository.save(savedChannel);
    }

    // PUT http://localhost:8080/api/videominer/v1/channels/{channelId}
    @Operation(
            summary = "Update a channel",
            description = "Update a channel whose data is passed in the body of the request in JSON format by specifying it's ID",
            tags = {"PUT"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema(implementation = Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/channels/{channelId}")
    public void update(@Parameter(description = "Update a channel") @PathVariable("channelId")  Long channelId,
                       @Valid @RequestBody Channel updatedChannel) throws ChannelNotFoundException {
        Optional<Channel> channelData = channelRepository.findById(channelId);
        if(!channelData.isPresent()) {
            throw new ChannelNotFoundException();
        }
        Channel _channel = channelData.get();
        _channel.setName(updatedChannel.getName());
        _channel.setDescription(updatedChannel.getDescription());
        _channel.setVideos(updatedChannel.getVideos());
        _channel.setCreatedTime(updatedChannel.getCreatedTime());
        channelRepository.save(_channel);
    }

    // DELETE http://localhost:8080/api/videominer/v1/channels/{channelId}
    @Operation(
            summary = "",
            description = "",
            tags = {"DELETE"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema(implementation = Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/channels/{channelId}")
    public void delete(@Parameter(description = "Delete a channel") @PathVariable("channelId") Long channelId) throws ChannelNotFoundException {
        if(channelRepository.existsById(channelId)) {
            channelRepository.deleteById(channelId);
        } else {
            throw new ChannelNotFoundException();
        }
    }
}
