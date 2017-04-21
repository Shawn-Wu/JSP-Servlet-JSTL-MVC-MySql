package com.wuxin.shop.servlet.back;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuxin.shop.factory.ServiceBackFactory;

public class OrdersServletBack extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("list".equals(status)) {
                path = this.list(request);
            } else if ("show".equals(status)) {
                path = this.show(request);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public String list(HttpServletRequest request) {
        int currentPage = 1;
        int lineSize = 15;
        String column = null;
        String keyWord = null;
        String columnData = "购买用户:title";
        try {
            currentPage = Integer.parseInt(request.getParameter("cp"));
        } catch (Exception e) {
        }
        try {
            lineSize = Integer.parseInt(request.getParameter("ls"));
        } catch (Exception e) {
        }
        column = request.getParameter("col");
        keyWord = request.getParameter("kw");
        if (column == null) {
            column = "mid";
        }
        if (keyWord == null) {
            keyWord = "";  // 表示查询全部
        }
        try {
            Map<String, Object> map = ServiceBackFactory.getIOrdersServiceBackInstance().list(currentPage, lineSize, column, keyWord);
            request.setAttribute("allOrders", map.get("allOrders"));
            request.setAttribute("allRecorders", map.get("ordersCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("lineSize", lineSize);
        request.setAttribute("column", column);
        request.setAttribute("keyWord", keyWord);
        request.setAttribute("columnData", columnData);
        request.setAttribute("url", "/pages/back/admin/orders/OrdersServletBack/list");
        return "/pages/back/admin/orders/orders_list.jsp";
    }

    public String show(HttpServletRequest request) {
        int oid = Integer.parseInt(request.getParameter("oid"));
        System.out.println("******************** " + oid);
        try {
            request
                    .setAttribute("orders", ServiceBackFactory.getIOrdersServiceBackInstance().show(oid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/back/admin/orders/orders_show.jsp";
    }
}
