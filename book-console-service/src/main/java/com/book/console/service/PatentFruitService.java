package com.book.console.service;

import com.book.console.dao.model.PatentFruit;

public interface PatentFruitService extends BaseService<PatentFruit> {

	
	public PatentFruit getPatentFruitById(String code);

}
