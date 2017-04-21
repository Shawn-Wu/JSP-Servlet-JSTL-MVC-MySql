package com.wuxin.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	/**
	 * ����һ��ר�Ÿ��𱣴�Cookie�ķ�������������Cookie�����֣����ݺͱ���ʱ��
	 * @param response ��Ҫ����ʹ��addCookie()�ķ�������Cookie�Ķ���
	 * @param request ��Ҫ����Cookie�ı���·�������û�п������ò���
	 * @param name Ҫ�����Cookie������
	 * @param value Ҫ�����Cookie������
	 * @param expiry Cookie��ʧЧʱ��
	 */
	public static void save(HttpServletResponse response,HttpServletRequest request,String name,String value,int expiry){
		Cookie c = new Cookie(name,value);
		c.setMaxAge(expiry);
		// c.setPath(request.getContextPath()); // ���ñ���·��
		c.setPath("/");
		response.addCookie(c);
	}
	
	public static Map<String,String> load(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		Cookie c[] = request.getCookies();
		if(c!=null){
			for(int x=0;x<c.length;x++){
				map.put(c[x].getName(),c[x].getValue());
			}
		}
		return map;
	}
	
	public static void clear(HttpServletRequest request,HttpServletResponse response){
		Map<String,String> map = load(request);
		Iterator<Map.Entry<String,String>> iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<String, String> me = iter.next();
			Cookie c = new Cookie(me.getKey(),"");
			c.setPath("/");
			c.setMaxAge(0);
			response.addCookie(c);
		}
	}
}
