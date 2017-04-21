package com.wuxin.shop.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.wuxin.shop.dao.IGoodsDAO;
import com.wuxin.shop.vo.Admin;
import com.wuxin.shop.vo.Goods;
import com.wuxin.shop.vo.Item;
import com.wuxin.util.dao.AbstractDAOImpl;

public class GoodsDAOImpl extends AbstractDAOImpl implements IGoodsDAO {

    public GoodsDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Goods vo) throws Exception {
        String sql = "INSERT INTO goods(iid,aid,title,pubdate,price,amount,bow,note,photo,status) VALUES (?,?,?,?,?,?,?,?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1, vo.getItem().getIid());
        super.pstmt.setString(2, vo.getAdmin().getAid());
        super.pstmt.setString(3, vo.getTitle());
        super.pstmt.setTimestamp(4, new java.sql.Timestamp(vo.getPubdate().getTime()));
        super.pstmt.setDouble(5, vo.getPrice());
        super.pstmt.setInt(6, vo.getAmount());
        super.pstmt.setInt(7, vo.getBow());
        super.pstmt.setString(8, vo.getNote());
        super.pstmt.setString(9, vo.getPhoto());
        super.pstmt.setInt(10, vo.getStatus());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doUpdate(Goods vo) throws Exception {
        String sql = "UPDATE goods SET iid=?,title=?,price=?,amount=?,note=?,photo=?,status=? WHERE gid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1, vo.getItem().getIid());
        super.pstmt.setString(2, vo.getTitle());
        super.pstmt.setDouble(3, vo.getPrice());
        super.pstmt.setInt(4, vo.getAmount());
        super.pstmt.setString(5, vo.getNote());
        super.pstmt.setString(6, vo.getPhoto());
        super.pstmt.setInt(7, vo.getStatus());
        super.pstmt.setInt(8, vo.getGid());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return super.removeHandle("goods","gid",ids);
    }

    @Override
    public Goods findById(Integer id) throws Exception {
        Goods vo = null;
        String sql = "SELECT gid,iid,aid,title,pubdate,price,amount,bow,note,photo,status FROM goods WHERE gid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1, id);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            vo = new Goods();
            vo.setGid(rs.getInt(1));
            Item item = new Item();
            item.setIid(rs.getInt(2));
            vo.setItem(item);
            Admin admin = new Admin();
            admin.setAid(rs.getString(3));
            vo.setAdmin(admin);
            vo.setTitle(rs.getString(4));
            vo.setPubdate(rs.getTimestamp(5));
            vo.setPrice(rs.getDouble(6));
            vo.setAmount(rs.getInt(7));
            vo.setBow(rs.getInt(8));
            vo.setNote(rs.getString(9));
            vo.setPhoto(rs.getString(10));
            vo.setStatus(rs.getInt(11));
        }
        return vo;
    }

    @Override
    public List<Goods> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Goods> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        List<Goods> all = new ArrayList<Goods>();
        String sql = "SELECT gid,iid,aid,title,pubdate,price,amount,bow,note,photo,status FROM goods WHERE " + column + " LIKE ? AND status<>2 LIMIT ? , ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setInt(2, (currentPage - 1) * lineSize);
        super.pstmt.setInt(3, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Goods vo = new Goods();
            vo.setGid(rs.getInt(1));
            Item item = new Item();
            item.setIid(rs.getInt(2));
            vo.setItem(item);
            Admin admin = new Admin();
            admin.setAid(rs.getString(3));
            vo.setAdmin(admin);
            vo.setTitle(rs.getString(4));
            vo.setPubdate(rs.getTimestamp(5));
            vo.setPrice(rs.getDouble(6));
            vo.setAmount(rs.getInt(7));
            vo.setBow(rs.getInt(8));
            vo.setNote(rs.getString(9));
            vo.setPhoto(rs.getString(10));
            vo.setStatus(rs.getInt(11));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        String sql = "SELECT COUNT(*) FROM goods WHERE " + column + " LIKE ? AND status<>2";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public List<Goods> findAllByStatus(Integer status, Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        List<Goods> all = new ArrayList<Goods>();
        String sql = "SELECT gid,iid,aid,title,pubdate,price,amount,bow,note,photo,status FROM goods WHERE " + column + " LIKE ? AND status=? LIMIT ? , ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setInt(2, status);
        super.pstmt.setInt(3, (currentPage - 1) * lineSize);
        super.pstmt.setInt(4, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Goods vo = new Goods();
            vo.setGid(rs.getInt(1));
            Item item = new Item();
            item.setIid(rs.getInt(2));
            vo.setItem(item);
            Admin admin = new Admin();
            admin.setAid(rs.getString(3));
            vo.setAdmin(admin);
            vo.setTitle(rs.getString(4));
            vo.setPubdate(rs.getTimestamp(5));
            vo.setPrice(rs.getDouble(6));
            vo.setAmount(rs.getInt(7));
            vo.setBow(rs.getInt(8));
            vo.setNote(rs.getString(9));
            vo.setPhoto(rs.getString(10));
            vo.setStatus(rs.getInt(11));
            all.add(vo);
        }
        return all;
    }

    @Override
    public List<Goods> findAllByItem(Integer iid,Integer status, Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        List<Goods> all = new ArrayList<Goods>();
        String sql = "SELECT gid,iid,aid,title,pubdate,price,amount,bow,note,photo,status FROM goods WHERE " + column + " LIKE ? AND status=? AND iid=? LIMIT ? , ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setInt(2, status);
        super.pstmt.setInt(3, iid);
        super.pstmt.setInt(4, (currentPage - 1) * lineSize);
        super.pstmt.setInt(5, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Goods vo = new Goods();
            vo.setGid(rs.getInt(1));
            Item item = new Item();
            item.setIid(rs.getInt(2));
            vo.setItem(item);
            Admin admin = new Admin();
            admin.setAid(rs.getString(3));
            vo.setAdmin(admin);
            vo.setTitle(rs.getString(4));
            vo.setPubdate(rs.getTimestamp(5));
            vo.setPrice(rs.getDouble(6));
            vo.setAmount(rs.getInt(7));
            vo.setBow(rs.getInt(8));
            vo.setNote(rs.getString(9));
            vo.setPhoto(rs.getString(10));
            vo.setStatus(rs.getInt(11));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer getAllCountByStatus(Integer status, String column, String keyWord) throws Exception {
        String sql = "SELECT COUNT(*) FROM goods WHERE " + column + " LIKE ? AND status=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setInt(2, status);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public Integer getAllCountByItem(Integer iid,Integer status, String column, String keyWord) throws Exception {
        String sql = "SELECT COUNT(*) FROM goods WHERE " + column + " LIKE ? AND status=? AND iid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setInt(2, status);
        super.pstmt.setInt(3, iid);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean doUpdateBow(Integer id) throws Exception {
        String sql = "UPDATE goods SET bow=bow+1 WHERE gid=?" ;
        super.pstmt = super.conn.prepareStatement(sql) ;
        super.pstmt.setInt(1,id);
        return this.pstmt.executeUpdate() > 0 ;
    }

    @Override
    public List<Goods> findAllByGid(Set<Integer> ids) throws SQLException {
        List<Goods> all = new ArrayList<Goods>();
        StringBuffer sql = new StringBuffer() ;
        sql.append("SELECT gid,iid,aid,title,pubdate,price,amount,bow,note,photo,status FROM goods WHERE status=1 AND gid IN (");
        Iterator<Integer> iter = ids.iterator() ;
        while (iter.hasNext()) {
            sql.append(iter.next()).append(",") ;
        }
        sql.delete(sql.length()-1,sql.length()).append(")") ;
        super.pstmt = super.conn.prepareStatement(sql.toString());
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Goods vo = new Goods();
            vo.setGid(rs.getInt(1));
            Item item = new Item();
            item.setIid(rs.getInt(2));
            vo.setItem(item);
            Admin admin = new Admin();
            admin.setAid(rs.getString(3));
            vo.setAdmin(admin);
            vo.setTitle(rs.getString(4));
            vo.setPubdate(rs.getTimestamp(5));
            vo.setPrice(rs.getDouble(6));
            vo.setAmount(rs.getInt(7));
            vo.setBow(rs.getInt(8));
            vo.setNote(rs.getString(9));
            vo.setPhoto(rs.getString(10));
            vo.setStatus(rs.getInt(11));
            all.add(vo);
        }
        return all;
    }

    @Override
    public boolean doUpdateByAmount(Integer gid, Integer num) throws SQLException {
        String sql = "UPDATE goods SET amount=amount+" + num + " WHERE gid=?" ;
        super.pstmt = super.conn.prepareStatement(sql) ;
        super.pstmt.setInt(1,gid);
        return super.pstmt.executeUpdate() > 0 ;
    }

    @Override
    public boolean doUpdateStatus(Set<Integer> id, Integer status) throws Exception {
        String sql = "UPDATE goods SET status=? WHERE gid=?";
        Iterator<Integer> iter = id.iterator();
        super.pstmt = super.conn.prepareStatement(sql);
        while (iter.hasNext()) {
            super.pstmt.setInt(1, status);
            super.pstmt.setInt(2, iter.next());
            super.pstmt.addBatch();
        }
        boolean flag = true;
        int result[] = super.pstmt.executeBatch();
        for (int x = 0; x < result.length; x++) {
            if (result[x] == 0) {
                flag = false;
            }
        }
        return true;
    }

    @Override
    public Set<String> findAllByPhoto(Set<Integer> id) throws Exception {
        if (id.size() > 0) {
            return super.photoHandle("goods","photo","gid",id) ;
        }
        return null;
    }
}

