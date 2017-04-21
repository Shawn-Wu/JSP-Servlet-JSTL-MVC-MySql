package com.wuxin.shop.dao;

import java.sql.SQLException;
import java.util.List;

import com.wuxin.shop.vo.Details;

public interface IDetailsDAO extends IDAO<Integer,Details>{
    /**
     * 批量创建订单详情
     * @param vos
     * @return
     * @throws Exception
     */
    public boolean doCreateBath(List<Details> vos) throws SQLException;

    /**
     * 根据订单编号查询出一个订单的完整详情内容
     * @param oid
     * @return
     * @throws Exception
     */
    public List<Details> findAllByOrders(Integer oid) throws Exception ;
}

