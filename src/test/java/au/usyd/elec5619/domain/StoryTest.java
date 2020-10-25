package au.usyd.elec5619.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

class StoryTest {
    private static Story story;

    @BeforeAll
    static void setUp() {
        story = new Story();
    }

    @Test
    void testSetAndGetId() {
        long testId = 23112241;
        story.setId(testId);
        assertEquals(testId, story.getId(), "Story id is not being set correctly");
    }

    @Test
    void testSetAndGetTitle() {
        String testTitle = "Illegal Gold Mining in Peru";
        assertNull(story.getTitle());
        story.setTitle(testTitle);
        assertEquals(testTitle, story.getTitle(), "Story title is not being set correctly");
    }

    @Test
    void testSetAndgetAuthor() {
        String testSource = "Jacob Sood";
        assertNull(story.getAuthor());
        story.setAuthor(testSource);
        assertEquals(testSource, story.getAuthor(), "Story author is not being set correctly");
    }

    @Test
    void testSetAndGetText() {
        String testText = "Illegal mining is a complex issue as Peru tries to balance natural resource " +
            "management and the health of local indigenous communities, with its continued growth and development goals.";
        assertNull(story.getText());
        story.setText(testText);
        assertEquals(testText, story.getText(), "Story text is not being set correctly");
    }

    @Test 
    void testSetAndGetAudioUrl() {
        String testAudioUrl = "https://hrithviksood.s3-ap-southeast-2.amazonaws.com/unmask/audio/articles/sample1.mp3";
        assertNull(story.getAudioUrl());
        story.setAudioUrl(testAudioUrl);
        assertEquals(testAudioUrl, story.getAudioUrl(), "Story audio url is not being set correctly");
    }

    @Test
    void testSetAndGetCountry() {
        Country country = new Country();
        country.setId("Egypt");
        assertNull(story.getCountry());
        story.setCountry(country);
        assertEquals(country, story.getCountry(), "Country is not being set correctly");
    }
}