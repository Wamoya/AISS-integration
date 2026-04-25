package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.dailymotion.Caption;
import aiss.dailymotionminer.model.dailymotion.Caption_Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaptionService {

    @Autowired
    RestTemplate restTemplate;

    String BASE_URI = "https://api.dailymotion.com";

    public List<Caption> getCaptionsFromVideo(String videoId) {
        String uri = BASE_URI + "/video/" + videoId + "/subtitles";
        ResponseEntity<Caption_Data> response = restTemplate.getForEntity(uri, Caption_Data.class);

        List<Caption> captions = new ArrayList<>();

        if (response.getBody() != null) {
            captions = response.getBody().getList();
        }

        return captions;

    }
}
