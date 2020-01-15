package com.ltzz.modules.alipay.service.impl;

import com.ltzz.modules.alipay.dao.ProductDao;
import com.ltzz.modules.alipay.entity.Product;
import com.ltzz.modules.alipay.entity.ProductExample;
import com.ltzz.modules.alipay.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Product> getProducts() {
		
		ProductExample pe = new ProductExample();
//		Criteria pc = pe.createCriteria();
		List<Product> list = productDao.selectByExample(pe);
		
		return list;
	}

	@Override
	public Product getProductById(String productId) {
		
		return productDao.selectByPrimaryKey(productId);
	}

}
