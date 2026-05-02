package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.peertube.Comment_Data;
import aiss.peertubeminer.model.peertube.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    String BASE_URI = "https://peertube.tv/api/v1";

    public List<Comment> getCommentsFromVideo(Video video, Integer maxComments) {
        if (maxComments == 0 || video.getNumComments() == 0) return Collections.emptyList(); //To avoid unnecessary API requests.

        String videoId = video.getId();
        int remaining = maxComments;
        List<Comment> comments = new ArrayList<>();
        boolean possible = true;
        int stride;
        int offset = 0;

        while (possible && remaining > 0) {
            stride = Math.min(100, remaining);
            List<Comment> step = new ArrayList<>();

            String uri = BASE_URI + "/videos/" + videoId + "/comment-threads?start=" + offset + "&count=" + stride;

            ResponseEntity<Comment_Data> response = restTemplate.getForEntity(uri, Comment_Data.class);

            if (response.getBody() != null) {
                step = response.getBody().getData();
            }

            if (step.size() < stride) { // If stride wasn't satisfied, it's because there are no more videos
                possible = false;
            }

            comments.addAll(step);
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

        return comments;
    }
}
