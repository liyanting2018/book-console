package com.book.console.service.impl;

import org.springframework.stereotype.Service;

import com.book.console.dao.model.BookFruit;
import com.book.console.service.BookFruitService;
@Service
public class BookFruitServiceImpl extends BaseServiceImpl<BookFruit> implements BookFruitService {
	@Override
    public int insert(BookFruit record) {
        return super.insert(record);
    }
    @Override
    public int updateById(BookFruit record) {

        return super.updateById(record);
    }
	@Override
	public BookFruit getBookFruitById(String id) {
		
		return null;
	}
}
