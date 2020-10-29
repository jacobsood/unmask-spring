package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "Country")
public class Country implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 933934919789831606L;

    @Id
    @GeneratedValue
    private Long id;

    private String country;

    @OneToMany(mappedBy = "country")
    private List<Article> articles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}