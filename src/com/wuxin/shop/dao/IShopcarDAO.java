package com.wuxin.shop.dao;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import com.wuxin.shop.vo.Shopcar;

public interface IShopcarDAO extends IDAO<Integer,Shopcar>{
    /**
     * �����ظ�����ʱ��Ӧ�ý����е����ݽ�������
     * @param mid
     * @param gid
     * @return
     * @throws Exception
     */
    public boolean doUpdateAmount(String mid,Integer gid) throws SQLException;

    /**
     * �����û��������Ʒ��ţ���ѯ�����ﳵ����Ϣ
     * @param mid �û����
     * @param gid ��Ʒ���
     * @return
     * @throws Exception
     */
    public Shopcar findByMemberAndGoods(String mid,Integer gid) throws SQLException ;

    /**
     * ���һ���û������еĹ��ﳵ��Ϣ
     * @param mid �û����
     * @return ����ɹ�����true�����򷵻�false
     * @throws Exception
     */
    public boolean doRemoveByMember(String mid) throws SQLException ;

    /**
     * ���������µĹ��ﳵ����
     * @param vos
     * @return
     * @throws Exception
     */
    public boolean doCreateBatch(Set<Shopcar> vos) throws SQLException ;

    /**
     * ɾ��һ���û�һ����Ʒ�Ĺ��ﳵ��¼
     * @param mid
     * @param gid
     * @return
     * @throws Exception
     */
    public boolean doRemoveByMemberAndGoods(String mid,Set<Integer> gid) throws SQLException ;

    /**
     * һ���û������������Ʒ����Ϣ
     * @param mid
     * @return
     * @throws Exception
     */
    public Map<Integer,Integer> findAllByMember(String mid) throws SQLException ;
}
