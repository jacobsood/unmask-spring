package au.usyd.elec5619.domain;

public class Article {
    private String title;
    private String source;
    private String text;
    private String audioUrl;
    
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