package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Channel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ChannelServiceTest {

    @Autowired
    ChannelService channelService;

    String channelHandle = "transport_evolved_take_2";

    @Test
    @DisplayName("Get basic information of a channel")
    void getChannel() {
        Channel channel = channelService.getChannel(channelHandle);
        assertNotNull(channel, "The channel cannot be null");
        System.out.println(channel);
    }

    @Test
    @DisplayName("Get complete information of a channel (including videos)")
    void getChannelWithVideos() {
        Channel channel = channelService.getChannelWithVideos(channelHandle, 5, 5);
        assertNotNull(channel, "The channel cannot be null");
        System.out.println(channel);
    }
}
