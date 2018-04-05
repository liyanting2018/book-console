package com.book.console.service.impl;

import org.springframework.stereotype.Service;

import com.book.console.dao.model.PaperFruit;
import com.book.console.service.PaperFruitService;
@Service
public class PaperFruitServiceImpl extends BaseServiceImpl<PaperFruit> implements PaperFruitService {
	@Override
    public int insert(PaperFruit record) {
        return super.insert(record);
    }
    @Override
    public int updateById(PaperFruit record) {

        return super.updateById(record);
    }
	@Override
	public PaperFruit getPaperFruitById(String id) {
		
		return null;
	}
}
