package aiss.peertubeminer.model.videominer;

public class VM_Caption {

    private String id;
    private String link;
    private String language;

    public VM_Caption(String id, String link, String language) {
        this.id = id;
        this.link = link;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) { this.language = language; }

    public String getLink() {
        return link;
    }

    public void setLink(String link) { this.link = link; }

    @Override
    public String toString() {
        return "Caption{" +
                "id='" + id + '\'' +
                ", language='" + language + '\'' +
                ", createdAt='" + link + '\'' +
                '}';
    }
}
