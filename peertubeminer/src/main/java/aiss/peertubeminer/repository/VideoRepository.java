package aiss.peertubeminer.repository;

import aiss.peertubeminer.model.peertube.Video;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VideoRepository {
    List<Video> videos = new ArrayList<>();
    ChannelRepository channelRepository = new ChannelRepository();
    CommentRepository commentRepository = new CommentRepository();


    public VideoRepository() {
        this.videos.add(
                new Video(
                        1,
                        "title",
                        "description",
                        "yesterday",
                        channelRepository.findOneById(1)
//                        new ArrayList<Comment>(
//                                List.of(commentRepository.findOneById(1),
//                                        commentRepository.findOneById(2))
//                                ),
//                        new ArrayList<Caption>()
                )

        );
        this.videos.add(
                new Video(
                        2,
                        "Different video",
                        "Different video's description",
                        "tomorrow",
                        channelRepository.findOneById(1)
//                        new ArrayList<Comment>(),
//                        new ArrayList<Caption>()
                        )
        );
        this.videos.add(
                new Video(
                        3,
                        "Java 101,",
                        "Haskell is better.",
                        "sad",
                        channelRepository.findOneById(3)
//                        new ArrayList<Comment>(List.of(commentRepository.findOneById(3))),
//                        new ArrayList<Caption>()
                )
        );
    }

    public List<Video> findAll() { return this.videos; }

    public Video findOneById(Integer id) {
        return this.videos.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
