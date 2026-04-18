package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.repository.VideoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/peertubeminer")
public class VideoController {

    private final VideoRepository videoRepository;

    public VideoController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @GetMapping("/videos")
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    @GetMapping("/videos/{id}")
    public Video findById(@PathVariable Integer id) {
        return videoRepository.findOnetById(id);
    }

//    @GetMapping("/{id}/videos") // id is a String type, and PeerTube receives it as the channelHandle
//    public List<Video> findAllVideosFromChannel(@PathVariable String id,
//                                                @RequestParam(defaultValue="10") Integer maxVideos,
//                                                @RequestParam(defaultValue="2") Integer maxComments) {
//        return null; //VideoService.getAllVideosFromChannel(id, maxVideos, maxComments);
//    }

}
