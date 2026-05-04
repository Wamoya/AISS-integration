package aiss.videominer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("text")
    @Column(columnDefinition="TEXT")
    private String text;

    @JsonProperty("createdOn")
    private String createdOn;

    public Comment() {
    }

    public Comment(String text, String createdOn) {
        this.text = text;
        this.createdOn = createdOn;
    }

    // Possible future extension point for the data model. (See comments in ./Video.java)
//    @JsonProperty("author")
//    @ManyToOne(cascade = CascadeType.ALL) // One author <-> Many comments
//    @NotNull(message = "Comment author cannot be null")
//    private User author;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
