package au.usyd.elec5619.service.Idatabase;

import au.usyd.elec5619.domain.Comment;

import java.io.Serializable;
import java.util.List;

public interface CommentManager extends Serializable {

    public void addComment(Comment comment);

    public Comment getCommentById(long commentID);

    public List<Comment> getCommentsByArticle(long articleID);

    public List<Comment> getCommentsByAuthor(long authorID);

    public void updateComment(Comment comment);

    public void deleteComment(long commentID);

}
