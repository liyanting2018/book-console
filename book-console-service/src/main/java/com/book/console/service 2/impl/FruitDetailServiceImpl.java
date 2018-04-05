package com.book.console.service.impl;

import org.springframework.stereotype.Service;

import com.book.console.dao.model.FruitDetail;
import com.book.console.service.FruitDetailService;
@Service
public class FruitDetailServiceImpl extends BaseServiceImpl<FruitDetail> implements FruitDetailService {
	@Override
    public int insert(FruitDetail record) {
        return super.insert(record);
    }
    @Override
    public int updateById(FruitDetail record) {

        return super.updateById(record);
    }
	@Override
	public FruitDetail getBusinessByCode(String code) {
		
		return null;
	}
}
