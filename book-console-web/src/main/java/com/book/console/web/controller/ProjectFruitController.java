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

import com.book.console.dao.model.ProjectFruit;
import com.book.console.service.ProjectFruitService;
import com.book.console.web.support.JsonResult;
import com.book.console.web.support.LoginUser;
import com.book.console.web.support.annotaion.CurrentUser;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/project/fruit")
public class ProjectFruitController {

    private final static Logger logger = LoggerFactory.getLogger(ProjectFruitController.class);
    @Autowired
    private ProjectFruitService pfService;

    @RequestMapping("/all")
    public JsonResult all() {
        List<ProjectFruit> businessPageInfo = pfService.selectAll();
        return JsonResult.success(businessPageInfo);
    }
    @RequestMapping("/search")
    public JsonResult search(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10") Integer size,
                                  @RequestParam(value = "name", defaultValue = "") String name) {
        Map likeMap = new HashMap<>();
        likeMap.put("projectNm", name);
        PageInfo<ProjectFruit> businessPageInfo = pfService.selectByConditions(page,size,null,likeMap,ProjectFruit.class);
        return JsonResult.success(businessPageInfo);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult add(@Valid @RequestBody ProjectFruit ProjectFruit, @CurrentUser LoginUser loginUser) {
    	ProjectFruit.setCrtTime(new Date());
    	ProjectFruit.setCrtUser(loginUser.getUsername());
    	ProjectFruit.setUptUser(loginUser.getUsername());
    	ProjectFruit.setUptTime(new Date());
    	ProjectFruit.setFruitStatus("0");
    	pfService.insert(ProjectFruit);
        return JsonResult.success(ProjectFruit);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public JsonResult update(@PathVariable int id, @Valid @RequestBody ProjectFruit business, @CurrentUser LoginUser loginUser) {
        business.setId(id);
        business.setUptTime(new Date());
        business.setUptUser(loginUser.getUsername());
        pfService.updateById(business);
        return JsonResult.success(business);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public JsonResult delete(@PathVariable int id, @Valid @RequestBody ProjectFruit ProjectFruit) {
    	ProjectFruit.setId(id);
        pfService.deleteById(id);
        return JsonResult.success(ProjectFruit);
    }
    @RequestMapping(value = "/upload/{id}", method = RequestMethod.PUT)
    public JsonResult upload(@PathVariable int id, @Valid @RequestBody ProjectFruit ProjectFruit) {
    		ProjectFruit.setFruitStatus("1");
    		pfService.updateById(ProjectFruit);
        return JsonResult.success(ProjectFruit);
    }
}
