package com.wuxin.shop.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Admin implements Serializable {
    private String aid ;
    private String password ;
    private Date lastdate ;
    private List<Goods> allGoods ;

    public String getAid() {
        return aid;
    }

    public List<Goods> getAllGoods() {
        return allGoods;
    }

    public void setAllGoods(List<Goods> allGoods) {
        this.allGoods = allGoods;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastdate() {
        return lastdate;
    }

    public void setLastdate(Date lastdate) {
        this.lastdate = lastdate;
    }
}
