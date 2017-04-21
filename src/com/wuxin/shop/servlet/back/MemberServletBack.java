package com.wuxin.shop.servlet.back;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuxin.shop.factory.ServiceBackFactory;
import com.wuxin.util.validata.ValidateUtil;

public class MemberServletBack extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp" ;
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1) ;
        if (status != null) {
            if ("list".equals(status)) {
                path = this.list(request);
            } else if ("listStatus".equals(status)) {
                path = this.listStatus(request) ;
            } else if ("updateStatus".equals(status)) {
                path = this.updateStatus(request) ;
            } else if ("show".equals(status)) {
                path = this.show(request) ;
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    public String show(HttpServletRequest request) {
        String msg = null ;
        String url = null ;
        String referer = request.getHeader("referer") ; // 取得之前的路径
        String mid = request.getParameter("mid") ;
        if (ValidateUtil.validateEmpty(mid)) {
            try {
                request.setAttribute("member",ServiceBackFactory.getIMemberServiceBackInstance().show(mid));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "/pages/back/admin/member/member_show.jsp" ;
        } else {
            msg = "您要浏览的数据不存在，或者是还未选择任何数据，请重新选择！" ;
            url = "/pages/back/admin/member/MemberServletBack" + referer.substring(referer.lastIndexOf("/")) ;
            request.setAttribute("msg",msg);
            request.setAttribute("url",url);
            return "/pages/forward.jsp" ;
        }
    }
    public String list(HttpServletRequest request) {
        int currentPage = 1 ;
        int lineSize = 15 ;
        String column = null ;
        String keyWord = null ;
        String columnData = "用户名:mid|真实姓名:name|联系电话:phone|地址:address" ;
        try {
            currentPage = Integer.parseInt(request.getParameter("cp")) ;
        } catch (Exception e) {}
        try {
            lineSize = Integer.parseInt(request.getParameter("ls")) ;
        } catch (Exception e) {}
        column = request.getParameter("col") ;
        keyWord = request.getParameter("kw") ;
        if (column == null) {
            column = "mid" ;
        }
        if (keyWord == null) {
            keyWord = "" ;  // 表示查询全部
        }
        try {
            Map<String,Object> map = ServiceBackFactory.getIMemberServiceBackInstance().list(currentPage,lineSize,column,keyWord) ;
            request.setAttribute("allMembers",map.get("allMembers"));
            request.setAttribute("allRecorders",map.get("memberCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("lineSize",lineSize);
        request.setAttribute("column",column);
        request.setAttribute("keyWord",keyWord);
        request.setAttribute("columnData",columnData);
        request.setAttribute("url","/pages/back/admin/member/MemberServletBack/list");
        return "/pages/back/admin/member/member_list.jsp" ;
    }
    public String updateStatus(HttpServletRequest request) {
//        Enumeration<String> enu = request.getHeaderNames() ;
//        while (enu.hasMoreElements()) {
//            String name = enu.nextElement() ;
//            System.out.println("*** name = " + name + "，value = " + request.getHeader(name));
//        }
        String referer = request.getHeader("referer") ; // 取得之前的路径
        String type = request.getParameter("type") ;
        String msg = null ;
        String url = null ;
        String ids = request.getParameter("ids") ;
        if (ValidateUtil.validateEmpty(ids)) {
            String result [] = ids.split("\\|") ;
            Set<String> mid = new HashSet<String>() ;
            for (int x = 0 ; x < result.length ; x ++) {
                mid.add(result[x]) ;
            }
            try {
                boolean flag = false ;  // 保存状态
                if ("active".equalsIgnoreCase(type)) {
                    flag = ServiceBackFactory.getIMemberServiceBackInstance().updateActive(mid) ;
                }
                if ("lock".equalsIgnoreCase(type)) {
                    flag = ServiceBackFactory.getIMemberServiceBackInstance().updateLock(mid) ;
                }
                if (flag) {
                    msg = "用户状态已经更新成功！";
                } else {
                    msg = "用户状态更新失败！";
                }

//                if ("active".equalsIgnoreCase(type)) {
//                    if (ServiceBackFactory.getIMemberServiceBackInstance().updateActive(mid)) {
//                        msg = "用户状态已经更新成功！";
//                    } else {
//                        msg = "用户状态已经更新失败！";
//                    }
//                }
//                if ("lock".equalsIgnoreCase(type)) {
//                    if (ServiceBackFactory.getIMemberServiceBackInstance().updateLock(mid)) {
//                        msg = "用户状态已经更新成功！" ;
//                    } else {
//                        msg = "用户状态已经更新失败！" ;
//                    }
//                }
                url = "/pages/back/admin/member/MemberServletBack" + referer.substring(referer.lastIndexOf("/")) ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "您还未选择更新数据，请重新操作！" ;
            url = "/pages/back/admin/member/MemberServletBack" + referer.substring(referer.lastIndexOf("/")) ;
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp" ;
    }
    public String listStatus(HttpServletRequest request) {
        int status = 0 ;
        int currentPage = 1 ;
        int lineSize = 15 ;
        String column = null ;
        String keyWord = null ;
        String columnData = "用户名:mid|真实姓名:name|联系电话:phone|地址:address" ;
        try {
            status = Integer.parseInt(request.getParameter("status")) ;
        } catch (Exception e) {}
        try {
            currentPage = Integer.parseInt(request.getParameter("cp")) ;
        } catch (Exception e) {}
        try {
            lineSize = Integer.parseInt(request.getParameter("ls")) ;
        } catch (Exception e) {}
        column = request.getParameter("col") ;
        keyWord = request.getParameter("kw") ;
        if (column == null) {
            column = "mid" ;
        }
        if (keyWord == null) {
            keyWord = "" ;  // 表示查询全部
        }
        try {
            Map<String,Object> map = ServiceBackFactory.getIMemberServiceBackInstance().listByStatus(status, currentPage, lineSize, column, keyWord) ;
            request.setAttribute("allMembers",map.get("allMembers"));
            request.setAttribute("allRecorders",map.get("memberCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("lineSize",lineSize);
        request.setAttribute("column",column);
        request.setAttribute("keyWord",keyWord);
        request.setAttribute("columnData",columnData);
        request.setAttribute("url","/pages/back/admin/member/MemberServletBack/list");
        request.setAttribute("paramName","status");
        request.setAttribute("paramValue",String.valueOf(status));
        return "/pages/back/admin/member/member_list.jsp" ;
    }
}

