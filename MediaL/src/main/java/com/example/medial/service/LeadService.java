package com.example.medial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medial.entity.Lead;
import com.example.medial.repository.LeadRepository;

@Service
public class LeadService  {

	@Autowired
	LeadRepository leadr;
	
	public List<Lead> findAll() {
		return leadr.findAll();
	}

	
	public void save(Lead entity) {
		leadr.save(entity);
	}

	
	public Lead findById(int id) {
		Lead lead=leadr.findById(id).get();
		if(lead==null)
			throw new RuntimeException("USER NOT FOUND");
		return lead;
	}

	public void delete(Lead lead)
	{
		if(lead==null)
			throw new RuntimeException("USER NOT FOUND");
		leadr.delete(lead);
	}
	
	public void update(Lead lead)
	{
		leadr.save(lead);
	}
	

}
