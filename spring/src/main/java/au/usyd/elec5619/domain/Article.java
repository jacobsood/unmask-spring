package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
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
    private long article_id;

    @NotNull
    private String title;

    private String source;

    @NotNull
    @Column(name = "created_by_admin")
    private boolean createdByAdmin = true;

    @NotNull
    private String text;

    private String country;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    private List<Comment> comments = new ArrayList<Comment>();

    @ManyToMany
    @JoinTable(
        name = "ArticleTag",
        joinColumns = @JoinColumn(name = "tag_id"),
        inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Tag> tags = new ArrayList<Tag>();

    @ManyToMany
    @JoinTable(
        name = "UserFavourite",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<User> favouritedBy = new ArrayList<User>();

    @ManyToMany
    @JoinTable(
        name = "UserHistory",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<User> viewedBy = new ArrayList<User>();


    public List<User> getUsersFavouritedBy() {
        return favouritedBy;
    }

    public long getId() {
        return article_id;
    }
    
    public String getTitle() {
        return title;
    }

    public boolean getCreatedByAdmin() {
        return createdByAdmin;
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

    public List<User> getUsersViewedBy() {
        return viewedBy;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setId(long id) {
        this.article_id = id;
    } 

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedByAdmin(boolean createdByAdmin) {
        this.createdByAdmin = createdByAdmin;
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

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setUsersViewedBy(List<User> viewedBy) {
        this.viewedBy = viewedBy;
    }

    public void setUsersFavouritedBy(List<User> favouritedBy) {
        this.favouritedBy = favouritedBy;
    }
    
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}