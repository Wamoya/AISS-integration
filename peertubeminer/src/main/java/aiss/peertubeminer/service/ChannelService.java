package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.peertube.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    VideoService videoService;

    String BASE_URI = "https://peertube.tv/api/v1";

    public Channel getChannel(String name) {
        String uri = BASE_URI + "/video-channels/" + name;
        return restTemplate.getForObject(uri, Channel.class);
    }

    public Channel getChannelWithVideos(String name, Integer maxVideos, Integer maxComments) {
        Channel channel = getChannel(name);
        List<Video> videos = new ArrayList<>();
        List<Video> allVideos = videoService.getVideosFromChannel(name, maxVideos);
        boolean do_throttle = allVideos.size() > 20; // If #videos exceeds the threshold, throttle the process to avoid "code 429" errors

        for (Video v : allVideos) {
            videos.add(
                    videoService.getVideoWithCommentsAndCaptions(v, maxComments)
            );
            throttle(do_throttle);
        }
        channel.setVideos(videos);

        return channel;
    }

    private void throttle(boolean do_throttle) {
        if (do_throttle) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("The thread was interrupted during the pause.", e);
            }
        }
    }
}
