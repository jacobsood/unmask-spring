package au.usyd.elec5619.domain;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false,unique = true)
    private long id;


    @Column(nullable = false,unique = true)
    private String username;

    @NotNull
    private String salt;

    @NotNull
    private String password ;


    private String email;


    @ManyToMany(mappedBy = "favouritedBy",fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    private Set<Article> favouriteArticles = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "viewedBy",fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    private Set<Article> history = new LinkedHashSet<>();



    public User(){

    }
    public User(String username, String password,String email){
        super();
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public User(String username, String salt, String password,String email){
        super();
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.email = email;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Article> getFavouriteArticles() {
        return  favouriteArticles;
    }

    public void setFavouriteArticles(Set<Article> favouriteArticles) {
        this.favouriteArticles = favouriteArticles;
    }

    public Set<Article> getHistory() {
        return  history;
    }

    public void setHistory(Set<Article> history) {
        this.history = history;
    }

    public Set<Article> getHistory(int maxNum) {
        int length = history.size();
        if (length<maxNum){
            return  history;
        }else{
            List<Article> linkedHashSetList = new ArrayList<Article>(history);
            return (LinkedHashSet<Article>) linkedHashSetList.subList(length-maxNum,length);
        }
    }

    public Set<Article> getFavouriteArticles(int maxNum) {
        int length = favouriteArticles.size();
        if (length<maxNum){
            return  favouriteArticles;
        }else{
            List<Article> linkedHashSetList = new ArrayList<Article>(favouriteArticles);
            return (LinkedHashSet<Article>) linkedHashSetList.subList(length-maxNum,length);
        }
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        //return name.hashCode();
        return (int) id;
    }
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(obj instanceof User) {
            User user = (User) obj;
            if(this.id == user.id) {
                return true;
            }
        }
        return false;
    }
}