package au.usyd.elec5619.domain;

import static org.junit.jupiter.api.Assertions.*;

public class CommentTest{

    private Comment comment;

    protected void setUp() throws Exception {
        comment = new Comment();
    }

    public void testSetAndGetCommentId(){
        long testId = 78468374;
        comment.setId(testId);
        assertEquals(testId, comment.getId(), "Comment Id not set correctly");
    }
    
    public void testSetAndGetAuthorID(){
        long testAuthorId = 95837154;
        comment.setAuthorId(testAuthorId);
        assertEquals(testAuthorId, comment.getAuthorId(), "Author Id not set correctly");
    }
    public void testSetAndGetCommentText(){
        String testText = "Comment 78468374 by User 95837154 on Article 95837154";
        assertNull(comment.getText());
        comment.setText(testText);
        assertEquals(testText, comment.getText(), "Comment text is not set correctly");
    }
    public void testSetAndGetVotes(){
        int testVotes = 33;
        comment.setVotes(testVotes);
        assertEquals(testVotes, comment.getVotes(), "Up vote count not set correctly");
    }
}
