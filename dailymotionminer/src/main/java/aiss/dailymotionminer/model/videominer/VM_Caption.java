package aiss.dailymotionminer.model.videominer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VM_Caption {

    @JsonProperty("id")
    private String id;

    @JsonProperty("link")
    private String link;

    @JsonProperty("language")
    private String language;

    public VM_Caption(String id, String link, String language) {
        this.id = id;
        this.link = link;
        this.language = language;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getLink() { return link; }

    public void setLink(String link) { this.link = link; }

    public String getLanguage() { return language; }

    public void setLanguage(String language) { this.language = language; }

    @Override
    public String toString() {
        return "VM_Caption{" +
                "id='" + id + '\'' +
                ", link='" + link + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
