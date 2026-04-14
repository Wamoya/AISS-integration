package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.service.VideoService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/peertube")
public class VideoController {

    @GetMapping("/{id}") // id is a String type, and PeerTube receives it as the channelHandle
    public List<Video> findAllVideosFromChannel(@PathVariable String id,
                                                @RequestParam(defaultValue="10") Integer maxVideos,
                                                @RequestParam(defaultValue="2") Integer maxComments) {
        return null;//VideoService.getAllVideosFromChannel(id, maxVideos, maxComments);
    }

}
