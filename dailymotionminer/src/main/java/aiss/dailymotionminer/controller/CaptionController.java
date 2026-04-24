package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.model.dailymotion.Caption;
import aiss.dailymotionminer.repository.CaptionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dailymotionminer")
public class CaptionController {
    private final CaptionRepository captionRepository;

    public CaptionController(CaptionRepository captionRepository) {
        this.captionRepository = captionRepository;
    }

    @GetMapping("/captions")
    public List<Caption> findAll() {
        return captionRepository.findAll();
    }

    @GetMapping("/captions/{id}")
    public Caption findById(@PathVariable Long id) {
        return captionRepository.findById(id).get();
    }
}
