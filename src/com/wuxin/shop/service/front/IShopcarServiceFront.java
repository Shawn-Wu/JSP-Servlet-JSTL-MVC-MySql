package com.wuxin.shop.service.front;

import java.util.Map;
import java.util.Set;

import com.wuxin.shop.vo.Shopcar;

public interface IShopcarServiceFront {
    /**
     * ��Ҫʵ�ֹ��ﳵ���ݵ����ӣ��߱����µĲ����߼���<br>
     *     <li>����Ҫ��ѯ���ӵĹ��ﳵ�����Ƿ��Ѿ����ڣ������������б���������</li>
     *     <li>���Ҫ�������Ϣ�����ڣ��򱣴�һ���µ����ݣ�����������1</li>
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean addCar(Shopcar vo) throws Exception ;

    /**
     * ͨ����ǰ���û�id����ѯ�����еĹ������Ʒ���������¹��ܣ�<br>
     *     <li>�����û�id��ѯ�����еĹ��ﳵ�еĹ����¼��</li>
     *     <li>�����¼������ȫ����Ʒ��ţ��Ϳ��Բ�ѯ��ȫ������Ʒ������Ϣ</li>
     *     <li>����Ҫ���ǵ����ݵĻ������⣬����Ӧ����������ʾ�����еĹ����¼</li>
     * @param mid �û�id
     * @return �������������ݣ�<br>
     *     <li>key = allShopcars��value = IShopcar.findAllByMember()��Map<Integer,Integer></li>
     *     <li>key = allGoods��value = IGoodsDAO.findAllByGid��List<Goods></li>
     * @throws Exception
     */
    public Map<String,Object> listCar(String mid) throws Exception ;

    /**
     * ����IShopcar.doRemoveByMemberAndGoods()������ɾ����ָ��һ���û�����Ʒ����Ϣ
     * @param mid
     * @param gid
     * @return
     * @throws Exception
     */
    public boolean deleteCar(String mid,Set<Integer> gid) throws Exception ;

    /**
     * ���¹����������Ʒ��Ϣ������֮ǰ��Ҫ��ɾ�������еĹ�����Ϣ�����󱣴��µĹ�����Ϣ
     * @param mid
     * @param vos
     * @return
     * @throws Exception
     */
    public boolean update(String mid,Set<Shopcar> vos) throws Exception ;
}
