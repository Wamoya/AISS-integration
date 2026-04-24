package aiss.peertubeminer.model.videominer;

public class VM_Caption {

    private String id;
    private String language;
    private String link;

    public VM_Caption(String id, String language, String link) {
        this.id = id;
        this.language = language;
        this.link = link;
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
