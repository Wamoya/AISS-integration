package aiss.videominer.controller;

import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/videominer/v1")
public class VideoController {
    @Autowired
    VideoRepository videoRepository;

    // GET http://localhost:8080/apipath/videos
    @GetMapping("/videos")
    public List<Video> findAllVideos() {
        return videoRepository.findAll();
    }

    // GET http://localhost:8080/apipath/videos/{videoId}
    @GetMapping("/videos/{videoId}")
    public Video findAllVideosById(@PathVariable("videoId") Long id) {
        return videoRepository.findById(id).get();
    }
}
