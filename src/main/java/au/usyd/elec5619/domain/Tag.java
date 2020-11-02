package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Tag")
public class Tag implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 507208535532864149L;

    @Id
    private String tag_id;

    @ManyToMany(mappedBy = "tags")
    private List<Article> articles = new ArrayList<Article>();

    public String getId() {
        return tag_id;
    }

    public void setId(String tag_id) {
        this.tag_id = tag_id;
    } 
}
