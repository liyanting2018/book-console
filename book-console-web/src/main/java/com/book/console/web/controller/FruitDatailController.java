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

import com.book.console.dao.model.FruitDetail;
import com.book.console.service.FruitDetailService;
import com.book.console.web.support.JsonResult;
import com.book.console.web.support.LoginUser;
import com.book.console.web.support.annotaion.CurrentUser;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/fruit/detail")
public class FruitDatailController {
	 private final static Logger logger = LoggerFactory.getLogger(FruitDatailController.class);
	    @Autowired
	    private FruitDetailService fruitDetailService;

	    @RequestMapping("/all")
	    public JsonResult all() {
	        List<FruitDetail> businessPageInfo = fruitDetailService.selectAll();
	        return JsonResult.success(businessPageInfo);
	    }
	    @RequestMapping("/search")
	    public JsonResult search(@RequestParam(value = "page", defaultValue = "1") Integer page,
	                                  @RequestParam(value = "size", defaultValue = "10") Integer size,
	                                  @RequestParam(value = "name", defaultValue = "") String name) {
	        Map likeMap = new HashMap<>();
	        likeMap.put("fruitNm", name);
	        Map eqMap = new HashMap<>();
	        PageInfo<FruitDetail> businessPageInfo = fruitDetailService.selectByConditions(page,size,eqMap,likeMap,FruitDetail.class);
	        return JsonResult.success(businessPageInfo);
	    }
	    @RequestMapping(value = "/add", method = RequestMethod.POST)
	    public JsonResult add(@Valid @RequestBody FruitDetail fruitDetail, @CurrentUser LoginUser loginUser) {
	    	fruitDetail.setCrtTime(new Date());
	    	fruitDetail.setCrtUser(loginUser.getUsername());
	    	fruitDetail.setCupUser(loginUser.getUsername());
	    	fruitDetail.setUptTime(new Date());
	    	fruitDetail.setFruitStatus("0");
	    	fruitDetailService.insert(fruitDetail);
	        return JsonResult.success(fruitDetail);
	    }

	    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	    public JsonResult update(@PathVariable int id, @Valid @RequestBody FruitDetail business, @CurrentUser LoginUser loginUser) {
	        business.setId(id);
	        business.setUptTime(new Date());
	        business.setCupUser(loginUser.getUsername());
	        fruitDetailService.updateById(business);
	        return JsonResult.success(business);
	    }

	    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
	    public JsonResult delete(@PathVariable int id, @Valid @RequestBody FruitDetail fruitDetail) {
	    	fruitDetail.setId(id);
	    	fruitDetailService.deleteById(id);
	        return JsonResult.success(fruitDetail);
	    }
	    
	    @RequestMapping(value = "/upload/{id}", method = RequestMethod.PUT)
	    public JsonResult upload(@PathVariable int id, @Valid @RequestBody FruitDetail fruitDetail) {
	    	fruitDetail.setId(id);
	    	fruitDetail.setFruitStatus("1");//0 未提交 1 已提交 2审核通过 3 审核未通过
	    	fruitDetailService.updateById(fruitDetail);
	        return JsonResult.success(fruitDetail);
	    }
}
