package com.wuxin.shop.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuxin.util.CookieUtil;

public class ShopcarCookieUtil {
    public static void addCar(HttpServletRequest request, HttpServletResponse response, int gid, int count) {
        CookieUtil.save(response, request, "sc-" + String.valueOf(gid), String.valueOf(count), 50000);
    }

    public static Map<Integer, Integer> loadCar(HttpServletRequest request) {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        Map<String, String> map = CookieUtil.load(request);
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> me = iter.next();
            if (me.getKey().startsWith("sc-")) {    // 为购物车数据
                result.put(Integer.parseInt(me.getKey().split("-")[1]), Integer.parseInt(me.getValue()));
            }
        }
        return result;
    }

    public static void removeCar(HttpServletRequest request, HttpServletResponse response, Set<Integer> ids) {
        Iterator<Integer> iter = ids.iterator();
        while (iter.hasNext()) {
            CookieUtil.save(response, request, "sc-" + String.valueOf(iter.next()), "0", 0);
        }
    }

    public static void clearCar(HttpServletRequest request, HttpServletResponse response)  {
        CookieUtil.clear(request,response);
    }
}
