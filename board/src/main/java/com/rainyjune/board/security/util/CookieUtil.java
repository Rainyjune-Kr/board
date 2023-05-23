package com.rainyjune.board.security.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class CookieUtil {
    private Map cookieMap = new HashMap();

    public CookieUtil(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0)
        {
            for (Cookie eachCookie: cookies) {
                cookieMap.put(eachCookie.getName(), eachCookie);
            }
        }
    }

    public Cookie getCookie(String cookieName) {
        if (cookieMap.containsKey(cookieName)) {
            return (Cookie) cookieMap.get(cookieName);
        }

        return null;
    }

    public String getCookieValue(String cookieName) {
        if (!cookieMap.containsKey(cookieName))
            return null;

        Cookie cookie = (Cookie) cookieMap.get(cookieName);
        return cookie.getValue();
    }
}
