package com.book.console.service.impl;

import org.springframework.stereotype.Service;

import com.book.console.dao.model.PatentFruit;
import com.book.console.service.PatentFruitService;
@Service
public class PatentFruitServiceImpl extends BaseServiceImpl<PatentFruit> implements PatentFruitService {
	@Override
    public int insert(PatentFruit record) {
        return super.insert(record);
    }
    @Override
    public int updateById(PatentFruit record) {

        return super.updateById(record);
    }
	@Override
	public PatentFruit getPatentFruitById(String id) {
		
		return null;
	}
}
