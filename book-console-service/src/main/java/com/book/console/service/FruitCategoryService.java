package com.book.console.service;

import com.book.console.dao.model.FruitCategory;

/**
 * @version 1.0 
 * @since  
 * @decribtion 
 */
public interface FruitCategoryService extends BaseService<FruitCategory> {
	/**    
	   
	 * getBusinessByCode
	
	 * @param code
	 * @return Business   
	   
	 * @throws    
	   
	 * @since    
	   
	*/
	public FruitCategory getBusinessByCode(String code);

}
