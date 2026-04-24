package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.*;
import aiss.peertubeminer.model.videominer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class VideoService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CommentService commentService;
    @Autowired
    CaptionService captionService;

    String BASE_URI = "https://peertube.tv/api/v1";

    // GET https://peertube.tv/api/v1/:id?maxVideos={Integer}&maxComments={Integer}
    public List<Video> getAllVideosFromChannel(String channelHandle, Integer maxVideos) {
//        maxVideos = Math.max(maxVideos, 1); // TODO. The controller will be the one checking the conditions in the future
//        maxVideos = Math.min(maxVideos, 100);

        String uri = BASE_URI + "/video-channels/" + channelHandle + "/videos?count=" + maxVideos;
        ResponseEntity<Video_Data> response = restTemplate.getForEntity(uri, Video_Data.class); // TODO. Identify why is this line returning an exception

        List<Video> videos = new ArrayList<>();

        if (response.getBody() != null) {
            videos = response.getBody().getData();
        }

        return videos;
    }

    public Video getVideoFull(Video video, Integer maxComments) {
        String videoId = video.getId().toString();
        String uri = BASE_URI + "/videos/" + videoId + "/comments";

        List<Comment> comments = commentService.getCommentsFromVideo(videoId, maxComments);
        video.setComments(comments);

        List<Caption> captions = captionService.getCaptionsFromVideo(videoId);
        video.setCaptions(captions);

        return video;
    }

    // TODO. We will send Channel objects with all the info to VM Miner instead of Video objects
    // POST https://peertube.tv/api/v1/:id?maxVideos={Integer}&maxComments={Integer}
//    public List<VM_Video> postAllVideosFromChannel(String channelHandle, Integer maxVideos, Integer maxComments) {
//        String uri = "http://localhost:8080/api/videominer/videos";
//
//        List<Video> pt_videos = getAllVideosFromChannel(channelHandle, maxVideos, maxComments);
//        List<VM_Video> vm_videos = new ArrayList<>();
//
//        // For each video, we have to make additional requests to get their comments and captions.
//        // Once we have all data required to build a VM_Video object, we do so and append it to the accumulator.
//        for (Video v : pt_videos) {
//            Integer videoId = v.getId();
//
//            // We get the List<Comment> for the video we are currently iterating
//            String uri_comments = BASE_URI + "/videos/" + videoId + "/comment-threads";
//            ResponseEntity<Comment_Data> r_comments = restTemplate.getForEntity(uri_comments, Comment_Data.class);
//            List<Comment> comments = new ArrayList<>();
//            if (r_comments.getBody() != null) {
//                comments = r_comments.getBody().getData();
//            }
//
//            // We also get the List<Caption>
//            String uri_captions = BASE_URI + "/videos/" + videoId + "/captions";
//            ResponseEntity<Caption_Data> r_captions = restTemplate.getForEntity(uri_captions, Caption_Data.class);
//            List<Caption> captions = new ArrayList<>();
//            if (r_captions.getBody() != null) {
//                captions = r_captions.getBody().getData();
//            }
//
//            // We create a VM_Channel object
//            VM_Channel vm_channel = new VM_Channel(
//                    UUID.randomUUID().toString(),
//                    v.getChannel().getName(),
//                    v.getChannel().getDescription(),
//                    v.getChannel().getCreatedAt()
//            );
//
//            // We create a VM_User object
//            VM_User vm_user = new VM_User(
//                    UUID.randomUUID().toString(),
//                    v.getAccount().getName(),
//                    v.getAccount().getUrl(),
//                    v.getAccount().getAvatars().getFirst().getFileUrl()
//            );
//
//            // We create a List<VM_Comment> object
//            List<VM_Comment> vm_comments = new ArrayList<>();
//            for (Comment c : comments) {
//                vm_comments.add(new VM_Comment(
//                                UUID.randomUUID().toString(),
//                                c.getText(),
//                                c.getCreatedAt()
//                        )
//                );
//            }
//
//            // We create a List<VM_Caption> object
//            List<VM_Caption> vm_captions = new ArrayList<>();
//            for (Caption c : captions) {
//                vm_captions.add(new VM_Caption(
//                        UUID.randomUUID().toString(),
//                        c.getFileUrl(),
//                        c.getUpdatedAt()
//                        )
//                );
//            }
//
//            // We add a new VM_Video to the accumulator
//            vm_videos.add(
//                    new VM_Video(
//                            UUID.randomUUID().toString(),
//                            v.getName(),
//                            v.getDescription(),
//                            v.getPublishedAt()
//                    )
//            );
//        }
//
//        // Now that we have the objects, we send them via POST request to Video Miner
//        ResponseEntity<VM_Video[]> response = restTemplate.postForEntity(uri, vm_videos, VM_Video[].class);
//
//        return response.getBody() == null
//                ? null
//                : Arrays.asList(response.getBody());
//    }
}
