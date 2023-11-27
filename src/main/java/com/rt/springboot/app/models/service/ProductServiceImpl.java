package com.rt.springboot.app.models.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rt.springboot.app.models.dao.IProductDao;
import com.rt.springboot.app.models.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private IProductDao productDao;

	
	@Override
	public List<Product> listAll() {
		return productDao.findAll();
	}
	@Override
	public void save(Product product) {
		productDao.save(product);
		
	}
	@Override
	public Product get(Long id) {
		return productDao.findById(id).get();
	}
	@Override
	
	public void delete(Long id) {
		productDao.deleteById(id);
	}
	@Override
	@Transactional(readOnly = true)
	public List<Product> findByName(String term) {
		return productDao.findByName(term);
	}
}
