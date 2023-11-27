package com.rt.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rt.springboot.app.models.entity.Labour;

@Repository
public interface ILabourDao extends JpaRepository<Labour, Long> {

	@Query("select l from Labour l where l.name like %?1%")
	public List<Labour> findByName(String term);
	
	
}
