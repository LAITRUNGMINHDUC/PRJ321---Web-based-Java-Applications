/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author duclt
 */
public class FUNC {

    public static String checkCookie(HttpServletRequest request, String type) {
        Cookie[] cookies = request.getCookies();
        String user = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String tmp = cookie.getName();
                if (!tmp.equalsIgnoreCase("JSESSIONID")
                        && tmp.equalsIgnoreCase(type)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
