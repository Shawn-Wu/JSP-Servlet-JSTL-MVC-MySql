package com.wuxin.shop.service.back;

import java.util.Map;
import java.util.Set;

import com.wuxin.shop.vo.Goods;

public interface IGoodsServiceBack {
    /**
     * 商品增加前的数据查询操作，要查询出所有的栏目信息：
     * <li>调用IItemDAO.findAll()方法查询出全部的分类</li>
     * @return 数据以Map集合形式返回，包含有如下内容：<br>
     *     <li>key = allItems、value = IItemDAO.findAll()返回值</li>
     * @throws Exception
     */
    public Map<String,Object> insertPre() throws Exception ;

    /**
     * 商品增加操作，增加的时候调用IGoodsDAO.doCreate()方法
     * @param vo 包含有要增加商品的信息
     * @return 增加成功返回true，否则返回false
     * @throws Exception
     */
    public boolean insert(Goods vo) throws Exception ;

    /**
     * 商品信息的基础列表操作，调用如下的方法：<br>
     *     <li>调用IGoodsDAO.findAll()方法查询全部的商品信息</li>
     *     <li>调用IGoodsDAO.getAllCount()方法统计全部数据量</li>
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return 以Map集合返回，包括如下内容：<br>
     *     <li>key = allGoods、value = IGoodsDAO.findAll()</li>
     *     <li>key = goodsCount、value = IGoodsDAO.getAllCount()</li>
     * @throws Exception
     */
    public Map<String,Object> list(int currentPage,int lineSize,String column,String keyWord) throws Exception ;

    public Map<String,Object> listStatus(int status,int currentPage,int lineSize,String column,String keyWord) throws Exception ;

    public boolean updateUp(Set<Integer> gid) throws Exception ;
    public boolean updateDown(Set<Integer> gid) throws Exception ;
    public boolean updateDelete(Set<Integer> gid) throws Exception ;
    /**
     * 商品修改前的数据查询操作，要查询出所有的栏目信息：
     * <li>调用IItemDAO.findAll()方法查询出全部的分类</li>
     * <li>调用IGoodsDAO.findById()方法查询出全部的分类</li>
     * @return 数据以Map集合形式返回，包含有如下内容：<br>
     *     <li>key = allItems、value = IItemDAO.findAll()返回值</li>
     *     <li>key = goods、value = IGoodsDAO.findById()返回值</li>
     * @throws Exception
     */
    public Map<String,Object> updatePre(int gid) throws Exception ;
    public boolean update(Goods vo) throws Exception ;

    /**
     * 执行数据的批量删除操作，但是在删除之后要清除对应的商品图片信息。
     * @param ids
     * @return 返回的数据包含有两项内容：<br>
     *     <li>key = flag、value = IGoodsDAO.doRemoveBatch()</li>
     *     <li>key = allPhotos、value = IGoodsDAO.findAllByPhoto()</li>
     * @throws Exception
     */
    public Map<String,Object> deleteAll(Set<Integer> ids) throws Exception ;

    /**
     * 根据编号删除商品信息
     * @param id
     * @return
     * @throws Exception
     */
    public boolean delete(Set<Integer> id) throws Exception ;
}

