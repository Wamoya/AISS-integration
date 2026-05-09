package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.dailymotion.Video;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class VideoServiceTest {

    @Autowired
    VideoService videoService;

    @Test
    @DisplayName("Get videos from a channel")
    void getVideos() {
        List<Video> videos = videoService.getVideosFromChannel("elmundo", 20, 2);
        assertFalse(videos.isEmpty(), "The list of videos cannot be empty");
        System.out.println(videos);
    }

    @Test
    @DisplayName("Get videos from a channel using pagination")
    void getVideosUsingPagination() {
        List<Video> videos = videoService.getVideosFromChannel("elmundo", 201, 4);
        assertFalse(videos.isEmpty(), "The list of videos cannot be empty");
        assertFalse(videos.size() != 201, "Should return 201 videos.");
        System.out.println(videos.size());
    }
}
