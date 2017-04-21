package com.wuxin.shop.servlet.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuxin.shop.factory.ServiceFrontFactory;
import com.wuxin.shop.util.ShopcarCookieUtil;
import com.wuxin.shop.vo.Goods;
import com.wuxin.shop.vo.Member;
import com.wuxin.shop.vo.Shopcar;

public class ShopcarServletFront extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("insert".equals(status)) {
                path = this.insert(request, response);
            } else if ("list".equals(status)) {
                path = this.list(request);
            } else if ("update".equals(status)) {
                path = this.update(request, response);
            } else if ("delete".equals(status)) {
                path = this.delete(request, response);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public String insert(HttpServletRequest request, HttpServletResponse response) {
        int gid = Integer.parseInt(request.getParameter("gid"));
        String mid = (String) request.getSession().getAttribute("mid");
        Shopcar vo = new Shopcar();
        Member member = new Member();
        member.setMid(mid);
        vo.setMember(member);
        Goods goods = new Goods();
        goods.setGid(gid);
        vo.setGoods(goods);
        String msg = null;
        try {
            if (ServiceFrontFactory.getIShopcarServiceFrontInstance().addCar(vo)) {
                msg = "购物车添加成功！";
            } else {
                msg = "购物车添加失败！";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String referer = request.getHeader("referer");
        String url = "/pages/front/goods/GoodsServletFront" + referer.substring(referer.lastIndexOf("/"));
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    public String list(HttpServletRequest request) {
        String mid = (String) request.getSession().getAttribute("mid");
        try {
            Map<String, Object> map = ServiceFrontFactory.getIShopcarServiceFrontInstance().listCar(mid);
            request.setAttribute("allGoods", map.get("allGoods"));
            request.setAttribute("allCars", map.get("allShopcars"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/front/shopcar/car_list.jsp";
    }

    public String update(HttpServletRequest request, HttpServletResponse response) {
        String mid = (String) request.getSession().getAttribute("mid");
        ShopcarCookieUtil.clearCar(request, response);
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Enumeration<String> enu = request.getParameterNames();
        Set<Shopcar> all = new HashSet<Shopcar>() ;
        while (enu.hasMoreElements()) {
            String temp = enu.nextElement();
            int gid = Integer.parseInt(temp);
            int count = Integer.parseInt(request.getParameter(temp));
            Shopcar vo = new Shopcar();
            Member member = new Member();
            member.setMid(mid);
            vo.setMember(member);
            Goods goods = new Goods();
            goods.setGid(gid);
            vo.setGoods(goods);
            vo.setAmount(count);
            all.add(vo) ;
        }
        String msg = null;
        String url = null;
        try {
            if (ServiceFrontFactory.getIShopcarServiceFrontInstance().update(mid,all)) {
                msg = "购物车信息更新成功！" ;
            } else {
                msg = "购物车信息更新失败！" ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        url = "/pages/front/shopcar/ShopcarServletFront/list" ;
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) {
        String mid = (String) request.getSession().getAttribute("mid");
        String ids = request.getParameter("ids");
        String result[] = ids.split("\\|");
        Set<Integer> set = new HashSet<Integer>();
        for (int x = 0; x < result.length; x++) {
            set.add(Integer.parseInt(result[x]));
        }
        String msg = null;
        String url = null;
        try {
            if (ServiceFrontFactory.getIShopcarServiceFrontInstance().deleteCar(mid, set)) {
                msg = "购物车商品删除成功！" ;
            } else {
                msg = "购物车商品删除失败！" ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        url = "/pages/front/shopcar/ShopcarServletFront/list" ;
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
}

