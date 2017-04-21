package com.wuxin.shop.service.front;

import java.util.Map;

import com.wuxin.shop.vo.Goods;

public interface IGoodsServiceFront {
    /**
     * �ڽ���ȫ����Ʒ�б��ʱ��ʹ�ã�������Ҫִ�����µĵ��ã�<br>
     *     <li>Ҫ����IItemDAO.findAll()������ѯ��ȫ������Ʒ����</li>
     *     <li>Ҫ����IGoodsDAO.findAllByStatus()������ѯ��ȫ������</li>
     *     <li>Ҫ����IGoodsDAO.getAllCountByStatus()������ѯ��ȫ��������</li>
     *     <li>���õ�ʱ��status���õ�����Ϊ1����ʾ�ϼ���Ʒ</li>
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return ���ص�����һ���������������ݣ�<br>
     *     <li>key = allItems��value = IItemDAO.findAll()���������List<Item></li>
     *     <li>key = allGoods��value = IGoodsDAO.findAllByStatus���������List<Goods></li>
     *     <li>key = goodsCount��value = IGoodsDAO.getAllCountByStatus()���������Integer</li>
     * @throws Exception
     */
    public Map<String,Object> list(int currentPage,int lineSize,String column,String keyWord) throws Exception ;

    /**
     * �ڽ���ȫ����Ʒ�б��ʱ��ʹ�ã�������Ҫִ�����µĵ��ã�<br>
     *     <li>Ҫ����IItemDAO.findAll()������ѯ��ȫ������Ʒ����</li>
     *     <li>Ҫ����IGoodsDAO.findAllByItem()������ѯ��ȫ������</li>
     *     <li>Ҫ����IGoodsDAO.getAllCountByItem()������ѯ��ȫ��������</li>
     *     <li>���õ�ʱ��status���õ�����Ϊ1����ʾ�ϼ���Ʒ</li>
     * @param iid ��Ʒ���͵ı��
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return ���ص�����һ���������������ݣ�<br>
     *     <li>key = allItems��value = IItemDAO.findAll()���������List<Item></li>
     *     <li>key = allGoods��value = IGoodsDAO.findAllByItem���������List<Goods></li>
     *     <li>key = goodsCount��value = IGoodsDAO.getAllCountByItem()���������Integer</li>
     * @throws Exception
     */
    public Map<String,Object> listByItem(int iid,int currentPage,int lineSize,String column,String keyWord) throws Exception ;

    /**
     * ������Ҫ��ʾ��Ʒ��������Ϣ�������Ĳ������£�<br>
     *     <li>����IGoodsDAO.findById()�������Ը�����Ʒ��Ų�ѯ��������Ϣ</li>
     *     <li>����IGoodsDAO.doUpdateBow()���������·��ʴ���</li>
     *     <li>����IItemDAO.findById()��������ѯ����Ʒ�������������</li>
     * @param gid ��Ʒ���
     * @return ��Ʒ������������Ʒ�����з��أ���������ݷ���ʵ�������󣬷��򷵻�null
     * @throws Exception
     */
    public Goods show(int gid) throws Exception ;
}

