
package aiss.peertubeminer.model.peertube;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "currentTime"
})
@Generated("jsonschema2pojo")
public class UserHistory {

    @JsonProperty("currentTime")
    private Integer currentTime;

    @JsonProperty("currentTime")
    public Integer getCurrentTime() {
        return currentTime;
    }

    @JsonProperty("currentTime")
    public void setCurrentTime(Integer currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserHistory.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("currentTime");
        sb.append('=');
        sb.append(((this.currentTime == null)?"<null>":this.currentTime));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
