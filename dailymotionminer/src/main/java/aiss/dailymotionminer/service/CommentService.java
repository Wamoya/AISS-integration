package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.dailymotion.Comment;
import aiss.dailymotionminer.model.dailymotion.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    VideoService videoService;

    String BASE_URI = "https://api.dailymotion.com";

    public List<Comment> getCommentsFromVideo(String videoId) {
        String uri = BASE_URI + "/video/" + videoId
                + "?fields=id,title,description,created_time,tags";

        Video video = videoService.getVideoInformation(videoId); //Obtaining video data

        List<String> tags = video.getTags();
        if(tags == null) {
            return Collections.emptyList();
        }

        List<Comment> comments = tags.stream()
                .map(tag -> new Comment(
                        UUID.randomUUID().toString(), //Random id assignment
                        tag, //Video tag
                        video.getCreatedTime().toString() //Video time stamp string
                ))
                .collect(Collectors.toList());

        return comments;

    }
}
