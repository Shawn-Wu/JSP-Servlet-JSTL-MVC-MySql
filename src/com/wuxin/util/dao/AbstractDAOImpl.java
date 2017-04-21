package com.wuxin.util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class AbstractDAOImpl {
	protected Connection conn;
	protected PreparedStatement pstmt;
	
	public AbstractDAOImpl(Connection conn){
		this.conn=conn;
	}
	
    /**
     * 实现数据的批量删除，这个时候的批量删除属于彻底删除功能
     * @param table 表名称
     * @param column 删除表的列名称
     * @param ids 所有的id数据，使用Set集合可以避免重复
     * @return 如果删除成功返回true，否则返回false
     * @throws Exception
     */	
	public boolean removeHandle(String table,String column,Set<?> ids)throws Exception{
		if(ids.size()==0){
			return false;
		}
		StringBuffer buf=new StringBuffer();
		buf.append("DELETE FROM ").append(table).append(" WHERE ").append(column).append(" IN (");
		Iterator<?> iter = ids.iterator();
		while(iter.hasNext()){
			buf.append(iter.next()).append(",");
		}
		buf.delete(buf.length()-1, buf.length()).append(")");
		this.pstmt=this.conn.prepareStatement(buf.toString());
		return this.pstmt.executeUpdate()==ids.size();
	}
	
    /**
     * 负责统计出数据量
     * @param table 要统计数据的表名称
     * @param column 模糊查询的数据列
     * @param keyWord 模糊查询关键字
     * @return 返回指定表的数据量，如果表没有数据，返回0
     * @throws Exception
     */
	public Integer countHandle(String table,String column,String keyWord)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM ").append(table).append(" WHERE ").append(column).append(" LIKE ?");
		this.pstmt = this.conn.prepareStatement(sql.toString());
		this.pstmt.setString(1, "%"+keyWord+"%");
		ResultSet rs = this.pstmt.executeQuery();
		return 0;
	}
    public Set<String> photoHandle(String table,String photoColumn,String column,Set<?> ids) throws Exception {
        Set<String> all = new HashSet<String>() ;
        StringBuffer sql = new StringBuffer() ;
        sql.append("SELECT ").append(photoColumn).append(" FROM ").append(table).append(" WHERE ").append(column).append(" IN (") ;
        Iterator<?> iter = ids.iterator() ;
        while(iter.hasNext()) {
            sql.append(iter.next()).append(",") ;
        }
        sql.delete(sql.length()-1,sql.length()).append(")") ;
        sql.append(" AND ").append(photoColumn).append("<>'nophoto.jpg'") ;
        this.pstmt = this.conn.prepareStatement(sql.toString()) ;
        ResultSet rs = this.pstmt.executeQuery() ;
        while (rs.next()) {
            all.add(rs.getString(1)) ;
        }
        return all ;
    }	
}
