package com.book.console.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.book.console.common.Constants;
import com.book.console.common.enums.Role;
import com.book.console.dao.mapper.UserMapper;
import com.book.console.dao.model.User;
import com.book.console.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Created by zino on 28/11/2016.
 */
@Service
public class UserSerivceImpl extends BaseServiceImpl<User> implements UserService, InitializingBean {

    @Resource
    private UserMapper mapper;

    private User admin;

    @Override
    public PageInfo<User> findAll(String username, int page, int size) {
        PageHelper.startPage(page, size, "id DESC");

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        if(!StringUtils.isBlank(username)) {
            criteria.andLike("username", "%"+username+"%");
        }

        List<User> users = mapper.selectByExample(example);

        return new PageInfo<>(users);
    }

    @Override
    public User findByUsername(String username) {

        if(Constants.ADMIN_USERNAME.equals(username)) {
            return admin;
        }

        User record = new User();
        record.setUsername(username);
        List<User> users = mapper.select(record);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User add(User user) {
        User existUser = this.findByUsername(user.getUsername());
        if(existUser == null) {
            user.setRole(Role.NORMAL.getRole());
            mapper.insert(user);
            return user;
        }
        existUser.setUsername(user.getUsername());
        existUser.setChineseName(user.getChineseName());
        existUser.setDepartment(user.getDepartment());
        existUser.setMobile(user.getMobile());
        existUser.setTitle(user.getTitle());

        mapper.updateByPrimaryKeySelective(existUser);

        return existUser;
    }

    @Override
    public void updateRole(String username, int role) {
        User user = this.findByUsername(username);
        user.setRole(role);
        mapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int teamCount(String team) {
        Example example = new Example(User.class);
        example.createCriteria().andLike("department", team + "%");
        return mapper.selectCountByExample(example);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        admin = new User();
        admin.setUsername(Constants.ADMIN_USERNAME);
        admin.setChineseName("超级管理员");
        admin.setRole(Role.ADMIN.getRole());
    }
}
