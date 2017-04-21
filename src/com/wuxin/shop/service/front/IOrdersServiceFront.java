package com.wuxin.shop.service.front;

import java.sql.SQLException;
import java.util.Map;

import com.wuxin.shop.exception.EmptyShopcarException;
import com.wuxin.shop.exception.UnCompleteMemberInfomrationException;
import com.wuxin.shop.exception.UnEnoughAmountException;
import com.wuxin.shop.vo.Orders;

public interface IOrdersServiceFront {
    /**
     * ������������
     * @param mid Ҫ�����������û�ID
     * @return ������������ɹ��򷵻�true�����򷵻�false
     * @throws UnCompleteMemberInfomrationException ������Ϣ������ʱ�׳��쳣
     * @throws UnEnoughAmountException û���㹻�Ŀ����ʱ�׳��쳣
     * @throws EmptyShopcarException ���ﳵû������κ���Ʒʱ�׳��쳣
     * @throws SQLException JDBC��������ɵ��쳣
     */
    public boolean insert(String mid) throws UnCompleteMemberInfomrationException, UnEnoughAmountException, EmptyShopcarException, SQLException;

    /**
     * �鿴һ���û����ж�����Ϣ
     * @param mid
     * @return
     * @throws Exception
     */
    public Map<String,Object> listByMember(String mid,int currentPage,int lineSize) throws Exception ;

    /**
     * �鿴һ��������Ϣ�Լ�������Ӧ������������Ϣ
     * @param mid
     * @param oid
     * @return
     * @throws Exception
     */
    public Orders show(String mid,int oid) throws Exception ;
}
