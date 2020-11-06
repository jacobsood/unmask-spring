package au.usyd.elec5619.domain.fontmodel;
/*
id     size_name
1           small
2           medium
3           large
 */
public class FontSize {
    //variables
    private String id;
    private String name;

    //constructor
    public FontSize(){
    }

    public FontSize(String id, String name){
        this.id = id;
        this.name = name;
    }

    //Getter and Setter for font size
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
