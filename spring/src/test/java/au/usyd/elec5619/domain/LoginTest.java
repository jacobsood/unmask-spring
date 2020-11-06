package au.usyd.elec5619.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {
    private static Login login;


    @BeforeEach
    void setUp() {
        login = new Login();
    }
    @Test
    void testSetandGetID(){
        String testId = "23112241";
        login.setId(testId);
        assertEquals(testId, login.getId(), "Login id is not being set correctly");
    }
    @Test
    void testSetandGetUname(){
        String Uname = "Pika";
        assertNull(login.getUname());
        login.setUname(Uname);
        assertEquals(Uname, login.getUname(), "Username is not being set correctly");
    }
    @Test
    void testSetandGetUpwd(){
        String pwd = "PikaPikaP";
        assertNull(login.getUpwd());
        login.setUpwd(pwd);
        assertEquals(pwd, login.getUpwd(), "Username is not being set correctly");

    }

}