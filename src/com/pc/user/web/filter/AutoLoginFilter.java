package com.pc.user.web.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pc.user.domain.User;
import com.pc.user.service.UserService;
import com.pc.user.utils.CookieUtils;

/**
 * 自动登录过滤器
 * 
 * @author Switch
 * @data 2016年10月24日
 * @version V1.0
 */
public class AutoLoginFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 获取Session中的登录用户
		User loginUser = (User) req.getSession().getAttribute("loginUser");
		// 存在，直接放行
		if (loginUser != null) {
			chain.doFilter(req, res);
			return;
		}

		// 判断自动登录Cookie是否存在
		Cookie cookie = CookieUtils.getCookie(req.getCookies(), "autoLogin");
		if (cookie != null) {
			String[] splitCookie = cookie.getValue().split(":");
			try {
				loginUser = new User();
				loginUser.setUsername(splitCookie[0]);
				loginUser.setPassword(splitCookie[1]);
				// 查询数据库
				UserService service = new UserService();
				loginUser = service.findUser(loginUser);
				// 查询到了用户，放入Session域中
				if (loginUser != null) {
					req.getSession().setAttribute("loginUser", loginUser);
				} else {
					// 将cookie时间设置为0
					cookie.setMaxAge(0);
					res.addCookie(cookie);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		chain.doFilter(req, res);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void destroy() {

	}
}
