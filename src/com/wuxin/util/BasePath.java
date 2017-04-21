package com.wuxin.util;

import javax.servlet.http.HttpServletRequest;

public class BasePath {
	public static String getBasePath(HttpServletRequest request){
		String path = request.getContextPath();
		String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
		return basepath;
	}
}
