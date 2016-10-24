package com.pc.user.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.pc.user.domain.User;
import com.pc.user.service.UserService;

/**
 * 用户登录Servlet
 * 
 * @author Switch
 * @data 2016年10月24日
 * @version V1.0
 */
public class UserLoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		// 封装表单
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			UserService userService = new UserService();
			// 从数据库中查找用户
			user = userService.findUser(user);
			if (user != null) {
				String autoLogin = request.getParameter("autoLogin");
				// 自动登录，使用Cookie
				if(autoLogin != null) {
					// 密码需要加密
					Cookie cookie = new Cookie("autoLogin", user.getUsername() + ":" + user.getPassword());
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(60 * 60 * 24);
					response.addCookie(cookie);
				}
				request.getSession().setAttribute("loginUser", user);
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				return ;
			} else {
				request.setAttribute("msg", "请输入正确的账号名和密码");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}