package com.wuxin.shop.dao;

import com.wuxin.shop.vo.Admin;

public interface IAdminDAO extends IDAO<String, Admin> {
	/**
	 * ʵ�ֹ���Ա��½����������ɹ�����һ�ε�½ʱ��ȡ��
	 * @param vo ����aid��password����
	 * @return ��½�ɹ��Ż�true�����򷵻�false
	 * @throws Exception
	 */
	public boolean findLogin(Admin vo) throws Exception;
	/**
	 * �������������һ�ε�½ʱ�䣬ֻ�贫�����Աaid�Ϳ���
	 * @param aid ����Աid
	 * @return ���³ɹ�����true�����򷵻�false
	 * @throws Exception
	 */
	public boolean doUpdateLastdate(String aid) throws Exception;
}
