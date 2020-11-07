package au.usyd.elec5619.utils;

import javax.servlet.http.Cookie;


public class SimpleCookieHandler {
    public static Cookie getCookieByName(Cookie[] cookies,String name) {
        if (name.isBlank()) {
            return null;
        }
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }


}
