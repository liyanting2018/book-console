package com.book.console.service.impl;

import org.springframework.stereotype.Service;

import com.book.console.dao.model.FruitCategory;
import com.book.console.service.FruitCategoryService;

/**
 * @version 1.0 
 * @since  
 * @decribtion 
 */
@Service
public class FruitCategoryServiceImpl extends BaseServiceImpl<FruitCategory> implements FruitCategoryService {
    @Override
    public int insert(FruitCategory record) {
        return super.insert(record);
    }
    @Override
    public int updateById(FruitCategory record) {

        return super.updateById(record);
    }
	@Override
	public FruitCategory getBusinessByCode(String code) {
		
		return null;
	}
}
