package com.wuxin.shop.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.wuxin.shop.vo.Member;

public interface IMemberDAO extends IDAO<String, Member> {
    public Member findById2(String mid) throws SQLException ;
    /**
     * �жϸ�����mid�������code�Ƿ���ͬ
     * @param mid Ҫ������û�id
     * @param code ���úõļ�����
     * @return ����û�id�뼤������ƥ������Է���true�����򷵻�false
     */
    public boolean findByCode(String mid,String code) throws Exception ;

    /**
     * ����ָ���û���״̬����
     * @param mid �û�id
     * @param status �û�״̬��0��ʾ������1��ʾ���2��ʾ�ȴ����
     * @return
     * @throws Exception
     */
    public boolean doUpdateStatus(String mid,Integer status) throws Exception ;

    /**
     * �û��ĵ�¼��������������¼����Բ�ѯ���û�����Ƭ��Ϣ��
     * ���ڲ������յ���Member�������Կ���ֱ�ӽ���Ƭ��Ϣ������Member����
     * @param vo ������mid��password��VO�����
     * @return ��¼�ɹ�����true�����򷵻�false
     * @throws Exception
     */
    public boolean findLogin(Member vo) throws Exception ;

    /**
     * �����û���״̬���������ݵ��б����
     * @param status
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public List<Member> findAllByStatus(Integer status,Integer currentPage,Integer lineSize,String column,String keyWord) throws Exception ;

    /**
     * �����û���״̬ͳ�����е�������
     * @param status
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public Integer getAllCountByStatus(Integer status,String column,String keyWord) throws Exception ;

    /**
     * �ǽ������ݵ��������£�״̬���ⲿ����
     * @param ids
     * @param status
     * @return
     * @throws Exception
     */
    public boolean doUpdateStatus(Set<String> ids, Integer status) throws Exception ;

    public boolean doUpdateMember(Member vo) throws Exception ;
}
