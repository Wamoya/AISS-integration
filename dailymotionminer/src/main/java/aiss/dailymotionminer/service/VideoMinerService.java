package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.videominer.VM_Channel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VideoMinerService {


    private final RestTemplate restTemplate;

    public VideoMinerService() {
        this.restTemplate = new RestTemplate();
    }

    public VM_Channel sendChannel(VM_Channel channelToSend) {
        String videoMinerUrl = "http://localhost:8080/api/videominer/channels";

        try {
            VM_Channel savedChannel = restTemplate.postForObject(videoMinerUrl, channelToSend, VM_Channel.class);
            return savedChannel;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;

        }
    }
}