package com.wuxin.shop.servlet.back;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuxin.shop.factory.ServiceBackFactory;
import com.wuxin.shop.vo.Item;
import com.wuxin.util.validata.ValidateUtil;

public class ItemServletBack extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("insert".equals(status)) {
                path = this.insert(request);
            } else if ("update".equals(status)) {
                path = this.update(request);
            } else if ("delete".equals(status)) {
                path = this.delete(request);
            } else if ("list".equals(status)) {
                path = this.list(request);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public String insert(HttpServletRequest request) {
        String msg = null;
        String url = null;
        String title = request.getParameter("title");
        if (ValidateUtil.validateEmpty(title)) {
            Item vo = new Item();
            vo.setTitle(title);
            try {
                if (ServiceBackFactory.getIItemServiceBackInstance().insert(vo)) {
                    msg = "商品分类信息增加成功！";
                } else {
                    msg = "商品分类增加失败，请确认输入数据是否正确！";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "商品分类增加失败，请确认输入数据是否正确！";
        }
        url = "/pages/back/admin/item/item_insert.jsp";
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    public String update(HttpServletRequest request) {
        String msg = null;
        String url = null;
        String iid = request.getParameter("iid") ;
        String title = request.getParameter("title") ;
        if (ValidateUtil.validateEmpty(iid) && ValidateUtil.validateEmpty(title)) {
            Item vo = new Item() ;
            vo.setIid(Integer.parseInt(iid));
            vo.setTitle(title);
            try {
                if (ServiceBackFactory.getIItemServiceBackInstance().update(vo)) {
                    msg = "商品分类信息更新成功！" ;
                } else {
                    msg = "商品分类信息更新失败！" ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "要更新的分类数据错误，请重新确认！" ;
        }
        url = "/pages/back/admin/item/ItemServletBack/list";
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    public String delete(HttpServletRequest request) {
        String msg = null;
        String url = null;
        String ids = request.getParameter("ids");
        if (ValidateUtil.validateEmpty(ids)) {
            String result[] = ids.split("\\|");
            Set<Integer> all = new HashSet<Integer>();
            for (int x = 0; x < result.length; x++) {
                all.add(Integer.parseInt(result[x]));
            }
            try {
                if (ServiceBackFactory.getIItemServiceBackInstance()
                        .delete(all)) {
                    msg = "商品分类信息删除成功！" ;
                } else {
                    msg = "商品分类信息删除失败！" ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "要删除的数据错误，请重新确认！";
        }
        url = "/pages/back/admin/item/ItemServletBack/list";
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    public String list(HttpServletRequest request) {
        try {
            request.setAttribute("allItems", ServiceBackFactory.getIItemServiceBackInstance().list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/back/admin/item/item_list.jsp";
    }
}
