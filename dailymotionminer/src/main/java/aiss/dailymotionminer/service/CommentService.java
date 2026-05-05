package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.dailymotion.Comment;
import aiss.dailymotionminer.model.dailymotion.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    String BASE_URI = "https://api.dailymotion.com";

    public List<Comment> getCommentsFromVideo(String videoId) {
//        if (maxPages == 0) return Collections.emptyList(); //To avoid unnecessary API requests.

        String uri = BASE_URI + "/video/" + videoId
                + "?fields=id,title,description,created_time,tags";
        Video video = restTemplate.getForObject(uri, Video.class);

        assert video != null;
        List<String> tags = video.getTags();
        List<Comment> comments;

        if(tags != null) {
            comments = tags.stream()
//                    .limit(maxPages)
                    .map(tag -> new Comment(
                            null, //id
                            tag, //Video tag
                            video.getCreatedTime().toString() //Video time stamp string
                    ))
                    .collect(Collectors.toList());
        } else {
            comments = Collections.emptyList();
        }

        return comments;
    }
}
