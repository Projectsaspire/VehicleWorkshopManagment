package com.rt.springboot.app.models.service;

import java.util.List;

import com.rt.springboot.app.models.entity.Product;

public interface ProductService {

	List<Product> listAll();

	void save(Product product);

	Product get(Long id);

	void delete(Long id);
	
	List<Product> findByName(String term);
	

}
