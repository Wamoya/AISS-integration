package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.peertube.VideoSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {
    @Autowired
    RestTemplate restTemplate;

    // String BASE_URI = "https://peertube2.cpy.sm/api/v1"; // Developer instance
    String BASE_URI = "https://peertube.tv/api/v1";   // Different instance (Community-maintained)

    public List<Video> getAllVideosFromChannel(String channelHandle) {
        // Default values (specified on project instructions)
        return getAllVideosFromChannel(channelHandle, 10, 2);
    }
    public List<Video> getAllVideosFromChannel(String channelHandle, Integer maxVideos, Integer maxComments) {
        // baseUri/:id?maxVideos={Integer}&maxComments={Integer}

        // ### count query parameter ###
        // - integer [1..100]
        // - specifies the (max) number of videos to return
        maxVideos = Math.max(maxVideos, 1);
        maxVideos = Math.min(maxVideos, 100);

        String uri = BASE_URI + "/video-channels/" + channelHandle + "/videos?count=" + maxVideos;

//        Video[] videos = restTemplate
//                .getForObject(uri, Video[].class);
        List<Video> videos = new ArrayList<>();

        ResponseEntity<VideoSearch> response = restTemplate.getForEntity(uri, VideoSearch.class);

        videos.addAll(response.getBody().getData());


        return videos;
    }
}
