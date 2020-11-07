package au.usyd.elec5619.service.Idatabase;

import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.User;

import java.util.List;
import java.util.Set;

public interface HistoryManager {
    // ADD
    public void addHistoryByUser(User user, Article article);

    // GET
    public Set<Article> getHistoryByUsername(String username, int maxNum);
    public Set<Article> getHistoryByUsername(String username);


    // DEL
    public void delHistoryByUser(User user, Article article);

}
