package com.book.console.service.impl;

import org.springframework.stereotype.Service;

import com.book.console.dao.model.BookFruit;
import com.book.console.dao.model.ProjectFruit;
import com.book.console.service.BookFruitService;
import com.book.console.service.ProjectFruitService;
@Service
public class ProjectFruitServiceImpl extends BaseServiceImpl<ProjectFruit> implements ProjectFruitService {
	@Override
    public int insert(ProjectFruit record) {
        return super.insert(record);
    }
    @Override
    public int updateById(ProjectFruit record) {

        return super.updateById(record);
    }
	@Override
	public ProjectFruit getProjectFruitById(String id) {
		
		return null;
	}
}
