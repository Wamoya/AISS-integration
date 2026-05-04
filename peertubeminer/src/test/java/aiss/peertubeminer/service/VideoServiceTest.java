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
    String test_ChannelHandle = "transport_evolved_take_2";

    @Autowired
    VideoService service;

    @Test
    @DisplayName("Get videos from a video channel. (Default values)")
    void findVideosFromChannelDefault() {
        List<Video> videos = service.getVideosFromChannel(test_ChannelHandle);
        assertFalse(videos.isEmpty());
        System.out.println(videos);
    }

    @Test
    @DisplayName("Get videos from a video channel. (maxVideos=2&maxComments=10)")
    void findVideosFromChannelParams() {
        List<Video> videos = service.getVideosFromChannel(test_ChannelHandle, 2, 10);
        assertFalse(videos.isEmpty());
        System.out.println(videos);
    }
}
