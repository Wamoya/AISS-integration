package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.dailymotion.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    String BASE_URI = "https://api.dailymotion.com";

    public Channel getChannel(String name) {
        String uri = BASE_URI + "/user/" + name + "?fields=id,screenname,description,created_time";
        return restTemplate.getForObject(uri, Channel.class);
    }
}
