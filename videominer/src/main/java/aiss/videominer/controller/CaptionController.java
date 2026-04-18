package aiss.videominer.controller;

import aiss.videominer.model.Caption;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CaptionRepository;
import aiss.videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CaptionController {

    @Autowired
    CaptionRepository captionRepository;
    @Autowired
    VideoRepository videoRepository;

    // GET http://localhost:8080/api/captions
    @GetMapping("/captions")
    public List<Caption> getAllCaptions() {
        return captionRepository.findAll();
    }

    // GET http://localhost:8080/api/videos/{videoId}/captions
    @GetMapping("videos/{videoId}/captions")
    public List<Caption> getAllCaptionsFromVideo(@PathVariable("videoId") Long videoId) {
        Video video = videoRepository.findById(videoId).get();
        return video.getCaptions();
    }

    // GET http://localhost:8080/api/captions/{captionId}
    @GetMapping("captions/{captionId}")
    public Caption getCaptionFromId(@PathVariable("captionId") Long captionId) {
        return captionRepository.findById(captionId).get();
    }
}
