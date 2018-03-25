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

import com.book.console.dao.model.PaperFruit;
import com.book.console.dao.model.PatentFruit;
import com.book.console.service.PatentFruitService;
import com.book.console.web.support.JsonResult;
import com.book.console.web.support.LoginUser;
import com.book.console.web.support.annotaion.CurrentUser;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/patent/fruit")
public class PatentFruitController {

    private final static Logger logger = LoggerFactory.getLogger(PatentFruitController.class);
    @Autowired
    private PatentFruitService pfService;

    @RequestMapping("/all")
    public JsonResult all() {
        List<PatentFruit> businessPageInfo = pfService.selectAll();
        return JsonResult.success(businessPageInfo);
    }
    @RequestMapping("/search")
    public JsonResult search(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10") Integer size,
                                  @RequestParam(value = "name", defaultValue = "") String name) {
        Map likeMap = new HashMap<>();
        likeMap.put("patentNm", name);
        PageInfo<PatentFruit> businessPageInfo = pfService.selectByConditions(page,size,null,likeMap,PatentFruit.class);
        return JsonResult.success(businessPageInfo);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult add(@Valid @RequestBody PatentFruit PatentFruit, @CurrentUser LoginUser loginUser) {
    	PatentFruit.setCrtTime(new Date());
    	PatentFruit.setCrtUser(loginUser.getUsername());
    	PatentFruit.setUptUser(loginUser.getUsername());
    	PatentFruit.setUptTime(new Date());
    	PatentFruit.setFruitStatus("0");
    	pfService.insert(PatentFruit);
        return JsonResult.success(PatentFruit);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public JsonResult update(@PathVariable int id, @Valid @RequestBody PatentFruit business, @CurrentUser LoginUser loginUser) {
        business.setId(id);
        business.setUptTime(new Date());
        business.setUptUser(loginUser.getUsername());
        pfService.updateById(business);
        return JsonResult.success(business);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public JsonResult delete(@PathVariable int id, @Valid @RequestBody PatentFruit PatentFruit) {
    	PatentFruit.setId(id);
        pfService.deleteById(id);
        return JsonResult.success(PatentFruit);
    }
    
    @RequestMapping(value = "/upload/{id}", method = RequestMethod.PUT)
    public JsonResult upload(@PathVariable int id, @Valid @RequestBody PatentFruit PatentFruit) {
    	PatentFruit.setFruitStatus("1");
    		pfService.updateById(PatentFruit);
        return JsonResult.success(PatentFruit);
    }
}
