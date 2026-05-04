package aiss.videominer.controller;

import aiss.videominer.exception.UserNotFoundException;
import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.User;
import aiss.videominer.model.Video;
import aiss.videominer.repository.UserRepository;
import aiss.videominer.repository.VideoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.AssociationOverride;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name="User", description="User management API")
@RestController
@RequestMapping("/api/videominer/v1")
public class UserController {
    @Autowired
    UserRepository userRepository;

    // GET http://localhost:8080/apipath/users
    @Operation(
            summary = "Retrieve a list of users",
            description = "Get a list of all available users",
            tags = {"GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")})
    })
    @GetMapping("/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // TODO: implement GET that gives User from video ID?

    // GET http://localhost:8080/apipath/users/{userId}
    @Operation(
            summary = "Retrieve a user by ID",
            description = "Get a User object by specifying its ID",
            tags = {"GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/users/{userId}")
    public User findOne(@Parameter(description = "ID of the user to be searched") @PathVariable("userId") Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }

    // POST http://localhost:8080/apipath/users
    @Operation(
            summary = "Insert a user",
            description = "Add a user whose data is passed in the body of the request in JSON format by specifying its ID",
            tags = {"POST"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public User create(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    // PUT http://localhost:8080/apipath/users/{userId}
    @Operation(
            summary = "Update a user",
            description = "Update a user whose data is passed in the body of the request in JSON format by specifying its ID",
            tags = {"PUT"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/users/{userId}")
    public void update(@Parameter(description = "ID of the user to be updated") @PathVariable("userId") Long userId,
                       @Valid @RequestBody User updatedUser) throws UserNotFoundException {
        Optional<User> userData = userRepository.findById(userId);
        if(!userData.isPresent()) {
            throw new UserNotFoundException();
        }
        User _user = userData.get();
        _user.setName(updatedUser.getName());
        _user.setUser_link(updatedUser.getUser_link());
        _user.setPicture_link(updatedUser.getPicture_link());
        userRepository.save(_user);
    }

    // DELETE http://localhost:8080/apipath/users/{userId}
    @Operation(
            summary = "Delete a user",
            description = "Delete a user by specifying its ID",
            tags = {"DELETE"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/users/{userId}")
    public void delete(@Parameter(description = "ID of the user to be deleted") @PathVariable("userId") Long userId) throws UserNotFoundException {
        if(userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new UserNotFoundException();
        }
    }

}
