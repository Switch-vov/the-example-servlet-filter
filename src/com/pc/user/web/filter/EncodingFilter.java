package com.pc.user.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 编码过滤器
 * 
 * @author Switch
 * @data 2016年10月24日
 * @version V1.0
 */
public class EncodingFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// 设置编码
		req.setCharacterEncoding("utf-8");
		
		// 使用装饰者模式，实现GET请求方式的乱码问题
		HttpServletRequestEncoding requestEncoding = new HttpServletRequestEncoding(req);
		
		// 使用自定义的Request，放行
		chain.doFilter(requestEncoding, res);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void destroy() {

	}
}
