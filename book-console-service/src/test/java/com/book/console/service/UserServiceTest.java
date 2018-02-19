package com.book.console.service;

import com.book.console.dao.model.User;
import com.book.console.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public  void selectAll(){
        userService.selectAll();
    }

    @Test
    public  void select(){
        User user = new User();
        userService.select(user);
    }
    @Test
    public  void findOne(){
        User user = new User();
        userService.findOne(user);
    }
    @Test
    public  void findById(){
        User user = userService.findById(1);
        System.err.print(user);
    }

    @Test
    public  void selectPage(){
        User user = new User();
        userService.select(user,0,10);
    }

    @Test
    public  void selectPgeOrder(){
        User user = new User();
        userService.select(user,0,10,"id desc");
    }

    @Test
    public  void findByUsername(){
        User user = new User();
        userService.findByUsername("bd");
    }

    @Test
    public  void selectByCondition(){
        userService.selectByCondition(null,null,User.class);
    }

    @Test
    public  void selectByConditions(){
        userService.selectByConditions(0,100,null,null,User.class);
    }
    @Test
    public  void findAll(){
        userService.findAll("",0,10);
    }
    @Test
    @Transactional
    public  void add(){
        User user = new User();
        user.setChineseName("dd");
        user.setUsername("dd");
        user.setRole(1);
        user.setTitle("111");
        user.setMobile("13101021415");
        userService.add(user);
    }
    @Test
    @Transactional
    public  void update(){
        User user = new User();
        user.setId(1);
        user.setChineseName("dd");
        user.setUsername("dd");
        user.setRole(1);
        user.setTitle("111");
        user.setMobile("13101021415");
        userService.updateById(user);
    }
}
