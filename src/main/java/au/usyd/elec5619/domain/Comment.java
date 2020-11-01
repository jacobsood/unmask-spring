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
    private long commentId;

    @ManyToOne
    @JoinColumn(name = "article_id")
    @Column(name="article_id")
    private long articleId;

    @Column(name="author_id")
    private long authorId;

    @Column(name="comment_content")
    private String commentContent;

    @Column(name="up_vote_count")
    private int upVoteCount;

    public long getCommentID() {
        return commentId;
    }
    public void setCommentID(long commentId) {
        this.commentId = commentId;
    }

    public long getArticleID() {
        return articleId;
    }
    public void setArticleID(long articleId) {
        this.articleId = articleId;
    }

    public long getAuthorID() {
        return authorId;
    }
    public void setAuthorID(long authorId) {
        this.authorId = authorId;
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
