package com.wuxin.shop.servlet.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuxin.shop.vo.Member;
import com.wuxin.util.BasePath;
import com.wuxin.util.CookieUtil;
import com.wuxin.util.MD5Code;
import com.wuxin.util.validata.ValidateUtil;
import com.wuxin.shop.factory.*;

public class MembeServletFront extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp" ;
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1) ;
        if (status != null) {
            if ("regist".equals(status)) {
                path = this.regist(request) ;
            } else if ("active".equals(status)) {
                path = this.active(request) ;
            } else if ("login".equals(status)) {
                path = this.login(request,response) ;
            } else if ("logout".equals(status)) {
                path = this.logout(request,response) ;
            }
        }
        request.getRequestDispatcher(path).forward(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    public String regist(HttpServletRequest request) {
        String msg = null ; // 执行forward.jsp使用
        String url = null ; // 执行forward.jsp使用
        String mid = request.getParameter("mid") ;
        String password = request.getParameter("password") ;
        // 必须验证通过，才可以进行后续的功能操作
        if (ValidateUtil.validateEmpty(mid) && ValidateUtil.validateEmpty(password)) {
            Member vo = new Member() ;  // 要将数据封装在VO类对象里
            vo.setMid(mid);
            vo.setPassword(new MD5Code().getMD5ofStr(password));    // md5加密
            vo.setRegdate(new Date());  // 今天为注册日期
            vo.setPhoto("nophoto.jpg");
            vo.setCode(UUID.randomUUID().toString());   // 生成一个随机的Code码
            vo.setStatus(2); // 现在属于待激活状态
            try {
                if (ServiceFrontFactory.getIMemberServiceFrontInstance().regist(vo)) {
                    msg = "用户注册成功，请进行账户激活！" ;
                    url = "/index.jsp" ;
                    System.out.println("【发激活邮件】" + BasePath.getBasePath(request) + "/pages/MemberServletFront/active?mid=" + mid + "&code=" + vo.getCode());
                } else {
                    msg = "用户注册失败，请填写新的注册信息！" ;
                    url = "/pages/member_regist.jsp" ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "输入的用户注册信息不正确，请重新注册！" ;
            url = "/pages/member_regist.jsp" ;
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp" ;
    }
    public String active(HttpServletRequest request) {
        String msg = null ;
        String url = null ;
        String mid = request.getParameter("mid") ;
        String code = request.getParameter("code") ;
        if (ValidateUtil.validateEmpty(mid) && ValidateUtil.validateEmpty(code)) {
            Member vo = new Member() ;
            vo.setMid(mid);
            vo.setCode(code);
            try {
                if (ServiceFrontFactory.getIMemberServiceFrontInstance().active(vo)) {
                    msg = "用户激活成功，请登录！" ;
                    url = "/pages/member_login.jsp" ;
                } else {
                    msg = "用户激活失败，请与管理员联系！" ;
                    url = "/index.jsp" ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "错误的激活操作，请与管理员联系！" ;
            url = "/index.jsp" ;
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp" ;
    }

    public String login(HttpServletRequest request,HttpServletResponse response) {
        String msg = null ;
        String url = null ;
        String mid = request.getParameter("mid") ;
        String password = request.getParameter("password") ;
        String code = request.getParameter("code") ;
        String rand = (String) request.getSession().getAttribute("rand") ;
        if (ValidateUtil.validateEmpty(mid) && ValidateUtil.validateEmpty(password)
                && ValidateUtil.validateEmpty(code) && ValidateUtil.validateEmpty(rand)) {
            if (ValidateUtil.validateSame(code,rand)) {
                Member vo = new Member() ;
                vo.setMid(mid);
                vo.setPassword(new MD5Code().getMD5ofStr(password));
                try {
                    if (ServiceFrontFactory.getIMemberServiceFrontInstance().login(vo)) {
                        request.getSession().setAttribute("mid",mid);
                        request.getSession().setAttribute("photo",vo.getPhoto());
                        msg = "登录成功，欢迎光临！" ;
                        url = "/index.jsp" ;
                        if (request.getParameter("reme") != null) { // 表示选择了复选框
                            int expiry = Integer.parseInt(request.getParameter("reme")) ;
                            CookieUtil.save(response, request, "mid", mid,expiry);
                            CookieUtil.save(response, request, "password", vo.getPassword(),expiry);
                        }
                    } else {
                        msg = "登录失败，错误的用户名或密码！" ;
                        url = "/pages/member_login.jsp" ;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                msg = "验证码输入错误，请重新登录！" ;
                url = "/pages/member_login.jsp" ;
            }
        } else {
            msg = "信息输入错误，请重新登录！" ;
            url = "/pages/member_login.jsp" ;
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp" ;
    }
    public String logout(HttpServletRequest request,HttpServletResponse response) {
        CookieUtil.clear(request,response);
        request.getSession().invalidate();
        request.setAttribute("msg","您已安全退出！");
        request.setAttribute("url","/index.jsp");
        return "/pages/forward.jsp" ;
    }
}
