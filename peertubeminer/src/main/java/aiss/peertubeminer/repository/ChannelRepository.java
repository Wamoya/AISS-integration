package aiss.peertubeminer.repository;

import aiss.peertubeminer.model.peertube.Channel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChannelRepository {
    List<Channel> channels = new ArrayList<>();

    public ChannelRepository() {
        this.channels.add(
                new Channel(
                        1,
                        "Dummy channel 1",
                        "Hello world!",
                        "sometime"
                )
        );
        this.channels.add(
                new Channel(
                        2,
                        "Different video channel",
                        "Different video channel's description",
                        "1492"
                )
        );
        this.channels.add(
                new Channel(
                        3,
                        "Java enjoyer",
                        "not really.",
                        "at some point"
                )
        );
    }

    public List<Channel> findAll() { return this.channels; }

    public Channel findOneById(Integer id) {
        return this.channels.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
