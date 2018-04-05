package com.book.console.service;

import com.book.console.dao.model.ProjectFruit;

public interface ProjectFruitService extends BaseService<ProjectFruit> {

	
	public ProjectFruit getProjectFruitById(String code);

}
