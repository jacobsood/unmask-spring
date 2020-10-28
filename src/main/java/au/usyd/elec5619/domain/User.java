/* 

    Define what's being stored. Look at Article.java, Country.java or Tag.java for assistance

    Relationship:

    1. Many-To-Many relationship with Articles and stories favourties list (implement in StoryFavourite.java and ArticleFavourite.java) (Refer to #4 in the link below)
        
    2. Many-To-Many relationship with articles and stories (History list) (implement here) (reference to #2 in the link below or in Article.java and Tag.java)
    
    3. Include the user's own posted stories 

    https://www.baeldung.com/jpa-many-to-many

    Ensure tests are also being written for all methods

*/
package au.usyd.elec5619.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class User {

    public User() {}
}