package com.book.console.service;

import com.book.console.dao.model.User;
import com.github.pagehelper.PageInfo;

/**
 * Created by zino on 28/11/2016.
 */
public interface UserService extends BaseService<User> {
    PageInfo<User> findAll(String username, int page, int size);
    User findByUsername(String username);
    User add(User user);
    void updateRole(String username, int role);
    int teamCount(String team);
}

