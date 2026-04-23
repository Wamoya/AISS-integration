package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.model.dailymotion.Comment;
import aiss.dailymotionminer.repository.CommentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dailymotionminer")
public class CommentController {
    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/comments")
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @GetMapping("/comments/{id}")
    public Comment findById(@PathVariable Long id) {
        return commentRepository.findById(id).get();
    }
}
