package com.wuxin.shop.service.back;

import java.util.Map;
import java.util.Set;

import com.wuxin.shop.vo.Member;

public interface IMemberServiceBack {
    /**
     * ����ȫ���û��ķ�ҳ������ʾ��Ҫ����IMemberDAO�����²�����<br>
     *     <li>����IMemberDAO.findAll()��������ѯ��ȫ������</li>
     *     <li>����IMemberDAO.getAllCount()������ͳ��ȫ��������</li>
     * @param column ģ����ѯ��
     * @param keyWord ģ����ѯ�ؼ���
     * @param currentPage ��ǰ����ҳ
     * @param lineSize ÿҳ��ʾ��������
     * @return ����Ҫ���ص�������List��Integer�������ͣ�����ʹ��Map���أ������������ݣ�<br>
     *     <li>key = allMembers��value = findAllSplit()</li>
     *     <li>key = memberCount��value = getAllCount()</li>
     * @throws Exception
     */
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception;
    /**
     * ����ȫ���û��ķ�ҳ������ʾ��Ҫ����IMemberDAO�����²�����<br>
     *     <li>����IMemberDAO.findAllByStatus()��������ѯ��ȫ������</li>
     *     <li>����IMemberDAO.getAllCountByStatus()������ͳ��ȫ��������</li>
     * @param column ģ����ѯ��
     * @param keyWord ģ����ѯ�ؼ���
     * @param currentPage ��ǰ����ҳ
     * @param lineSize ÿҳ��ʾ��������
     * @return ����Ҫ���ص�������List��Integer�������ͣ�����ʹ��Map���أ������������ݣ�<br>
     *     <li>key = allMembers��value = findAllByStatus()</li>
     *     <li>key = memberCount��value = getAllCountByStatus()</li>
     * @throws Exception
     */
    public Map<String, Object> listByStatus(int status,int currentPage, int lineSize, String column, String keyWord) throws Exception;

    /**
     * ���û�״̬����Ϊ����״̬��status = 1��
     * @param ids
     * @return
     * @throws Exception
     */
    public boolean updateActive(Set<String> ids) throws Exception ;

    /**
     * ���û�״̬����Ϊ����״̬��status = 0��
     * @param ids
     * @return
     * @throws Exception
     */
    public boolean updateLock(Set<String> ids) throws Exception ;

    /**
     * �鿴һ����Ա��������Ϣ
     * @param id
     * @return
     * @throws Exception
     */
    public Member show(String id) throws Exception ;
}

