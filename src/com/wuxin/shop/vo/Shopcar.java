package com.wuxin.shop.vo;

import java.io.Serializable;

public class Shopcar implements Serializable{
    private Member member ;
    private Goods goods ;
    private Integer amount ;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

