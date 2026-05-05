
package aiss.dailymotionminer.model.dailymotion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "screenname", // -> name
    "description",
    "created_time", // -> createdTime
    "videos",
    "url", // -> user_link for User
    "avatar_240_url" // -> picture_link for User
})
public class Channel {

    @JsonProperty("id")
    private String id;
    @JsonProperty("screenname")
    private String screenname;
    @JsonProperty("description")
    private String description;
    @JsonProperty("created_time")
    private Integer createdTime;
    @JsonProperty("url")
    private String url;
    @JsonProperty("avatar_240_url")
    private String avatar240url;

    private List<Video> videos;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("screenname")
    public String getScreenname() {
        return screenname;
    }

    @JsonProperty("screenname")
    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("created_time")
    public Integer getCreatedTime() {
        return createdTime;
    }

    @JsonProperty("created_time")
    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }
    @JsonProperty("url")
    public String getUrl() { return url;}
    @JsonProperty("avatar_240_url")
    public String getAvatar240url() {return avatar240url;}

    public List<Video> getVideos() { return videos; }
    public void setVideos(List<Video> videos) { this.videos = videos; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Channel.class.getName())
                .append('@')
                .append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');

        sb.append("id=").append(id != null ? id : "<null>").append(", ");
        sb.append("screenname=").append(screenname != null ? screenname : "<null>").append(", ");
        sb.append("description=").append(description != null ? description : "<null>").append(", ");
        sb.append("createdTime=").append(createdTime != null ? createdTime : "<null>").append(", ");
        sb.append("url=").append(url != null ? url : "<null>").append(", ");
        sb.append("avatar240Url=").append(avatar240url != null ? avatar240url : "<null>");
        sb.append(']');

        return sb.toString();
    }

}
