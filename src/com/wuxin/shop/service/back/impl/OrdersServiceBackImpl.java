package com.wuxin.shop.service.back.impl;

import java.util.HashMap;
import java.util.Map;

import com.wuxin.shop.dbc.DatabaseConnection;
import com.wuxin.shop.factory.DAOFactory;
import com.wuxin.shop.service.back.IOrdersServiceBack;
import com.wuxin.shop.vo.Orders;

public class OrdersServiceBackImpl implements IOrdersServiceBack {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("allOrders", DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, lineSize, column, keyWord));
            map.put("ordersCount", DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord));
            return map;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Orders show(int oid) throws Exception {
        try {
            Orders vo = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findById(oid);
            if (vo != null) {
                vo.setAllDetails(DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).findAllByOrders(oid));
            }
            return vo;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
