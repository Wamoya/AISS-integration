package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Video;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class VideoServiceTest {

    @Autowired
    VideoService videoService;

    @Test
    @DisplayName("Get videos from a channel")
    void getVideos() {
        List<Video> videos = videoService.getVideosFromChannel("transport_evolved_take_2", 100);
        assertFalse(videos.isEmpty(), "The list of videos cannot be empty");
        System.out.println(videos);
    }
}
