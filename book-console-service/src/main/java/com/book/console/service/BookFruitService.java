package com.book.console.service;

import com.book.console.dao.model.BookFruit;

public interface BookFruitService extends BaseService<BookFruit> {

	
	public BookFruit getBookFruitById(String id);

}
