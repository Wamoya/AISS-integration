package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.model.dailymotion.Channel;
import aiss.dailymotionminer.repository.ChannelRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dailymotionminer")
public class ChannelController {
    private final ChannelRepository channelRepository;

    public ChannelController(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @GetMapping("/channels")
    public List<Channel> findAll() {
        return channelRepository.findAll();
    }

    @GetMapping("/channels/{id}")
    public Channel findById(@PathVariable Long id) {
        return channelRepository.findById(id).get();
    }
}
