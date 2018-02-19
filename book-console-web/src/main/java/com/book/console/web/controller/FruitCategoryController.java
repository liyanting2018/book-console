package com.book.console.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.console.dao.model.FruitCategory;
import com.book.console.service.FruitCategoryService;
import com.book.console.web.support.JsonResult;
import com.book.console.web.support.LoginUser;
import com.book.console.web.support.annotaion.CurrentUser;
import com.github.pagehelper.PageInfo;

/**
 * com.pajk.skyeye.web.controller
 * Created by zhengfeng on 2016-08-05 14:33.
 */
@RestController
@RequestMapping("/fruit/category")
public class FruitCategoryController {

    private final static Logger logger = LoggerFactory.getLogger(FruitCategoryController.class);
    @Autowired
    private FruitCategoryService fruitCategoryService;

    @RequestMapping("/all")
    public JsonResult all() {
        List<FruitCategory> businessPageInfo = fruitCategoryService.selectAll();
        return JsonResult.success(businessPageInfo);
    }
    @RequestMapping("/search")
    public JsonResult search(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10") Integer size,
                                  @RequestParam(value = "name", defaultValue = "") String name) {
        Map likeMap = new HashMap<>();
        likeMap.put("fruitCategory", name);
        PageInfo<FruitCategory> businessPageInfo = fruitCategoryService.selectByConditions(page,size,null,likeMap,FruitCategory.class);
        return JsonResult.success(businessPageInfo);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult add(@Valid @RequestBody FruitCategory fruitCategory, @CurrentUser LoginUser loginUser) {
    	fruitCategory.setCrtTime(new Date());
    	fruitCategory.setCrtUser(loginUser.getUsername());
    	fruitCategory.setCupUser(loginUser.getUsername());
    	fruitCategory.setUptTime(new Date());
    	fruitCategoryService.insert(fruitCategory);
        return JsonResult.success(fruitCategory);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public JsonResult update(@PathVariable int id, @Valid @RequestBody FruitCategory business, @CurrentUser LoginUser loginUser) {
        business.setId(id);
        business.setUptTime(new Date());
        business.setCupUser(loginUser.getUsername());
        fruitCategoryService.updateById(business);
        return JsonResult.success(business);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public JsonResult delete(@PathVariable int id, @Valid @RequestBody FruitCategory fruitCategory) {
    	fruitCategory.setId(id);
        fruitCategoryService.deleteById(id);
        return JsonResult.success(fruitCategory);
    }
}
