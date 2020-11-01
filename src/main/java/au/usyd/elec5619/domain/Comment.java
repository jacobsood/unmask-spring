package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "Comment")
public class Comment implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7977502097515134800L;

    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private long comment_id;

    @Column(name="user_id")
    private long user_id;

    private String text;

    @Column(name="up_vote_count")
    private int upVoteCount;

    public long getCommentID() {
        return comment_id;
    }
    public void setCommentID(long comment_id) {
        this.comment_id = comment_id;
    }

    public long getArticleID() {
        return article_id;
    }
    public void setArticleID(long article_id) {
        this.article_id = article_id;
    }

    public long getAuthorID() {
        return user_id;
    }
    public void setAuthorID(long user_id) {
        this.user_id = user_id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public int getUpVoteCount() {
        return upVoteCount;
    }
    public void setUpVoteCount(int upVoteCount) {
        this.upVoteCount = upVoteCount;
    }
}
