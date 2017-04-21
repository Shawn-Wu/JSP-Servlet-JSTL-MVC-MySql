package com.wuxin.shop.service.back.impl;

import com.wuxin.shop.dbc.DatabaseConnection;
import com.wuxin.shop.factory.DAOFactory;
import com.wuxin.shop.service.back.IAdminServiceBack;
import com.wuxin.shop.vo.Admin;

public class AdminServiceBackImpl implements IAdminServiceBack {
    private DatabaseConnection dbc = new DatabaseConnection() ;
    @Override
    public boolean login(Admin vo) throws Exception {
        try {
            if (DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findLogin(vo)) {
                return DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).doUpdateLastdate(vo.getAid()) ;
            }
            return false ;
        } catch (Exception e) {
            throw e ;
        } finally {
            this.dbc.close();
        }
    }
}
