package au.usyd.elec5619.domain;

import junit.framework.TestCase;

public class CommentTest extends TestCase {

    private Comment comment;

    @Override
    protected void setUp() throws Exception {
        comment = new Comment();
    }

    public void testSetAndGetCommentID(){
        long testCommentID = 78468374;
        assertNull(comment.getCommentID());
        comment.setCommentID(testCommentID);
        assertEquals("Comment ID not set correctly", testCommentID, comment.getCommentID());
    }
    public void testSetAndGetArticleID(){
        long testArticleID = 42257421;
        assertNull(comment.getArticleID());
        comment.setCommentID(testArticleID);
        assertEquals("Article ID not set correctly", testArticleID, comment.getArticleID());
    }
    public void testSetAndGetAuthorID(){
        long testAuthorID = 95837154;
        assertNull(comment.getArticleID());
        comment.setCommentID(testAuthorID);
        assertEquals("Author ID not set correctly", testAuthorID, comment.getAuthorID());
    }
    public void testSetAndGetCommentContent(){
        String testCommentContent = "Comment 78468374 by User 95837154 on Article 95837154";
        assertNull(comment.getCommentContent());
        comment.setCommentContent(testCommentContent);
        assertEquals("Comment content not set correctly", testCommentContent, comment.getCommentContent());
    }
    public void testSetAndGetUpVoteCount(){
        int testUpVoteCount = 33;
        assertNull(comment.getUpVoteCount());
        comment.setCommentID(testUpVoteCount);
        assertEquals("Up vote count not set correctly", testUpVoteCount, comment.getUpVoteCount());
    }
}
