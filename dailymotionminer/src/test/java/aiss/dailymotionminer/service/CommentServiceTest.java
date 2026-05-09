package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.dailymotion.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;



@SpringBootTest
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("Get video comments")
    void getComments() {
        List<Comment> comments = commentService.getCommentsFromVideo("xa8pdrm");
        assertFalse(comments.isEmpty(), "The list of comments (tags) cannot be empty");
        System.out.println(comments);
    }




}
