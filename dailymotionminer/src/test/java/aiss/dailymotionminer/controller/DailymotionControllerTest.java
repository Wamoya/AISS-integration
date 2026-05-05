package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.model.videominer.VM_Channel;
import aiss.dailymotionminer.service.VideoMinerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DailymotionControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    VideoMinerService videoMinerService;

    @Test
    void testGetChannel() {
        String channelName = "redbull";
        String url = "/api/dailymotionminer/v1/" + channelName + "?maxVideos=2&maxComments=2";

        ResponseEntity<VM_Channel> response = restTemplate.getForEntity(url, VM_Channel.class);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "The status code should be 200 OK");

        VM_Channel fetchedChannel = response.getBody();

        assertNotNull(fetchedChannel, "The response body should not be null");
        assertEquals("Red Bull", fetchedChannel.getName(), "The translated channel name should match");
        assertNotNull(fetchedChannel.getVideos(), "The translated channel should have a list of videos");
        assertFalse(fetchedChannel.getVideos().isEmpty(), "The video list should not be empty after translation");
    }

    @Test
    void testPostChannel() {
        Mockito.when(videoMinerService.postChannel(any(VM_Channel.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        String channelName = "redbull";
        String url = "/api/dailymotionminer/v1/" + channelName + "?maxVideos=2&maxComments=2";

        ResponseEntity<VM_Channel> response = restTemplate.postForEntity(url, null, VM_Channel.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "The status code should be 201 CREATED");

        VM_Channel savedChannel = response.getBody();

        assertNotNull(savedChannel, "The response body should not be null");
        assertEquals("Red Bull", savedChannel.getName(), "The translated channel name should match");
        assertNotNull(savedChannel.getVideos(), "The translated channel should have a list of videos");
        assertFalse(savedChannel.getVideos().isEmpty(), "The video list should not be empty after translation");
    }
}