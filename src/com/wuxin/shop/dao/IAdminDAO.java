package com.wuxin.shop.dao;

import com.wuxin.shop.vo.Admin;

public interface IAdminDAO extends IDAO<String, Admin> {
	/**
	 * 实现管理员登陆操作，登入成功后将上一次登陆时间取出
	 * @param vo 包含aid，password数据
	 * @return 登陆成功放回true，否则返回false
	 * @throws Exception
	 */
	public boolean findLogin(Admin vo) throws Exception;
	/**
	 * 本操作更新最后一次登陆时间，只需传入管理员aid就可以
	 * @param aid 管理员id
	 * @return 更新成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean doUpdateLastdate(String aid) throws Exception;
}
