package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Tag")
public class Tag implements Serializable {

    @Id
    private String tag;

    @ManyToMany
    List<Article> articles;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    } 
}
