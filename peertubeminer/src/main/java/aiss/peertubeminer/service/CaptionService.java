package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.*;
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

    String BASE_URI = "https://peertube.tv/api/v1";

    public List<Caption> getCaptionsFromVideo(Video video) {
        String uri = BASE_URI + "/videos/" + video.getId() + "/captions";
        ResponseEntity<Caption_Data> response = restTemplate.getForEntity(uri, Caption_Data.class);

        List<Caption> captions = new ArrayList<>();

        if (response.getBody() != null) {
            captions = response.getBody().getData();
        }

        return captions;
    }
}
