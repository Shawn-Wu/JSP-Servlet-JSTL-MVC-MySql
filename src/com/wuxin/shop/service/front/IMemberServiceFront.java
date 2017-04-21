package com.wuxin.shop.service.front;

import com.wuxin.shop.vo.Member;

public interface IMemberServiceFront {
    /**
     * ʵ���û���ע�������ע��ʱҪ�ṩ��mid��password��regdate��code��status;<br>
     *     �����򽫵������µĲ�����<br>
     *     <li>����IMemberDAO.findById()�����ж�ע��mid�Ƿ���ڡ�</li>
     *     <li>����IMemberDAO.doCreate()�������������Ϣ</li>
     * @param vo ������ע����Ϣ��VO�����
     * @return ע��ɹ�����true�����򷵻�false
     * @throws Exception
     */
    public boolean regist(Member vo) throws Exception ;

    /**
     * ʵ��ע���û��ļ��������ֻ�м������û��ſ��Ե�¼��������Ҫ�������¹��ܣ�<br>
     *     <li>����IMemberDAO.findByCode()�����ж��û��ļ������Ƿ���ȷ��</li>
     *     <li>����IMemberDAO.doUpdateStatus()�����޸ļ�����</li>
     * @param vo ������mid��code������
     * @return ����ɹ�����true�����򷵻�false
     * @throws Exception
     */
    public boolean active(Member vo) throws Exception ;

    /**
     * �û���¼����������IMemberDAO.findLogin()������ֻ�ܵ�¼������û�
     * @param vo ������mid��password��VO�����
     * @return ��¼�ɹ�����true�����򷵻�false
     * @throws Exception
     */
    public boolean login(Member vo) throws Exception ;

    public Member updatePre(String mid) throws Exception ;
    public boolean update(Member vo) throws Exception ;
}
