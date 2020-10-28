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
@Table(name="PreferedAppearance")
public class PreferedAppearance {

    @NotNull
    private String font;

    @NotNull
    private String font_color;

    @NotNull
    private int font_size;

    @NotNull
    private String themes;

    @NotNull
    private User user;

    //font
    public String getFont() { return font; }
    public void setFont(String font) { this.font = font; }

    public String getFont_color() {
        return font_color;
    }

    public void setFont_color(String font_color) {
        this.font_color = font_color;
    }

    public int getFont_size() {
        return font_size;
    }

    public void setFont_size(int font_size) {
        this.font_size = font_size;
    }

    //theme
    public String getThemes() { return themes; }
    public void setThemes(String themes) { this.themes = themes; }

    //user
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }


}
