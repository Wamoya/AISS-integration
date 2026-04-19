package aiss.peertubeminer.model.videominer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;

public class VM_Caption {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("language")
    private String language;

    @JsonProperty("createdAt")
    private String createdAt;

    public VM_Caption(String id, String name, String language, String createdAt) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.createdAt = createdAt;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) { this.language = language; }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Caption{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
