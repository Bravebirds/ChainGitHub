package com.mryu.chainnews.entity;

import java.util.List;

/**
 *@内容 实体类所有的集合
 * Created by Administrator on 2018/10/15 0015.
 */

public class Result {
    //成功返回值
    private String stat;
    private List<Data> data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
