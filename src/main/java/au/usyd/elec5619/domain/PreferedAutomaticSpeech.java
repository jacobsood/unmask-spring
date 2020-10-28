/* 

    Define what's being stored. Look at Article.java, Country.java or Tag.java for assistance
    - Must include accent, language, talking speed (for amazon polly)
    - include themes (max 3 or 4, might need a Theme entity and have a Many-To-One relationship)
    - include whatever else may be necessary to improve UX

    Ensure tests are also being written for all methods

    Note that, for preference functionality,
    will implement a dropdown box for all variables.

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

@Entity
@Table(name = "PreferedAutomaticSpeech")
public class PreferedAutomaticSpeech {
    /* variables */
    @NotNull
    private String language;

    @NotNull
    private String accent;

    @NotNull
    private String talking_speed;

    @NotNull
    private User user;

    /* getter and setter */
    //language
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    //accent
    public String getAccent() { return accent; }
    public void setAccent(String accent) { this.accent = accent; }
    //talking_speed
    public String getTalking_speed() { return talking_speed; }
    public void setTalking_speed(String talking_speed) { this.talking_speed = talking_speed; }

    //user
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}