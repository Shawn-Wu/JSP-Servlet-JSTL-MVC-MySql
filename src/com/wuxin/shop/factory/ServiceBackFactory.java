package com.wuxin.shop.factory;

import com.wuxin.shop.service.back.IAdminServiceBack;
import com.wuxin.shop.service.back.IGoodsServiceBack;
import com.wuxin.shop.service.back.IItemServiceBack;
import com.wuxin.shop.service.back.IMemberServiceBack;
import com.wuxin.shop.service.back.IOrdersServiceBack;
import com.wuxin.shop.service.back.impl.AdminServiceBackImpl;
import com.wuxin.shop.service.back.impl.GoodsServiceBackImpl;
import com.wuxin.shop.service.back.impl.ItemServiceBackImpl;
import com.wuxin.shop.service.back.impl.MemberServiceBackImpl;
import com.wuxin.shop.service.back.impl.OrdersServiceBackImpl;

public class ServiceBackFactory {
    public static IAdminServiceBack getIAdminServiceBackInstance() {
        return new AdminServiceBackImpl();
    }
    public static IMemberServiceBack getIMemberServiceBackInstance() {
        return new MemberServiceBackImpl() ;
    }
    public static IItemServiceBack getIItemServiceBackInstance() {
        return new ItemServiceBackImpl() ;
    }
    public static IGoodsServiceBack getIGoodsServiceBackInstance() {
        return new GoodsServiceBackImpl() ;
    }
    public static IOrdersServiceBack getIOrdersServiceBackInstance() {
        return new OrdersServiceBackImpl() ;
    }
}
