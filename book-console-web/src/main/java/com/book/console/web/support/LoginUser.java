package com.book.console.web.support;


import java.io.Serializable;

import com.book.console.dao.model.User;

/**
 * Created by zhengfeng on 2016-07-26 16:12.
 */
public class LoginUser implements Serializable {

    private User user;

    public LoginUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public Integer getRole() {
        return user.getRole();
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "username='" + user.getUsername() + '\'' +
                ", chineseName='" + user.getChineseName() + '\'' +
                ", role=" + user.getRole();
    }
}
