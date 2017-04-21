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
        String msg = null ; // ִ��forward.jspʹ��
        String url = null ; // ִ��forward.jspʹ��
        String mid = request.getParameter("mid") ;
        String password = request.getParameter("password") ;
        // ������֤ͨ�����ſ��Խ��к����Ĺ��ܲ���
        if (ValidateUtil.validateEmpty(mid) && ValidateUtil.validateEmpty(password)) {
            Member vo = new Member() ;  // Ҫ�����ݷ�װ��VO�������
            vo.setMid(mid);
            vo.setPassword(new MD5Code().getMD5ofStr(password));    // md5����
            vo.setRegdate(new Date());  // ����Ϊע������
            vo.setPhoto("nophoto.jpg");
            vo.setCode(UUID.randomUUID().toString());   // ����һ�������Code��
            vo.setStatus(2); // �������ڴ�����״̬
            try {
                if (ServiceFrontFactory.getIMemberServiceFrontInstance().regist(vo)) {
                    msg = "�û�ע��ɹ���������˻����" ;
                    url = "/index.jsp" ;
                    System.out.println("���������ʼ���" + BasePath.getBasePath(request) + "/pages/MemberServletFront/active?mid=" + mid + "&code=" + vo.getCode());
                } else {
                    msg = "�û�ע��ʧ�ܣ�����д�µ�ע����Ϣ��" ;
                    url = "/pages/member_regist.jsp" ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "������û�ע����Ϣ����ȷ��������ע�ᣡ" ;
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
                    msg = "�û�����ɹ������¼��" ;
                    url = "/pages/member_login.jsp" ;
                } else {
                    msg = "�û�����ʧ�ܣ��������Ա��ϵ��" ;
                    url = "/index.jsp" ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "����ļ���������������Ա��ϵ��" ;
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
                        msg = "��¼�ɹ�����ӭ���٣�" ;
                        url = "/index.jsp" ;
                        if (request.getParameter("reme") != null) { // ��ʾѡ���˸�ѡ��
                            int expiry = Integer.parseInt(request.getParameter("reme")) ;
                            CookieUtil.save(response, request, "mid", mid,expiry);
                            CookieUtil.save(response, request, "password", vo.getPassword(),expiry);
                        }
                    } else {
                        msg = "��¼ʧ�ܣ�������û��������룡" ;
                        url = "/pages/member_login.jsp" ;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                msg = "��֤��������������µ�¼��" ;
                url = "/pages/member_login.jsp" ;
            }
        } else {
            msg = "��Ϣ������������µ�¼��" ;
            url = "/pages/member_login.jsp" ;
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp" ;
    }
    public String logout(HttpServletRequest request,HttpServletResponse response) {
        CookieUtil.clear(request,response);
        request.getSession().invalidate();
        request.setAttribute("msg","���Ѱ�ȫ�˳���");
        request.setAttribute("url","/index.jsp");
        return "/pages/forward.jsp" ;
    }
}
