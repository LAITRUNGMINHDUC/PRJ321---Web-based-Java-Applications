/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author duclt
 */
public class Function {

    public static String cookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        String value = null;
        if (cookies != null) {
            for (Cookie x : cookies) {
                if (x.getName().equals(name)) {
                    value = x.getValue();
                }
            }
        }
        if (value != null) {
            return value;
        }
        return null;
    }

    public static List<String> listErrors;
    public static String error;

    public static Date validDate(String date) {

        //1. Check Format YYYY-MM-DD
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        date = date.trim();
        if (date.matches(regex)) {
            //2. Check valid date
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return formatter.parse(date);                
            } catch (Exception e) {
                error = e.getMessage();
            }
        }
        if (error == null) {
            error = "Invalid format yyyy-MM-dd";
        }
        return null;
    }

}
