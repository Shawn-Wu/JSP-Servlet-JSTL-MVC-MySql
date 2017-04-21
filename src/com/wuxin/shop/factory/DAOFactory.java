package com.wuxin.shop.factory;

import java.sql.Connection;

import com.wuxin.shop.dao.IAdminDAO;
import com.wuxin.shop.dao.IDetailsDAO;
import com.wuxin.shop.dao.IGoodsDAO;
import com.wuxin.shop.dao.IItemDAO;
import com.wuxin.shop.dao.IMemberDAO;
import com.wuxin.shop.dao.IOrdersDAO;
import com.wuxin.shop.dao.IShopcarDAO;
import com.wuxin.shop.dao.impl.AdminDAOImpl;
import com.wuxin.shop.dao.impl.DetailsDAOImpl;
import com.wuxin.shop.dao.impl.GoodsDAOImpl;
import com.wuxin.shop.dao.impl.ItemDAOImpl;
import com.wuxin.shop.dao.impl.MemberDAOImpl;
import com.wuxin.shop.dao.impl.OrdersDAOImpl;
import com.wuxin.shop.dao.impl.ShopcarDAOImpl;

public class DAOFactory {
    public static IMemberDAO getIMemberDAOInstance(Connection conn) {
        return new MemberDAOImpl(conn);
    }
    public static IAdminDAO getIAdminDAOInstance(Connection conn) {
        return new AdminDAOImpl(conn) ;
    }
    public static IItemDAO getIItemDAOInstance(Connection conn) {
        return new ItemDAOImpl(conn) ;
    }
    public static IGoodsDAO getIGoodsDAOInstance(Connection conn) {
        return new GoodsDAOImpl(conn) ;
    }
    public static IShopcarDAO getIShopcarDAOInstance(Connection conn) {
        return new ShopcarDAOImpl(conn) ;
    }
    public static IOrdersDAO getIOrdersDAOInstance(Connection conn) {
        return new OrdersDAOImpl(conn) ;
    }
    public static IDetailsDAO getIDetailsDAOInstance(Connection conn) {
        return new DetailsDAOImpl(conn) ;
    }
}
