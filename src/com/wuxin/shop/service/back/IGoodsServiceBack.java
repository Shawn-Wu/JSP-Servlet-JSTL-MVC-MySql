package com.wuxin.shop.service.back;

import java.util.Map;
import java.util.Set;

import com.wuxin.shop.vo.Goods;

public interface IGoodsServiceBack {
    /**
     * ��Ʒ����ǰ�����ݲ�ѯ������Ҫ��ѯ�����е���Ŀ��Ϣ��
     * <li>����IItemDAO.findAll()������ѯ��ȫ���ķ���</li>
     * @return ������Map������ʽ���أ��������������ݣ�<br>
     *     <li>key = allItems��value = IItemDAO.findAll()����ֵ</li>
     * @throws Exception
     */
    public Map<String,Object> insertPre() throws Exception ;

    /**
     * ��Ʒ���Ӳ��������ӵ�ʱ�����IGoodsDAO.doCreate()����
     * @param vo ������Ҫ������Ʒ����Ϣ
     * @return ���ӳɹ�����true�����򷵻�false
     * @throws Exception
     */
    public boolean insert(Goods vo) throws Exception ;

    /**
     * ��Ʒ��Ϣ�Ļ����б�������������µķ�����<br>
     *     <li>����IGoodsDAO.findAll()������ѯȫ������Ʒ��Ϣ</li>
     *     <li>����IGoodsDAO.getAllCount()����ͳ��ȫ��������</li>
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return ��Map���Ϸ��أ������������ݣ�<br>
     *     <li>key = allGoods��value = IGoodsDAO.findAll()</li>
     *     <li>key = goodsCount��value = IGoodsDAO.getAllCount()</li>
     * @throws Exception
     */
    public Map<String,Object> list(int currentPage,int lineSize,String column,String keyWord) throws Exception ;

    public Map<String,Object> listStatus(int status,int currentPage,int lineSize,String column,String keyWord) throws Exception ;

    public boolean updateUp(Set<Integer> gid) throws Exception ;
    public boolean updateDown(Set<Integer> gid) throws Exception ;
    public boolean updateDelete(Set<Integer> gid) throws Exception ;
    /**
     * ��Ʒ�޸�ǰ�����ݲ�ѯ������Ҫ��ѯ�����е���Ŀ��Ϣ��
     * <li>����IItemDAO.findAll()������ѯ��ȫ���ķ���</li>
     * <li>����IGoodsDAO.findById()������ѯ��ȫ���ķ���</li>
     * @return ������Map������ʽ���أ��������������ݣ�<br>
     *     <li>key = allItems��value = IItemDAO.findAll()����ֵ</li>
     *     <li>key = goods��value = IGoodsDAO.findById()����ֵ</li>
     * @throws Exception
     */
    public Map<String,Object> updatePre(int gid) throws Exception ;
    public boolean update(Goods vo) throws Exception ;

    /**
     * ִ�����ݵ�����ɾ��������������ɾ��֮��Ҫ�����Ӧ����ƷͼƬ��Ϣ��
     * @param ids
     * @return ���ص����ݰ������������ݣ�<br>
     *     <li>key = flag��value = IGoodsDAO.doRemoveBatch()</li>
     *     <li>key = allPhotos��value = IGoodsDAO.findAllByPhoto()</li>
     * @throws Exception
     */
    public Map<String,Object> deleteAll(Set<Integer> ids) throws Exception ;

    /**
     * ���ݱ��ɾ����Ʒ��Ϣ
     * @param id
     * @return
     * @throws Exception
     */
    public boolean delete(Set<Integer> id) throws Exception ;
}

