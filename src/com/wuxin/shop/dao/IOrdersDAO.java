package com.wuxin.shop.dao;

import java.sql.SQLException;
import java.util.List;

import com.wuxin.shop.vo.Orders;

public interface IOrdersDAO extends IDAO<Integer,Orders> {
    /**
     * ����last_insert_id()����ȡ�õ�ǰ������Ķ�����ţ�Ϊ��������׼��
     * @return ���������Զ��������
     * @throws Exception
     */
    public Integer findLastInsertId() throws SQLException;
    public boolean doCreateOrders(Orders vo) throws SQLException ;

    /**
     * �����û��ı�ţ��г����еĶ�����Ϣ
     * @param mid
     * @return
     * @throws Exception
     */
    public List<Orders> findAllByMember(String mid,Integer currentPage,Integer lineSize) throws Exception ;
    public Integer getAllCountByMember(String mid) throws Exception ;

    /**
     * ��ѯһ���û���һ��������Ϣ
     * @param mid
     * @param oid
     * @return
     * @throws Exception
     */
    public Orders findByIdAndMember(String mid,Integer oid) throws Exception ;
}

