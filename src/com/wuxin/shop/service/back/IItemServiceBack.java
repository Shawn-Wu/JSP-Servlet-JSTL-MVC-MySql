package com.wuxin.shop.service.back;

import java.util.List;
import java.util.Set;

import com.wuxin.shop.vo.Item;

public interface IItemServiceBack {
    public boolean insert(Item vo) throws Exception ;
    public boolean update(Item vo) throws Exception ;
    public boolean delete(Set<Integer> ids) throws Exception ;
    public List<Item> list() throws Exception ;
}

