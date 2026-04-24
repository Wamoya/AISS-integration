package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.model.dailymotion.Video;
import aiss.dailymotionminer.repository.VideoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dailymotionminer")
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
    public Video findById(@PathVariable Long id) {
        return videoRepository.findById(id).get();
    }
}
