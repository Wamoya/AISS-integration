package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.dailymotion.Caption;
import aiss.dailymotionminer.model.dailymotion.Comment;
import aiss.dailymotionminer.model.dailymotion.Video;
import aiss.dailymotionminer.model.dailymotion.Video_Data;
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
    @Autowired
    CommentService commentService;
    @Autowired
    CaptionService captionService;

    String BASE_URI = "https://api.dailymotion.com";

    public List<Video> getVideosFromChannel(String profileId, Integer maxVideos) {
        String uri = BASE_URI + "/user/" + profileId
                + "/videos?fields=id,title,description,created_time,tags&limit=" + maxVideos;
        ResponseEntity<Video_Data> response = restTemplate.getForEntity(uri, Video_Data.class);

        List<Video> videos = new ArrayList<>();

        if (response.getBody() != null) {
            videos = response.getBody().getList();
        }

        return videos;
    }

    //Unused
//    public Video getVideoInformation(String videoId) {
//        String uri = BASE_URI + "/video/" + videoId
//                + "?fields=id,title,description,created_time,tags";
//        return restTemplate.getForObject(uri, Video.class);
//    }

    public Video getVideoFull(Video video, Integer maxComments) {
        String videoId = video.getId();
        List<Comment> comments = commentService.getCommentsFromVideo(videoId, maxComments);
        video.setComments(comments);

        List<Caption> captions = captionService.getCaptionsFromVideo(videoId);
        video.setCaptions(captions);

        return video;
    }

}
