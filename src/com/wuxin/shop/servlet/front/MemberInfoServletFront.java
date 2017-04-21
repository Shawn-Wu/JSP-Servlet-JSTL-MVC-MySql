package com.wuxin.shop.servlet.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.wuxin.shop.factory.ServiceFrontFactory;
import com.wuxin.shop.vo.Member;
import com.wuxin.util.validata.ValidateUtil;

public class MemberInfoServletFront extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("updatePre".equals(status)) {
                path = this.updatePre(request);
            } else if ("update".equals(status)) {
                path = this.update(request, response);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public String updatePre(HttpServletRequest request) {
        String mid = (String) request.getSession().getAttribute("mid");
        try {
            request.setAttribute("member", ServiceFrontFactory.getIMemberServiceFrontInstance().updatePre(mid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/front/member/member_update.jsp";
    }

    public String update(HttpServletRequest request, HttpServletResponse response) {
        String mid = (String) request.getSession().getAttribute("mid");
        SmartUpload smart = new SmartUpload();
        try {
            smart.initialize(super.getServletConfig(),request,response);
            smart.upload();
        } catch (Exception e) {
        }
        String name = smart.getRequest().getParameter("name");
        String phone = smart.getRequest().getParameter("phone");
        String address = smart.getRequest().getParameter("address");
        String msg = null ;
        String url = null ;
        if (ValidateUtil.validateEmpty(name) &&
                ValidateUtil.validateEmpty(phone) &&
                ValidateUtil.validateEmpty(address)) {
            Member vo = new Member() ;
            vo.setMid(mid);
            vo.setName(name);
            vo.setPhone(phone);
            vo.setAddress(address);
            vo.setPhoto(smart.getRequest().getParameter("oldpic"));
            try {
                if(smart.getFiles().getSize() > 0) {    // 有新的照片要更新
                    if (smart.getFiles().getFile(0).getContentType().contains("image")) {
                        if ("nophoto.jpg".equals(vo.getPhoto())) {
                            vo.setPhoto(UUID.randomUUID() + "." + smart.getFiles().getFile(0).getFileExt());
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ServiceFrontFactory.getIMemberServiceFrontInstance().update(vo)) {
                    if(smart.getFiles().getSize() > 0) {    // 有新的照片要更新
                        if (smart.getFiles().getFile(0).getContentType().contains("image")) {
                            String filePath = super.getServletContext().getRealPath("/upload/member/") + vo.getPhoto() ;
                            smart.getFiles().getFile(0).saveAs(filePath);
                        }
                    }
                    msg = "用户信息更新成功！" ;
                    request.getSession().setAttribute("photo",vo.getPhoto());
                } else {
                    msg = "用户信息更新失败！" ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "更新数据不完整，请重新输入！" ;
        }
        url = "/pages/front/member/MemberInfoServletFront/updatePre" ;
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
}

