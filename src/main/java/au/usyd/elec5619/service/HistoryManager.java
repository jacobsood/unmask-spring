package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.User;

import java.util.List;

public interface HistoryManager {
    // ADD
    public void addHistoryByUser(User user, Article article);

    // GET
    public List<Article> getHistoryByUserID(String userID);

    // DEL
    public void delHistoryByUser(User user, Article article);

}
