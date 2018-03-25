package com.book.console.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.book.console.dao.model.BookFruit;
import com.book.console.dao.model.PatentFruit;
import com.book.console.service.BookFruitService;
import com.book.console.web.support.JsonResult;
import com.book.console.web.support.LoginUser;
import com.book.console.web.support.annotaion.CurrentUser;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/book/fruit")
public class BookFruitController {

    private final static Logger logger = LoggerFactory.getLogger(BookFruitController.class);
    @Autowired
    private BookFruitService bfService;

    @RequestMapping("/all")
    public JsonResult all() {
        List<BookFruit> businessPageInfo = bfService.selectAll();
        return JsonResult.success(businessPageInfo);
    }
    @RequestMapping("/search")
    public JsonResult search(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10") Integer size,
                                  @RequestParam(value = "name", defaultValue = "") String name) {
        Map likeMap = new HashMap<>();
        likeMap.put("bookNm", name);
        PageInfo<BookFruit> businessPageInfo = bfService.selectByConditions(page,size,null,likeMap,BookFruit.class);
        return JsonResult.success(businessPageInfo);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult add(@Valid @RequestBody BookFruit BookFruit, @CurrentUser LoginUser loginUser) {
    	BookFruit.setCrtTime(new Date());
    	BookFruit.setCrtUser(loginUser.getUsername());
    	BookFruit.setUptUser(loginUser.getUsername());
    	BookFruit.setUptTime(new Date());
    	BookFruit.setFruitStatus("0");
    	bfService.insert(BookFruit);
        return JsonResult.success(BookFruit);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public JsonResult update(@PathVariable int id, @Valid @RequestBody BookFruit business, @CurrentUser LoginUser loginUser) {
        business.setId(id);
        business.setUptTime(new Date());
        business.setUptUser(loginUser.getUsername());
        bfService.updateById(business);
        return JsonResult.success(business);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public JsonResult delete(@PathVariable int id, @Valid @RequestBody BookFruit BookFruit) {
    	BookFruit.setId(id);
        bfService.deleteById(id);
        return JsonResult.success(BookFruit);
    }
    
    
    @RequestMapping(value = "/upload/{id}", method = RequestMethod.PUT)
    public JsonResult upload(@PathVariable int id, @Valid @RequestBody BookFruit BookFruit) {
    	BookFruit.setFruitStatus("1");
    		bfService.updateById(BookFruit);
        return JsonResult.success(BookFruit);
    }
}
