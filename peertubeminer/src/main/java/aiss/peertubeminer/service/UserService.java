package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    String BASE_URI = "https://peertube.tv/api/v1";

    public User getUser(String profileId) {
        String uri = BASE_URI + "/user/" + profileId + "?fields=id,screenname,url,avatar_240_url";
        return restTemplate.getForObject(uri, User.class);
    }
}
