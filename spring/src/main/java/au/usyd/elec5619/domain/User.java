package au.usyd.elec5619.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "User")
public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2485483117804868283L;

    @GeneratedValue
    private long user_id;

    @Id
    private String username;

    @NotNull
    private String salt;

    @NotNull
    private String password ;

    @NotNull
    private Boolean freeze;

    private String email;

    @NotNull
    @Column(name = "login_status")
    private Boolean loginStatus;

    @ManyToMany(mappedBy = "favouritedBy")
    private List<Article> favouriteArticles = new ArrayList<Article>();

    @ManyToMany(mappedBy = "viewedBy")
    private List<Article> history = new ArrayList<Article>();

    public User() { }
    public User(String username, String salt, String password,String email){
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.freeze = false;
        this.email = email;
        this.loginStatus = false;
    }

    public List<Article> getFavouriteArticle() {
        return favouriteArticles;
    }

    public void setFavouriteArticle(List<Article> favouriteArticles) {
        this.favouriteArticles = favouriteArticles;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Boolean getFreeze() {
        return freeze;
    }

    public void setFreeze(Boolean freeze) {
        this.freeze = freeze;
    }


    public long getId() {
        return user_id;
    }

    public void setId(long id) {
        this.user_id = id;
    }

    public List<Article> getViewedArticles() {
        return history;
    }

    public void setViewedArticles(List<Article> history) {
        this.history = history;
    }

}