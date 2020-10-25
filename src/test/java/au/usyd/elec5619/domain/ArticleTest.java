package au.usyd.elec5619.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {
    private static Article article;

    @BeforeAll
    static void setUp() {
        article = new Article();
    }

    @Test
    void testSetAndGetTitle() {
        String testTitle = "Illegal Gold Mining in Peru";
        assertNull(article.getTitle());
        article.setTitle(testTitle);
        assertEquals(testTitle, article.getTitle(), "Article title is not being set correctly");
    }

    @Test
    void testSetAndGetSource() {
        String testSource = "SBS";
        assertNull(article.getSource());
        article.setSource(testSource);
        assertEquals(testSource, article.getSource(), "Article source is not being set correctly");
    }

    @Test
    void testSetAndGetText() {
        String testText = "Illegal mining is a complex issue as Peru tries to balance natural resource " +
            "management and the health of local indigenous communities, with its continued growth and development goals.";
        assertNull(article.getText());
        article.setText(testText);
        assertEquals(testText, article.getText(), "Article text is not being set correctly");
    }

    @Test 
    void testSetAndGetAudioUrl() {
        String testAudioUrl = "https://hrithviksood.s3-ap-southeast-2.amazonaws.com/unmask/audio/articles/sample1.mp3";
        assertNull(article.getAudioUrl());
        article.setAudioUrl(testAudioUrl);
        assertEquals(testAudioUrl, article.getAudioUrl(), "Article audio url is not being set correctly");
    }
}