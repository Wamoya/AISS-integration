package aiss.dailymotionminer.model.dailymotion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

    @JsonProperty("id")
    private String id;

    @JsonProperty("text")
    private String text;

    @JsonProperty("createdOn")
    private String createdOn;

//    @JsonProperty("videoId") // Used for relating videos to comments
//    private Integer videoId;

    public Comment(String id, String text, String createdOn) {
        this.id = id;
        this.text = text;
        this.createdOn = createdOn;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("text")
    public String getText() { return text; }

    @JsonProperty("text")
    public void setText(String text) { this.text = text; }

    @JsonProperty("createdOn")
    public String getCreatedOn() {
        return createdOn;
    }

    @JsonProperty("createdOn")
    public void setCreatedAt(String createdOn) {
        this.createdOn = createdOn;
    }

    // Utility method for converting a list of Dailymotion tags into a list of Comment objects

    public static List<Comment> getCommentsFromTags(List<String> tags, String videoReleaseTime) {
        return tags.stream()
                .map(tag -> new Comment(
                        UUID.randomUUID().toString(),
                        tag,
                        videoReleaseTime))
                .collect(Collectors.toList());
    }


}
