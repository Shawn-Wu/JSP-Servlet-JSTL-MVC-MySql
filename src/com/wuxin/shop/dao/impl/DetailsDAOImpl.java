package com.wuxin.shop.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.wuxin.shop.dao.IDetailsDAO;
import com.wuxin.shop.vo.Details;
import com.wuxin.shop.vo.Goods;
import com.wuxin.shop.vo.Orders;
import com.wuxin.util.dao.AbstractDAOImpl;

public class DetailsDAOImpl extends AbstractDAOImpl implements IDetailsDAO {
    public DetailsDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreateBath(List<Details> vos) throws SQLException {
        boolean flag = true ;
        String sql = "INSERT INTO details(oid,gid,title,price,amount) VALUES (?,?,?,?,?)" ;
        super.pstmt = super.conn.prepareStatement(sql) ;
        Iterator<Details> iter = vos.iterator() ;
        while(iter.hasNext()) {
            Details vo = iter.next() ;
            super.pstmt.setInt(1, vo.getOrders().getOid());
            super.pstmt.setInt(2,vo.getGoods().getGid()) ;
            super.pstmt.setString(3,vo.getTitle());
            super.pstmt.setDouble(4,vo.getPrice());
            super.pstmt.setInt(5,vo.getAmount());
            super.pstmt.addBatch();
        }
        int result [] = super.pstmt.executeBatch() ;
        for (int x = 0 ; x < result.length ; x ++) {
            if (result[x] == 0) {
                flag = false ;
            }
        }
        return flag;
    }

    @Override
    public List<Details> findAllByOrders(Integer oid) throws Exception {
        List<Details> all = new ArrayList<Details>() ;
        String sql = "SELECT odid,oid,gid,title,price,amount FROM details WHERE oid=?" ;
        super.pstmt = super.conn.prepareStatement(sql) ;
        super.pstmt.setInt(1,oid);
        ResultSet rs = super.pstmt.executeQuery() ;
        while(rs.next()) {
            Details vo = new Details() ;
            vo.setOdid(rs.getInt(1));
            Orders or = new Orders() ;
            or.setOid(rs.getInt(2));
            vo.setOrders(or);
            Goods g = new Goods() ;
            g.setGid(rs.getInt(3));
            vo.setGoods(g);
            vo.setTitle(rs.getString(4));
            vo.setPrice(rs.getDouble(5));
            vo.setAmount(rs.getInt(6));
            all.add(vo) ;
        }
        return all;
    }

    @Override
    public boolean doCreate(Details vo) throws Exception {
        return false;
    }

    @Override
    public boolean doUpdate(Details vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Details findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Details> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Details> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
