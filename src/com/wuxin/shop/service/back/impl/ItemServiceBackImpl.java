package com.wuxin.shop.service.back.impl;

import java.util.List;
import java.util.Set;

import com.wuxin.shop.dbc.DatabaseConnection;
import com.wuxin.shop.factory.DAOFactory;
import com.wuxin.shop.service.back.IItemServiceBack;
import com.wuxin.shop.vo.Item;

public class ItemServiceBackImpl implements IItemServiceBack {
    private DatabaseConnection dbc = new DatabaseConnection() ;
    @Override
    public boolean insert(Item vo) throws Exception {
        try {
            return DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).doCreate(vo) ;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(Item vo) throws Exception {
        try {
            return DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).doUpdate(vo) ;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean delete(Set<Integer> ids) throws Exception {
        try {
            return DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids) ;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public List<Item> list() throws Exception {
        try {
            return DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll() ;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}

