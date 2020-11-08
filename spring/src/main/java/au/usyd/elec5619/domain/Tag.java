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
    private String id;

    @ManyToMany(mappedBy = "tags")
    private Set<Article> articles;

    public String getTag() {
        return id;
    }

    public void setTag(String id) {
        this.id = id;
    } 
}
