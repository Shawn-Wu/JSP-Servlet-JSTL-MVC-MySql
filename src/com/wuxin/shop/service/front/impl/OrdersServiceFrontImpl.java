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
            // 1、首先应该判断出当前的用户信息是否完整，要根据mid查询一个用户的完整信息
            Member member = DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findById2(mid);
            // 2、如果现在没有姓名、电话、地址，那么根本就不可能创建订单
            if (member.getName() == null || member.getPhone() == null || member.getAddress() == null) {
                throw new UnCompleteMemberInfomrationException("用户个人信息不完整，无法进行订单创建！");
            }
            // 3、如果现在信息完整，判断数据库中是否包含有购物车信息
            Map<Integer, Integer> cars = DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).findAllByMember(mid);
            // 4、购物车中没有任何的购买商品信息
            if (cars.size() == 0) {
                throw new EmptyShopcarException("购物车暂无购买商品，无法创建订单！");
            }
            // 5、如果现在要是有购物信息，则需要查询出所有的商品信息，目的是取得商品的名称、价格、库存量
            List<Goods> allGoods = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllByGid(cars.keySet());
            // 6、判断要购买的商品的库存是否存在
            Iterator<Goods> iterGoods = allGoods.iterator();
            // 7、在判断库存量操作的同时还需要计算出总的花费金额
            double pay = 0.0;
            while (iterGoods.hasNext()) {
                Goods vo = iterGoods.next();
                // 库存量 - 要准备购买的商品数量
                if (vo.getAmount() - cars.get(vo.getGid()) < 0) {
                    throw new UnEnoughAmountException("商品没有足够的出售数量，无法创建订单！");
                }
                pay += vo.getPrice() * cars.get(vo.getGid());  // 单价 * 购买数量
            }
            // 8、创建订单对象
            Orders orders = new Orders();
            orders.setMember(member);
            orders.setName(member.getName());
            orders.setPhone(member.getPhone());
            orders.setAddress(member.getAddress());
            orders.setCredate(new Date());
            orders.setPay(pay);
            flag = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).doCreateOrders(orders);
            // 9、订单创建完成之后一定要取得当前增长的订单编号，因为订单详情需要这个编号
            orders.setOid(DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findLastInsertId());
            // 10、创建订单详情信息
            iterGoods = null;
            iterGoods = allGoods.iterator();
            List<Details> all = new ArrayList<Details>();
            // 11、修改所有购买商品的数量以及创建所有的详情对象
            while (iterGoods.hasNext()) {
                Details vo = new Details();
                Goods goods = iterGoods.next();
                vo.setGoods(goods); // 订单详情与商品的关系
                vo.setOrders(orders); // 订单详情与订单的关系
                int amount = cars.get(goods.getGid()); // 购买数量
                vo.setAmount(amount);
                vo.setTitle(goods.getTitle());
                vo.setPrice(goods.getPrice());
                all.add(vo);
                flag = DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).doUpdateByAmount(goods.getGid(), 0 - amount);
            }
            flag = DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).doCreateBath(all);
            // 12、清空购物车信息
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
