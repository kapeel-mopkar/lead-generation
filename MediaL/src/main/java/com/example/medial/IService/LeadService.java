package com.example.medial.IService;

import java.util.List;

import com.example.medial.entity.Lead;

public interface LeadService {
	
	List<Lead> findAll();
	void save(Lead entity);
	Lead findById(int id);
	void delete(Lead lead);
	void update(Lead lead);
}
