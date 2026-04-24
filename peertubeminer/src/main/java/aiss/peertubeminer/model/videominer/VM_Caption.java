package aiss.peertubeminer.model.videominer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;

public class VM_Caption {

    private String id;
    private String language;
    private String createdAt;

    public VM_Caption(String id, String language, String createdAt) {
        this.id = id;
        this.language = language;
        this.createdAt = createdAt;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Caption{" +
                "id='" + id + '\'' +
                ", language='" + language + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
