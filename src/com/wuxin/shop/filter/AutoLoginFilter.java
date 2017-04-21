package com.wuxin.shop.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wuxin.shop.factory.ServiceFrontFactory;
import com.wuxin.shop.vo.Member;
import com.wuxin.util.CookieUtil;

@WebFilter(filterName = "AutoLoginFilter", urlPatterns = {"/index.jsp", "/pages/front/*"})
public class AutoLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;  // ȡ��session
        HttpSession ses = request.getSession();
        if (ses.getAttribute("mid") == null) {  // ���ڻ�û�е�¼������Ӧ��ȡ��ȫ����Cookie����
            Map<String, String> map = CookieUtil.load(request);
            // ��������б����mid��password����Cookie���ݣ���ô�Ϳ��Խ���ҵ����֤
            if (map.containsKey("mid") && map.containsKey("password")) {
                Member vo = new Member();
                vo.setMid(map.get("mid"));
                vo.setPassword(map.get("password"));
                try {
                    if (ServiceFrontFactory.getIMemberServiceFrontInstance().login(vo)) {
                        ses.setAttribute("mid", vo.getMid());
                        ses.setAttribute("photo", vo.getPhoto());
                    }
                } catch (Exception e) {
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
