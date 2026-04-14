
package aiss.peertubeminer.model.peertube;

import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "uuid",
    "shortUUID",
    "isLive",
    "liveSchedules",
    "createdAt",
    "publishedAt",
    "updatedAt",
    "originallyPublishedAt",
    "category",
    "licence",
    "language",
    "privacy",
    "truncatedDescription",
    "duration",
    "aspectRatio",
    "isLocal",
    "name",
    "thumbnailPath",
    "previewPath",
    "thumbnails",
    "embedPath",
    "views",
    "likes",
    "dislikes",
    "comments",
    "nsfw",
    "nsfwFlags",
    "nsfwSummary",
    "waitTranscoding",
    "state",
    "scheduledUpdate",
    "blacklisted",
    "blacklistedReason",
    "account",
    "channel",
    "userHistory"
})
@Generated("jsonschema2pojo")
public class Comment {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("shortUUID")
    private String shortUUID;
    @JsonProperty("isLive")
    private Boolean isLive;
    @JsonProperty("liveSchedules")
    private List<LiveSchedule> liveSchedules;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("publishedAt")
    private String publishedAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("originallyPublishedAt")
    private String originallyPublishedAt;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("licence")
    private Licence licence;
    @JsonProperty("language")
    private Language language;
    @JsonProperty("privacy")
    private Privacy privacy;
    @JsonProperty("truncatedDescription")
    private String truncatedDescription;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("aspectRatio")
    private Double aspectRatio;
    @JsonProperty("isLocal")
    private Boolean isLocal;
    @JsonProperty("name")
    private String name;
    @JsonProperty("thumbnailPath")
    private String thumbnailPath;
    @JsonProperty("previewPath")
    private String previewPath;
    @JsonProperty("thumbnails")
    private List<Thumbnail> thumbnails;
    @JsonProperty("embedPath")
    private String embedPath;
    @JsonProperty("views")
    private Integer views;
    @JsonProperty("likes")
    private Integer likes;
    @JsonProperty("dislikes")
    private Integer dislikes;
    @JsonProperty("comments")
    private Integer comments;
    @JsonProperty("nsfw")
    private Boolean nsfw;
    @JsonProperty("nsfwFlags")
    private Integer nsfwFlags;
    @JsonProperty("nsfwSummary")
    private String nsfwSummary;
    @JsonProperty("waitTranscoding")
    private Boolean waitTranscoding;
    @JsonProperty("state")
    private State state;
    @JsonProperty("scheduledUpdate")
    private ScheduledUpdate scheduledUpdate;
    @JsonProperty("blacklisted")
    private Boolean blacklisted;
    @JsonProperty("blacklistedReason")
    private String blacklistedReason;
    @JsonProperty("account")
    private User account;
    @JsonProperty("channel")
    private Channel channel;
    @JsonProperty("userHistory")
    private UserHistory userHistory;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("shortUUID")
    public String getShortUUID() {
        return shortUUID;
    }

    @JsonProperty("shortUUID")
    public void setShortUUID(String shortUUID) {
        this.shortUUID = shortUUID;
    }

    @JsonProperty("isLive")
    public Boolean getIsLive() {
        return isLive;
    }

    @JsonProperty("isLive")
    public void setIsLive(Boolean isLive) {
        this.isLive = isLive;
    }

    @JsonProperty("liveSchedules")
    public List<LiveSchedule> getLiveSchedules() {
        return liveSchedules;
    }

    @JsonProperty("liveSchedules")
    public void setLiveSchedules(List<LiveSchedule> liveSchedules) {
        this.liveSchedules = liveSchedules;
    }

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("publishedAt")
    public String getPublishedAt() {
        return publishedAt;
    }

    @JsonProperty("publishedAt")
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("originallyPublishedAt")
    public String getOriginallyPublishedAt() {
        return originallyPublishedAt;
    }

    @JsonProperty("originallyPublishedAt")
    public void setOriginallyPublishedAt(String originallyPublishedAt) {
        this.originallyPublishedAt = originallyPublishedAt;
    }

    @JsonProperty("category")
    public Category getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonProperty("licence")
    public Licence getLicence() {
        return licence;
    }

    @JsonProperty("licence")
    public void setLicence(Licence licence) {
        this.licence = licence;
    }

    @JsonProperty("language")
    public Language getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(Language language) {
        this.language = language;
    }

    @JsonProperty("privacy")
    public Privacy getPrivacy() {
        return privacy;
    }

    @JsonProperty("privacy")
    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    @JsonProperty("truncatedDescription")
    public String getTruncatedDescription() {
        return truncatedDescription;
    }

