package com.pc.user.web.filter;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * HttpServletRequest包装类，用于编码处理
 * 
 * @author Switch
 * @data 2016年10月24日
 * @version V1.0
 */
public class HttpServletRequestEncoding extends HttpServletRequestWrapper {

	public HttpServletRequestEncoding(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String parameter = super.getParameter(name);
		try {
			if ("GET".equalsIgnoreCase(super.getMethod())) {
				parameter = new String(parameter.getBytes("iso8859-1"), "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return parameter;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] parameterValues = super.getParameterValues(name);
		try {
			if ("GET".equalsIgnoreCase(super.getMethod())) {
				for (int i = 0; i < parameterValues.length; i++) {
					parameterValues[i] = new String(parameterValues[i].getBytes("iso8859-1"), "utf-8");
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return parameterValues;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> parameterMap = super.getParameterMap();
		Map<String, String[]> map = new LinkedHashMap<String, String[]>();
		if ("GET".equalsIgnoreCase(super.getMethod())) {
			for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
				String parameter = entry.getKey();
				String[] parameterValues = entry.getValue();
				try {
					parameter = new String(parameter.getBytes("iso8859-1"), "utf-8");
					for (int i = 0; i < parameterValues.length; i++) {
						parameterValues[i] = new String(parameterValues[i].getBytes("iso8859-1"), "utf-8");
					}
					map.put(parameter, parameterValues);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}

}
