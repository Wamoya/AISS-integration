
package aiss.dailymotionminer.model.dailymotion;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "screenname", // -> name
    "description", // -> description
    "created_time" // -> createdTime
})
@Generated("jsonschema2pojo")
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
        sb.append(Channel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("screenname");
        sb.append('=');
        sb.append(((this.screenname == null)?"<null>":this.screenname));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("createdTime");
        sb.append('=');
        sb.append(((this.createdTime == null)?"<null>":this.createdTime));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');

        // --- AÑADIDO: avatar_240_url ---
        sb.append("avatar_240_url");
        sb.append('=');
        sb.append(((this.avatar240url == null)?"<null>":this.avatar240url));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }


}
