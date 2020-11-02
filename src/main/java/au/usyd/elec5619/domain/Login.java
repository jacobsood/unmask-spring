package au.usyd.elec5619.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Login {
    @Id
    @GeneratedValue
    private String id;
    private String uname;
    private String upwd;



    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getUpwd() {
        return upwd;
    }
    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }
    public Login(){

    }
    public Login( String uname, String upwd) {
        super();
        this.uname = uname;
        this.upwd = upwd;
    }
    public Login(String id, String uname, String upwd) {
        super();
        this.id = id;
        this.uname = uname;
        this.upwd = upwd;
    }

}