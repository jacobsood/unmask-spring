package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="PreferredAppearance")
public class PreferredAppearance implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2540298648906499220L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String font;

    @NotNull
    private String font_color;

    @NotNull
    private int font_size;

    @NotNull
    private String themes;

    // Id
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; } 

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
}
