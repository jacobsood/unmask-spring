package au.usyd.elec5619.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

class TagTest {
    private static Tag tag;

    @BeforeAll
    static void setUp() {
        tag = new Tag();
    }

    @Test
    void testSetAndGetTag() {
        String testTag = "Money Laundering";
        assertNull(tag.getTag());
        tag.setTag(testTag);
        assertEquals(testTag, tag.getTag(), "Tag is not being set correctly");
    }
}
