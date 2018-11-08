package com.common.model;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

public class EasyPoiTestModel implements Serializable {

    @Excel(name = "用户名", orderNum = "5", width = 15.0)
    private String name;

    @Excel(name = "密码", orderNum = "6", width = 15.0)
    private String password;

    @Excel(name = "创建时间", orderNum = "7", width = 30.0, exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    public EasyPoiTestModel(String name, String password) {
        this.name = name;
        this.password = password;
        this.createdTime = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
