package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "Tag")
public class Tag implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 507208535532864149L;

    @Id
    private long id;

    @NotNull
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private Set<Article> articles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    } 

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
