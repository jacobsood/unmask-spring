package au.usyd.elec5619.domain.fontmodel;
/*
id      name
 */
public class FontColor {
    //variables
    private String id;
    private String name;

    //constructor
    public FontColor(){
    }

    public FontColor(String id, String name){
        this.id = id;
        this.name = name;
    }


    //Getter and Setter for font color
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
