package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.*;
import aiss.peertubeminer.model.videominer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class VideoService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CommentService commentService;
    @Autowired
    CaptionService captionService;

    String BASE_URI = "https://peertube.tv/api/v1";

    public List<Video> getAllVideosFromChannel(String channelHandle, Integer maxVideos) {
        if (maxVideos == 0) return Collections.emptyList(); //To avoid unnecessary API requests.

        int remaining = maxVideos;
        List<Video> videos = new ArrayList<>();
        boolean possible = true; // True if it's possible to keep asking for more videos
        int stride;
        int offset = 0;

        while (possible && remaining > 0) {
            stride = Math.min(100, remaining);
            List<Video> step = new ArrayList<>();

            String uri = BASE_URI + "/video-channels/" + channelHandle + "/videos?start=" + offset + "&count=" + stride;

            ResponseEntity<Video_Data> response = restTemplate.getForEntity(uri, Video_Data.class);

            if (response.getBody() != null) {
                step.addAll(response.getBody().getData());
            }

            if (step.size() < stride) { // If stride wasn't satisfied, it's because there are no more videos
                possible = false;
            }

            videos.addAll(step);        // Update accumulator and loop variables
            remaining -= step.size();
            offset += stride;

            if (possible && remaining > 0) { // Delay before iterating again to avoid "code 429" errors.
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("The thread was interrupted during the pause.", e);
                }
            }
        }

        return videos;
    }

    public Video getVideoFull(Video video, Integer maxComments) {
        List<Comment> comments = commentService.getCommentsFromVideo(video, maxComments);
        video.setComments(comments);

        List<Caption> captions = captionService.getCaptionsFromVideo(video);
        video.setCaptions(captions);

        return video;
    }
}