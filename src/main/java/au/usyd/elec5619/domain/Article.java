package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "Article")
public class Article implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4688762465122251377L;
  
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String title;

    private String source;

    @NotNull
    private boolean created_by_admin;

    @NotNull
    private String text;

    private String country;

    @ManyToMany
    @JoinTable(
        name = "article_tag",
        joinColumns = @JoinColumn(name = "article_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @ManyToMany
    @JoinColumn(
            name = "article_user"
            //joinColumns = @JoinColumn(name = "article_id"),
            //inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> likes;

    @ManyToMany
    @JoinColumn(
            name = "article_history"
            //joinColumns = @JoinColumn(name = "article_id"),
            //inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> wasRead;


    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public long getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    public boolean getCreatedByAdmin() {
        return created_by_admin;
    }
  
    public String getSource() {
        return source;
    }

    public String getText() {
        return text;
    }

    public String getCountry() {
        return country;
    }

    public void setId(long id) {
        this.id = id;
    } 

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedByAdmin(boolean createdByAdmin) {
        this.created_by_admin = createdByAdmin;
    }
  
    public void setSource(String source) {
        this.source = source;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<User> getWasRead() {
        return wasRead;
    }

    public void setWasRead(List<User> wasRead) {
        this.wasRead = wasRead;
    }
}