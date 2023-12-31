package com.rt.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rt.springboot.app.models.entity.Product;

@Repository
public interface IProductDao extends JpaRepository<Product, Long>{
	
	@Query("select p from Product p where p.name like %?1%")
	public List<Product> findByName(String term);
	
}
