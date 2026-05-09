package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Caption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CaptionServiceTest {

    @Autowired
    CaptionService captionService;

    @Test
    @DisplayName("Get captions from a video")
    void getCaptions() {
        List<Caption> captions = captionService.getCaptionsFromVideo("wsa7yQiUSVnw9HTwba1KiN");
        assertFalse(captions.isEmpty(), "The list of captions cannot be empty");
        System.out.println(captions);
    }
}
