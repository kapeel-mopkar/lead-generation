package com.example.medial.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medial.IDao.LeadDao;
import com.example.medial.IService.LeadService;
import com.example.medial.entity.Lead;

@Service
public class LeadServiceImpl implements LeadService {

	@Autowired
	private LeadDao leadr;
	
	@Override
	public List<Lead> findAll() {
		return leadr.allLead();
	}

	@Override
	public void save(Lead entity) {
		leadr.saveLead(entity);
	}

	@Override
	public Lead findById(int id) {
		Lead lead=leadr.getLead(id);
		
//		if(lead==null)
//			throw new RuntimeException("USER NOT FOUND");
		return lead;
	}
	
	@Override
	public void delete(Lead lead)
	{
		if(lead==null)
			throw new RuntimeException("USER NOT FOUND");
		leadr.deleteLead(lead);
	}
	
	@Override
	public void update(Lead lead)
	{
		leadr.saveLead(lead);
	}
	

}
