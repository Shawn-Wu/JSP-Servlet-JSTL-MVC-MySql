package com.wuxin.shop.service.back;

import com.wuxin.shop.vo.Admin;

public interface IAdminServiceBack {
    /**
     * ʵ�ֹ���Ա�ĵ�¼������������Ҫִ�����´��룺<br>
     *     <li>����IAdminDAO.findLogin()����ȷ���û����������Ƿ���ȷ�������ȷ�������һ�ε�¼����</li>
     *     <li>����IAdminDAO.doUpdateLastdate()�������������һ�ε�¼���ڡ�</li>
     * @param vo ֻ������aid��password����
     * @return ��¼�ɹ�����true�����򷵻�false
     * @throws Exception
     */
    public boolean login(Admin vo) throws Exception ;
}
