package aiss.peertubeminer.model.videominer;

import java.util.List;

public class VM_Video {

    private String id;
    private String name;
    private String description;
    private String releaseTime;
    private VM_User user;
    private List<VM_Comment> comments;
    private List<VM_Caption> captions;

    public VM_Video(String id, String name, String description, String releaseTime, VM_User user, List<VM_Comment> comments, List<VM_Caption> captions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseTime = releaseTime;
        this.user = user;
        this.comments = comments;
        this.captions = captions;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseTime() {
        return releaseTime;
    }
    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public VM_User getUser() { return user; }
    public void SetUser(VM_User user) { this.user = user; }

    public List<VM_Comment> getComments() {
        return comments;
    }
    public void setComments(List<VM_Comment> comments) {
        this.comments = comments;
    }

    public List<VM_Caption> getCaptions() {
        return captions;
    }
    public void setCaptions(List<VM_Caption> captions) {
        this.captions = captions;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", comments=" + comments +
                ", captions=" + captions +
                '}';
    }
}