    @JsonProperty("truncatedDescription")
    public void setTruncatedDescription(String truncatedDescription) {
        this.truncatedDescription = truncatedDescription;
    }

    @JsonProperty("duration")
    public Integer getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @JsonProperty("aspectRatio")
    public Double getAspectRatio() {
        return aspectRatio;
    }

    @JsonProperty("aspectRatio")
    public void setAspectRatio(Double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    @JsonProperty("isLocal")
    public Boolean getIsLocal() {
        return isLocal;
    }

    @JsonProperty("isLocal")
    public void setIsLocal(Boolean isLocal) {
        this.isLocal = isLocal;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("thumbnailPath")
    public String getThumbnailPath() {
        return thumbnailPath;
    }

    @JsonProperty("thumbnailPath")
    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    @JsonProperty("previewPath")
    public String getPreviewPath() {
        return previewPath;
    }

    @JsonProperty("previewPath")
    public void setPreviewPath(String previewPath) {
        this.previewPath = previewPath;
    }

    @JsonProperty("thumbnails")
    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    @JsonProperty("thumbnails")
    public void setThumbnails(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    @JsonProperty("embedPath")
    public String getEmbedPath() {
        return embedPath;
    }

    @JsonProperty("embedPath")
    public void setEmbedPath(String embedPath) {
        this.embedPath = embedPath;
    }

    @JsonProperty("views")
    public Integer getViews() {
        return views;
    }

    @JsonProperty("views")
    public void setViews(Integer views) {
        this.views = views;
    }

    @JsonProperty("likes")
    public Integer getLikes() {
        return likes;
    }

    @JsonProperty("likes")
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @JsonProperty("dislikes")
    public Integer getDislikes() {
        return dislikes;
    }

    @JsonProperty("dislikes")
    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    @JsonProperty("comments")
    public Integer getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    @JsonProperty("nsfw")
    public Boolean getNsfw() {
        return nsfw;
    }

    @JsonProperty("nsfw")
    public void setNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    @JsonProperty("nsfwFlags")
    public Integer getNsfwFlags() {
        return nsfwFlags;
    }

    @JsonProperty("nsfwFlags")
    public void setNsfwFlags(Integer nsfwFlags) {
        this.nsfwFlags = nsfwFlags;
    }

    @JsonProperty("nsfwSummary")
    public String getNsfwSummary() {
        return nsfwSummary;
    }

    @JsonProperty("nsfwSummary")
    public void setNsfwSummary(String nsfwSummary) {
        this.nsfwSummary = nsfwSummary;
    }

    @JsonProperty("waitTranscoding")
    public Boolean getWaitTranscoding() {
        return waitTranscoding;
    }

    @JsonProperty("waitTranscoding")
    public void setWaitTranscoding(Boolean waitTranscoding) {
        this.waitTranscoding = waitTranscoding;
    }

    @JsonProperty("state")
    public State getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(State state) {
        this.state = state;
    }

    @JsonProperty("scheduledUpdate")
    public ScheduledUpdate getScheduledUpdate() {
        return scheduledUpdate;
    }

    @JsonProperty("scheduledUpdate")
    public void setScheduledUpdate(ScheduledUpdate scheduledUpdate) {
        this.scheduledUpdate = scheduledUpdate;
    }

    @JsonProperty("blacklisted")
    public Boolean getBlacklisted() {
        return blacklisted;
    }

    @JsonProperty("blacklisted")
    public void setBlacklisted(Boolean blacklisted) {
        this.blacklisted = blacklisted;
    }

    @JsonProperty("blacklistedReason")
    public String getBlacklistedReason() {
        return blacklistedReason;
    }

    @JsonProperty("blacklistedReason")
    public void setBlacklistedReason(String blacklistedReason) {
        this.blacklistedReason = blacklistedReason;
    }

    @JsonProperty("account")
    public User getAccount() {
        return account;
    }

    @JsonProperty("account")
    public void setAccount(User account) {
        this.account = account;
    }

    @JsonProperty("channel")
    public Channel getChannel() {
        return channel;
    }

    @JsonProperty("channel")
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @JsonProperty("userHistory")
    public UserHistory getUserHistory() {
        return userHistory;
    }

    @JsonProperty("userHistory")
    public void setUserHistory(UserHistory userHistory) {
        this.userHistory = userHistory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Comment.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("uuid");
        sb.append('=');
        sb.append(((this.uuid == null)?"<null>":this.uuid));
        sb.append(',');
        sb.append("shortUUID");
        sb.append('=');
        sb.append(((this.shortUUID == null)?"<null>":this.shortUUID));
        sb.append(',');
        sb.append("isLive");
        sb.append('=');
        sb.append(((this.isLive == null)?"<null>":this.isLive));
        sb.append(',');
        sb.append("liveSchedules");
        sb.append('=');
        sb.append(((this.liveSchedules == null)?"<null>":this.liveSchedules));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("publishedAt");
        sb.append('=');
        sb.append(((this.publishedAt == null)?"<null>":this.publishedAt));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
        sb.append(',');
        sb.append("originallyPublishedAt");
        sb.append('=');
        sb.append(((this.originallyPublishedAt == null)?"<null>":this.originallyPublishedAt));
        sb.append(',');
        sb.append("category");
        sb.append('=');
        sb.append(((this.category == null)?"<null>":this.category));
        sb.append(',');
        sb.append("licence");
        sb.append('=');
        sb.append(((this.licence == null)?"<null>":this.licence));
        sb.append(',');
        sb.append("language");
        sb.append('=');
        sb.append(((this.language == null)?"<null>":this.language));
        sb.append(',');
        sb.append("privacy");
        sb.append('=');
        sb.append(((this.privacy == null)?"<null>":this.privacy));
        sb.append(',');
        sb.append("truncatedDescription");
        sb.append('=');
        sb.append(((this.truncatedDescription == null)?"<null>":this.truncatedDescription));
        sb.append(',');
        sb.append("duration");
        sb.append('=');
        sb.append(((this.duration == null)?"<null>":this.duration));
        sb.append(',');
        sb.append("aspectRatio");
        sb.append('=');
        sb.append(((this.aspectRatio == null)?"<null>":this.aspectRatio));
        sb.append(',');
        sb.append("isLocal");
        sb.append('=');
        sb.append(((this.isLocal == null)?"<null>":this.isLocal));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("thumbnailPath");
        sb.append('=');
        sb.append(((this.thumbnailPath == null)?"<null>":this.thumbnailPath));
        sb.append(',');
        sb.append("previewPath");
        sb.append('=');
        sb.append(((this.previewPath == null)?"<null>":this.previewPath));
        sb.append(',');
        sb.append("thumbnails");
        sb.append('=');
        sb.append(((this.thumbnails == null)?"<null>":this.thumbnails));
        sb.append(',');
        sb.append("embedPath");
        sb.append('=');
        sb.append(((this.embedPath == null)?"<null>":this.embedPath));
        sb.append(',');
        sb.append("views");
        sb.append('=');
        sb.append(((this.views == null)?"<null>":this.views));
        sb.append(',');
        sb.append("likes");
        sb.append('=');
        sb.append(((this.likes == null)?"<null>":this.likes));
        sb.append(',');
        sb.append("dislikes");
        sb.append('=');
        sb.append(((this.dislikes == null)?"<null>":this.dislikes));
        sb.append(',');
        sb.append("comments");
        sb.append('=');
        sb.append(((this.comments == null)?"<null>":this.comments));
        sb.append(',');
        sb.append("nsfw");
        sb.append('=');
        sb.append(((this.nsfw == null)?"<null>":this.nsfw));
        sb.append(',');
        sb.append("nsfwFlags");
        sb.append('=');
        sb.append(((this.nsfwFlags == null)?"<null>":this.nsfwFlags));
        sb.append(',');
        sb.append("nsfwSummary");
        sb.append('=');
        sb.append(((this.nsfwSummary == null)?"<null>":this.nsfwSummary));
        sb.append(',');
        sb.append("waitTranscoding");
        sb.append('=');
        sb.append(((this.waitTranscoding == null)?"<null>":this.waitTranscoding));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null)?"<null>":this.state));
        sb.append(',');
        sb.append("scheduledUpdate");
        sb.append('=');
        sb.append(((this.scheduledUpdate == null)?"<null>":this.scheduledUpdate));
        sb.append(',');
        sb.append("blacklisted");
        sb.append('=');
        sb.append(((this.blacklisted == null)?"<null>":this.blacklisted));
        sb.append(',');
        sb.append("blacklistedReason");
        sb.append('=');
        sb.append(((this.blacklistedReason == null)?"<null>":this.blacklistedReason));
        sb.append(',');
        sb.append("account");
        sb.append('=');
        sb.append(((this.account == null)?"<null>":this.account));
        sb.append(',');
        sb.append("channel");
        sb.append('=');
        sb.append(((this.channel == null)?"<null>":this.channel));
        sb.append(',');
        sb.append("userHistory");
        sb.append('=');
        sb.append(((this.userHistory == null)?"<null>":this.userHistory));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
