package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.User;

import java.util.List;

public interface UserManager {
    // Check

    public User getUserByUsername(String username);

    public String getUserSaltByUsername(String username);


}
