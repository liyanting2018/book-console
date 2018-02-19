package com.book.console.service;

import com.book.console.dao.model.FruitCategory;
import com.book.console.service.FruitCategoryService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class BusinessServiceTest {
    @Autowired
    private FruitCategoryService businessService;
    @Test
    public  void getVO(){
        businessService.findById(1);
    }

    @Test
    @Transactional
    public  void insertVo(){
        FruitCategory factorVo = new FruitCategory();
        
        businessService.insert(factorVo);
    }
    @Test
    @Transactional
    public  void updateVO(){
        FruitCategory factorVo = new FruitCategory();
        factorVo.setId(1);
        businessService.updateById(factorVo);
    }

    @Test
    @Transactional
    public  void getBusinessByCode(){
        businessService.getBusinessByCode("test");
    }
}
