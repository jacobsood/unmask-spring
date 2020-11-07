package au.usyd.elec5619.service.Idatabase;

import au.usyd.elec5619.domain.User;

public interface UserManager {
    // Check

    public User getUserByUsername(String username);

    public String getUserSaltByUsername(String username);

    public boolean checkEmailIsExist(String yourEmail);
    // Set
    public void setNewPassword(User user);


}
