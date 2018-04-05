package com.book.console.service;

import com.book.console.dao.model.PaperFruit;

public interface PaperFruitService extends BaseService<PaperFruit> {

	
	public PaperFruit getPaperFruitById(String id);

}
