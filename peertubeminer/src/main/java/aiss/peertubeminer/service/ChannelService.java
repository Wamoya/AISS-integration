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

    public Channel getChannelFull(String name, Integer maxVideos, Integer maxComments) {
        Channel channel = getChannel(name);
        List<Video> videos = new ArrayList<>();

        for (Video v : videoService.getAllVideosFromChannel(name, maxVideos)) {
            videos.add(
                    videoService.getVideoFull(v, maxComments)
            );
        }
        channel.setVideos(videos);

        return channel;
    }
}
