package com.wuxin.shop.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.wuxin.shop.vo.Goods;

public interface IGoodsDAO extends IDAO<Integer, Goods> {
    public List<Goods> findAllByStatus(Integer status,Integer currentPage,Integer lineSize,String column,String keyWord) throws Exception ;
    public Integer getAllCountByStatus(Integer status,String column,String keyWord) throws Exception ;
    public boolean doUpdateStatus(Set<Integer> id,Integer status) throws Exception ;
    public Set<String> findAllByPhoto(Set<Integer> id) throws Exception ;

    /**
     * ������Ʒ�ķ�����״̬������Ʒ���б���ʾ
     * @param iid ��Ʒ����������
     * @param status ��Ʒ�ĵ�ǰ״̬
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public List<Goods> findAllByItem(Integer iid,Integer status,Integer currentPage,Integer lineSize,String column,String keyWord) throws Exception ;
    public Integer getAllCountByItem(Integer iid,Integer status,String column,String keyWord) throws Exception ;

    /**
     * ���·��ʴ�����ÿ�ε��÷��ʴ�����1
     * @param id
     * @return
     * @throws Exception
     */
    public boolean doUpdateBow(Integer id) throws Exception ;

    /**
     * ��ѯָ����ŵ�������Ʒ��Ϣ
     * @param ids
     * @return
     * @throws Exception
     */
    public List<Goods> findAllByGid(Set<Integer> ids) throws SQLException ;

    /**
     * Ҫ������Ʒ������ı��
     * @param gid
     * @param num
     * @return
     * @throws Exception
     */
    public boolean doUpdateByAmount(Integer gid,Integer num) throws SQLException;
}
