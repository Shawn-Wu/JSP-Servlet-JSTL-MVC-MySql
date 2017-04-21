package com.wuxin.shop.service.front.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.wuxin.shop.dbc.DatabaseConnection;
import com.wuxin.shop.factory.DAOFactory;
import com.wuxin.shop.service.front.IShopcarServiceFront;
import com.wuxin.shop.vo.Shopcar;

public class ShopcarServiceFrontImpl implements IShopcarServiceFront {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public boolean addCar(Shopcar vo) throws Exception {
        try {
            // 该项购买记录已经存在了
            if (DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).findByMemberAndGoods(vo.getMember().getMid(), vo.getGoods().getGid()) != null) {
                return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doUpdateAmount(vo.getMember().getMid(), vo.getGoods().getGid()) ;
            } else {    // 如果现在购买记录不存在
                vo.setAmount(1); // 刚刚保存的时候数量只能是1
                return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doCreate(vo) ;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> listCar(String mid) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询出一个用户的所有购物车中的记录
            Map<Integer, Integer> cars = DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).findAllByMember(mid);
            map.put("allShopcars", cars);
            map.put("allGoods", DAOFactory.getIGoodsDAOInstance(this.dbc.getConnection()).findAllByGid(cars.keySet()));
            return map;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean deleteCar(String mid, Set<Integer> gid) throws Exception {
        try {
            return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doRemoveByMemberAndGoods(mid,gid) ;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(String mid, Set<Shopcar> vos) throws Exception {
        try {
            if(vos.size() == 0) {
                return false ;
            }
            // 清空已有的购物车中的记录
            if (DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doRemoveByMember(mid)) {
                return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doCreateBatch(vos) ;
            }
            return false ;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
