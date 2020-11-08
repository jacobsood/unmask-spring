package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;
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
    private long id;

    @ManyToOne
    private Article article;

    @NotNull
    @Column(name="author_id")
    private long authorId;

    @NotNull
    private String text;

    @NotNull
    private int votes;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public int getVotes() {
        return votes;
    }
    public void setVotes(int votes) {
        this.votes = votes;
    }
}
