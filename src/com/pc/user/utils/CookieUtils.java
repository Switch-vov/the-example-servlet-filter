package com.pc.user.utils;

import javax.servlet.http.Cookie;

/**
 * Cookie工具类
 * 
 * @author Switch
 * @data 2016年10月14日
 * @version V1.0
 */
public class CookieUtils {
	public static Cookie getCookie(Cookie[] cookies, String name) {
		Cookie cookie = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equalsIgnoreCase(name)) {
					cookie = cookies[i];
				}
			}
		}
		return cookie;
	}
}
