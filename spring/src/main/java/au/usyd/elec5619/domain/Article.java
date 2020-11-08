package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @NotNull
    private String source;

    @NotNull
    @Column(name = "is_story")
    private boolean isStory;

    @NotNull
    private String text;

    @NotNull
    private String country;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
        name = "article_tag",
        joinColumns = @JoinColumn(name = "article_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "UserFavourite",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> favouritedBy = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }) // to avoid no session error
    @JoinTable(
            name = "UserHistory",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")

    )
    private Set<User> viewedBy = new HashSet<>();

    public long getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    public boolean getIsStory() {
        return isStory;
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

    public Set<User> getUsersViewedBy() {
        return  viewedBy;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setId(long id) {
        this.id = id;
    } 

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsStory(boolean isStory) {
        this.isStory = isStory;
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

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setUsersViewedBy(Set<User> viewedBy) {
        this.viewedBy = viewedBy;
    }


    public Set<User> getFavouritedBy() {
        return  favouritedBy;
    }

    public void setFavouritedBy(Set<User> favouritedBy) {
        this.favouritedBy = favouritedBy;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Article) {
            Article article = (Article) obj;
            if(this.id == article.id) {
                return true;
            }
        }
        return false;
    }


}