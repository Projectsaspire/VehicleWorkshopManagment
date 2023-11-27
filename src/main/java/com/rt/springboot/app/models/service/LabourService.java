package com.rt.springboot.app.models.service;

import java.util.List;

import com.rt.springboot.app.models.entity.Labour;

public interface LabourService {
	List<Labour> listAll();

	void save(Labour labour);

	Labour get(Long id);

	void delete(Long id);
	
	List<Labour> findByName(String term);
}
