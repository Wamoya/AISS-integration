
package aiss.peertubeminer.model.peertube;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "privacy",
    "updateAt"
})
@Generated("jsonschema2pojo")
public class ScheduledUpdate {

    @JsonProperty("privacy")
    private Integer privacy;
    @JsonProperty("updateAt")
    private String updateAt;

    @JsonProperty("privacy")
    public Integer getPrivacy() {
        return privacy;
    }

    @JsonProperty("privacy")
    public void setPrivacy(Integer privacy) {
        this.privacy = privacy;
    }

    @JsonProperty("updateAt")
    public String getUpdateAt() {
        return updateAt;
    }

    @JsonProperty("updateAt")
    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ScheduledUpdate.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("privacy");
        sb.append('=');
        sb.append(((this.privacy == null)?"<null>":this.privacy));
        sb.append(',');
        sb.append("updateAt");
        sb.append('=');
        sb.append(((this.updateAt == null)?"<null>":this.updateAt));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
