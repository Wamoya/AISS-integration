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
import java.util.Collections;
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
        if (maxVideos == 0) return Collections.emptyList(); //To avoid unnecessary API requests.

        int remainingVideos = maxVideos;
        int page = 1;
        String uri;
        ResponseEntity<Video_Data> response;
        List<Video> videos = new ArrayList<>();

        do {
            int limit = Math.min(remainingVideos, 100); //The maximum value allowed by the dailymotion API for "limit" is 100 videos per page.

            uri = BASE_URI + "/user/" + profileId
                    + "/videos?fields=id,title,description,created_time,tags&limit=" + limit + "&page=" + page;
            response = restTemplate.getForEntity(uri, Video_Data.class);

            if (response.getBody() != null) {
                videos.addAll(response.getBody().getList());
            }

            remainingVideos -= limit; //Subtract obtained videos.
            page++;

        } while(response.getBody().getHasMore() && (videos.size() < maxVideos));

        return videos;
    }

    public Video getVideoFull(Video video, Integer maxComments) {
        String videoId = video.getId();
        List<Comment> comments = commentService.getCommentsFromVideo(videoId, maxComments);
        video.setComments(comments);

        List<Caption> captions = captionService.getCaptionsFromVideo(videoId);
        video.setCaptions(captions);

        return video;
    }

}
