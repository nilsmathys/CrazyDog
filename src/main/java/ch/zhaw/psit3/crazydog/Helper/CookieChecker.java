package ch.zhaw.psit3.crazydog.Helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/*
CookieChecker is responsible for checking wether cookies are set or not.
 */
public class CookieChecker {
    private static boolean cookieIsSet = false;

    public static boolean isCookieSet(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("id")) {
                    cookieIsSet = true;
                }
                else {
                    cookieIsSet = false;
                }
            }
        }
        return cookieIsSet;
    }
}
