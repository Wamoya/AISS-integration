package aiss.peertubeminer.repository;

import aiss.peertubeminer.model.peertube.Comment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepository {
    List<Comment> comments = new ArrayList<>();

    public CommentRepository() {
        comments.add(
                new Comment(
                        1,
                        "Hello!",
                        "After watching the video."
                )

        );
        comments.add(
                new Comment(
                        2,
                        "Anyone in 2026?",
                        "2026, probably"
                )

        );
        comments.add(
                new Comment(
                        3,
                        "Dead channel",
                        "10 years ago"
                )
        );
    }

    public List<Comment> findAll() { return this.comments; }

    public Comment findOneById(Integer id) {
        return comments.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
