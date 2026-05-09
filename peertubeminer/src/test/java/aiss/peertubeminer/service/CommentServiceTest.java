package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("Get comments from a video")
    void getComments() {
        List<Comment> comments = commentService.getCommentsFromVideo("wsa7yQiUSVnw9HTwba1KiN", 5);
        assertFalse(comments.isEmpty(), "The list of comments cannot be empty");
        System.out.println(comments);
    }
}
