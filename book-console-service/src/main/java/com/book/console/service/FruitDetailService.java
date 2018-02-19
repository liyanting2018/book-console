package com.book.console.service;

import com.book.console.dao.model.FruitDetail;

public interface FruitDetailService extends BaseService<FruitDetail> {

	
	public FruitDetail getBusinessByCode(String code);

}
