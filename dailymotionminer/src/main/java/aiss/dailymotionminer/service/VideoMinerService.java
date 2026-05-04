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

    String BASE_URI = "http://localhost:8080/api/videominer/v1";

    public VM_Channel postChannel(VM_Channel channelToSend) {
        String videoMinerUrl = BASE_URI + "/channels";
        return restTemplate.postForObject(videoMinerUrl, channelToSend, VM_Channel.class);
    }
}