package au.usyd.elec5619.service;
import java.util.List;
import au.usyd.elec5619.domain.Login;
public interface LoginManager {


    // Check

    public boolean check(Login login);

    public Boolean loginStatusByUsername(String username);

    // Logout
    public void setLoginStatus(String username);
}
