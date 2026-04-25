package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.dailymotion.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    String BASE_URI = "https://api.dailymotion.com";

    public User getUser(String profileId) {
        String uri = BASE_URI + "/user/" + profileId + "?fields=id,screenname,url,avatar_240_url";
        return restTemplate.getForObject(uri, User.class);
    }
}
