package au.usyd.elec5619.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "Comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private long commentID;

    @Column(name="article_id")
    private long articleID;

    @Column(name="author_id")
    private long authorID;

    @Column(name="comment_content")
    private String commentContent;

    @Column(name="up_vote_count")
    private int upVoteCount;

    public long getCommentID() {
        return commentID;
    }
    public void setCommentID(long commentID) {
        this.commentID = commentID;
    }

    public long getArticleID() {
        return articleID;
    }
    public void setArticleID(long articleID) {
        this.articleID = articleID;
    }

    public long getAuthorID() {
        return authorID;
    }
    public void setAuthorID(long authorID) {
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
