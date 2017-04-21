package com.wuxin.shop.dao;

import java.sql.SQLException;
import java.util.List;

import com.wuxin.shop.vo.Details;

public interface IDetailsDAO extends IDAO<Integer,Details>{
    /**
     * ����������������
     * @param vos
     * @return
     * @throws Exception
     */
    public boolean doCreateBath(List<Details> vos) throws SQLException;

    /**
     * ���ݶ�����Ų�ѯ��һ��������������������
     * @param oid
     * @return
     * @throws Exception
     */
    public List<Details> findAllByOrders(Integer oid) throws Exception ;
}

