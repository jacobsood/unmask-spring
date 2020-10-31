package au.usyd.elec5619.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/*

    Define what's being stored. Look at Article.java, Country.java or Tag.java for assistance

    Primary key is (User and Article) - many to many

    See how many-to-many is implemented in Article.java (and Tag.java) - with Article being the owner. 
    or at https://www.baeldung.com/jpa-many-to-many

    Ensure tests are also being written for all methods

*/

@Table(name = "ArticleFavourite")
public class ArticleFavourite implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2350611056725644359L;

    // This part is added to User and Article as many-to-many


}
