package com.example.fei.domain;

import com.alibaba.druid.support.monitor.annotation.MTable;

// 这一层常用名字 model bean domain
public class User {

    private long id;
    private String username;
    private String password;
    private String email;
    private String mobile;

    // fei_tip: 自定义数据库映射字段,在 UserMapper 中写对应映射关系
    private String foo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

}
