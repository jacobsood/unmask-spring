package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Country")
public class Country implements Serializable {

    @Id
    @Column(name = "country")
    @OneToMany(mappedBy = "country")
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}