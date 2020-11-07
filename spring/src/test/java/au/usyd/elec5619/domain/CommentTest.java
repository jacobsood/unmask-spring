package au.usyd.elec5619.domain;

import static org.junit.jupiter.api.Assertions.*;

public class CommentTest{

    private Comment comment;

    protected void setUp() throws Exception {
        comment = new Comment();
    }

    public void testSetAndGetCommentID(){
        long testCommentID = 78468374;
        comment.setCommentID(testCommentID);
        assertEquals(testCommentID, comment.getCommentID(), "Comment ID not set correctly");
    }
    public void testSetAndGetArticleID(){
        long testArticleID = 42257421;
        comment.setCommentID(testArticleID);
        assertEquals(testArticleID, comment.getArticleID(),"Article ID not set correctly");
    }
    public void testSetAndGetAuthorID(){
        long testAuthorID = 95837154;
        comment.setCommentID(testAuthorID);
        assertEquals(testAuthorID, comment.getAuthorID(), "Author ID not set correctly");
    }
    public void testSetAndGetCommentContent(){
        String testCommentContent = "Comment 78468374 by User 95837154 on Article 95837154";
        assertNull(comment.getCommentContent());
        comment.setCommentContent(testCommentContent);
        assertEquals(testCommentContent, comment.getCommentContent(), "Comment content not set correctly");
    }
    public void testSetAndGetUpVoteCount(){
        int testUpVoteCount = 33;
        comment.setCommentID(testUpVoteCount);
        assertEquals(testUpVoteCount, comment.getUpVoteCount(), "Up vote count not set correctly");
    }
}
