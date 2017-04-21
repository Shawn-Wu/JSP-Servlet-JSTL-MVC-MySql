package com.wuxin.shop.factory;

import com.wuxin.shop.service.front.IGoodsServiceFront;
import com.wuxin.shop.service.front.IMemberServiceFront;
import com.wuxin.shop.service.front.IOrdersServiceFront;
import com.wuxin.shop.service.front.IShopcarServiceFront;
import com.wuxin.shop.service.front.impl.GoodsServiceFrontImpl;
import com.wuxin.shop.service.front.impl.MemberServiceFrontImpl;
import com.wuxin.shop.service.front.impl.OrdersServiceFrontImpl;
import com.wuxin.shop.service.front.impl.ShopcarServiceFrontImpl;

public class ServiceFrontFactory {
    public static IMemberServiceFront getIMemberServiceFrontInstance() {
        return new MemberServiceFrontImpl();
    }

    public static IGoodsServiceFront getIGoodsServiceFrontInstance() {
        return new GoodsServiceFrontImpl();
    }
    public static IShopcarServiceFront getIShopcarServiceFrontInstance() {
        return new ShopcarServiceFrontImpl();
    }
    public static IOrdersServiceFront getIOrdersServiceFrontInstance() {
    	return new OrdersServiceFrontImpl();
    }
}
