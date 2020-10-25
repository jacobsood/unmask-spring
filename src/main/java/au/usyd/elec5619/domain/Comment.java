package au.usyd.elec5619.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;

@Component("comment")
@Entity
@Table(name = "Comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="CommentID")
    private String commentID;

    @Column(name="ArticleID")
    private String articleID;

    @Column(name="AuthorID")
    private String authorID;

    @Column(name="CommentContent")
    private String commentContent;

    @Column(name="UpVoteCount")
    private int upVoteCount;

    public String getCommentID() {
        return commentID;
    }
    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getArticleID() {
        return articleID;
    }
    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getAuthorID() {
        return authorID;
    }
    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getCommentContent() {
        return commentContent;
    }
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getUpVoteCount() {
        return upVoteCount;
    }
    public void setUpVoteCount(int upVoteCount) {
        this.upVoteCount = upVoteCount;
    }
}
