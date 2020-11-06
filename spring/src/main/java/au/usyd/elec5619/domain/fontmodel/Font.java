package au.usyd.elec5619.domain.fontmodel;
/* use google fonts api
* in our case:
* font families including
* font_id   font_name
* 1         Anton
* 2         Noto_Sans
* 3         Playfair_Display
* 4         Roboto
* 5         Syne_Mono
* */

public class Font {
    //variables
    private String id;
    private String name;

    //constructor
    public Font(){
    }

    public Font(String id, String name){
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
    }}
