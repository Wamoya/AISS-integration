package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.peertube.Comment_Data;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.peertube.Video_Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    String BASE_URI = "https://peertube.tv/api/v1";

    public List<Comment> getCommentsFromVideo(String videoId, Integer maxComments) {
        String uri = BASE_URI + "/" + videoId + "/comment-threads";
        ResponseEntity<Comment_Data> response = restTemplate.getForEntity(uri, Comment_Data.class);

        List<Comment> comments = new ArrayList<>();

        if (response.getBody() != null) {
            comments = response.getBody().getData().stream()
                    .limit(maxComments)
                    .toList();
        }

        return comments;
    }
}
