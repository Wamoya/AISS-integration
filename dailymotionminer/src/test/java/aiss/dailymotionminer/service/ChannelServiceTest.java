package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.dailymotion.Channel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChannelServiceTest {

    @Autowired
    ChannelService channelService;

    @Test
    void testGetChannelFull() {

        String channelName = "redbull";
        Integer maxVideos = 2;
        Integer maxComments = 2;

        System.out.println("Searching for channel: " + channelName);
        Channel downloadedChannel = channelService.getChannelWithVideos(channelName, maxVideos, maxComments);


        assertNotNull(downloadedChannel, "Downloaded channel should not be null");
        assertEquals("Red Bull", downloadedChannel.getScreenname(), "Channel screen name should be Red Bull");
        assertNotNull(downloadedChannel.getVideos(), "Video list should not be null");
        assertFalse(downloadedChannel.getVideos().isEmpty(), "Video list should contain at least one video");
        assertTrue(downloadedChannel.getVideos().size() <= maxVideos, "Should not fetch more videos than the max allowed");
    }

    @Test
    void testGetChannelBasic() {

        String channelName = "redbull";

        System.out.println("Retrieving basic channel data for: " + channelName);
        Channel channel = channelService.getChannel(channelName);


        assertNotNull(channel, "Channel should not be null");
        assertEquals("Red Bull", channel.getScreenname(), "Channel screen name should be 'Red Bull'");
        assertNotNull(channel.getId(), "Channel id should not be null");
        assertNotNull(channel.getUrl(), "Channel URL should not be null");
        assertNotNull(channel.getAvatar240url(), "Channel avatar URL should not be null");
    }
}