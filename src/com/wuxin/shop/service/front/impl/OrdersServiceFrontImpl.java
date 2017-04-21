package com.wuxin.shop.service.front.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wuxin.shop.dbc.DatabaseConnection;
import com.wuxin.shop.exception.EmptyShopcarException;
import com.wuxin.shop.exception.UnCompleteMemberInfomrationException;
import com.wuxin.shop.exception.UnEnoughAmountException;
import com.wuxin.shop.factory.DAOFactory;
import com.wuxin.shop.service.front.IOrdersServiceFront;
import com.wuxin.shop.vo.Details;
import com.wuxin.shop.vo.Goods;
import com.wuxin.shop.vo.Member;
import com.wuxin.shop.vo.Orders;

public class OrdersServiceFrontImpl implements IOrdersServiceFront {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public boolean insert(String mid) throws UnCompleteMemberInfomrationException, UnEnoughAmountException, EmptyShopcarException, SQLException {
        boolean flag = false;
        dbc.getConnection().setAutoCommit(false);
        try {
            // 1������Ӧ���жϳ���ǰ���û���Ϣ�Ƿ�������Ҫ����mid��ѯһ���û���������Ϣ
            Member member = DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findById2(mid);
            // 2���������û���������绰����ַ����ô�����Ͳ����ܴ�������
            if (member.getName() == null || member.getPhone() == null || member.getAddress() == null) {
                throw new UnCompleteMemberInfomrationException("�û�������Ϣ���������޷����ж���������");
            }
            // 3�����������Ϣ�������ж����ݿ����Ƿ�����й��ﳵ��Ϣ
            Map<Integer, Integer> cars = DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).findAllByMember(mid);
            // 4�����ﳵ��û���κεĹ�����Ʒ��Ϣ
            if (cars.size() == 0) {
                throw new EmptyShopcarException("���ﳵ���޹�����Ʒ���޷�����������");
            }
            // 5���������Ҫ���й�����Ϣ������Ҫ��ѯ�����е���Ʒ��Ϣ��Ŀ����ȡ����Ʒ�����ơ��۸񡢿����
            List<Goods> allGoods = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllByGid(cars.keySet());
            // 6���ж�Ҫ�������Ʒ�Ŀ���Ƿ����
            Iterator<Goods> iterGoods = allGoods.iterator();
            // 7�����жϿ����������ͬʱ����Ҫ������ܵĻ��ѽ��
            double pay = 0.0;
            while (iterGoods.hasNext()) {
                Goods vo = iterGoods.next();
                // ����� - Ҫ׼���������Ʒ����
                if (vo.getAmount() - cars.get(vo.getGid()) < 0) {
                    throw new UnEnoughAmountException("��Ʒû���㹻�ĳ����������޷�����������");
                }
                pay += vo.getPrice() * cars.get(vo.getGid());  // ���� * ��������
            }
            // 8��������������
            Orders orders = new Orders();
            orders.setMember(member);
            orders.setName(member.getName());
            orders.setPhone(member.getPhone());
            orders.setAddress(member.getAddress());
            orders.setCredate(new Date());
            orders.setPay(pay);
            flag = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).doCreateOrders(orders);
            // 9�������������֮��һ��Ҫȡ�õ�ǰ�����Ķ�����ţ���Ϊ����������Ҫ������
            orders.setOid(DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findLastInsertId());
            // 10����������������Ϣ
            iterGoods = null;
            iterGoods = allGoods.iterator();
            List<Details> all = new ArrayList<Details>();
            // 11���޸����й�����Ʒ�������Լ��������е��������
            while (iterGoods.hasNext()) {
                Details vo = new Details();
                Goods goods = iterGoods.next();
                vo.setGoods(goods); // ������������Ʒ�Ĺ�ϵ
                vo.setOrders(orders); // ���������붩���Ĺ�ϵ
                int amount = cars.get(goods.getGid()); // ��������
                vo.setAmount(amount);
                vo.setTitle(goods.getTitle());
                vo.setPrice(goods.getPrice());
                all.add(vo);
                flag = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdateByAmount(goods.getGid(), 0 - amount);
            }
            flag = DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).doCreateBath(all);
            // 12����չ��ﳵ��Ϣ
            flag = DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doRemoveByMember(mid);
            dbc.getConnection().commit();
        } catch (SQLException e) {
            dbc.getConnection().rollback();
            throw e;
        } finally {
            this.dbc.close();
        }
        return flag;
    }

    @Override
    public Map<String,Object> listByMember(String mid,int currentPage,int lineSize) throws Exception {
        try {
            Map<String,Object> map = new HashMap<String, Object>() ;
            map.put("allOrders",DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findAllByMember(mid,currentPage,lineSize)) ;
            map.put("ordersCount",DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).getAllCountByMember(mid)) ;
            return map ;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Orders show(String mid, int oid) throws Exception {
        try {
            Orders vo = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findByIdAndMember(mid,oid) ;
            if (vo != null) {
                vo.setAllDetails(DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).findAllByOrders(oid));
            }
            return vo ;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
