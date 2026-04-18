package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.repository.CommentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/peertubeminer")
public class CommentController {

    public final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/comments")
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @GetMapping("/comments/{id}")
    public Comment findById(@PathVariable Integer id) {
        return commentRepository.findOneById(id);
    }

}
