package com.rt.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rt.springboot.app.models.dao.ILabourDao;
import com.rt.springboot.app.models.entity.Labour;

@Service
public class LabourServiceImpl implements LabourService{

	@Autowired
	private ILabourDao labourDao;
	
	@Override
	public List<Labour> listAll() {
		
		return labourDao.findAll();
	}

	@Override
	public void save(Labour labour) {
		labourDao.save(labour);
		
	}

	@Override
	public Labour get(Long id) {
		
		return labourDao.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		labourDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Labour> findByName(String term) {
		// TODO Auto-generated method stub
		return labourDao.findByName(term);
	}
	

}
