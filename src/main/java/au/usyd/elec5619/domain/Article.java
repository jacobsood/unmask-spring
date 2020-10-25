package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Article")
public class Article implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private long id;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Source")
    private String source;

    @Column(name = "Text")
    private String text;

    @Column(name = "AudioUrl", nullable = false)
    private String audioUrl;

    public long getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getText() {
        return text;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setId(long id) {
        this.id = id;
    } 

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}