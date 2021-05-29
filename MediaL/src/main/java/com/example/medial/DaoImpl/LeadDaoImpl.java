package com.example.medial.DaoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medial.IDao.LeadDao;
import com.example.medial.entity.Lead;
import com.example.medial.repository.LeadRepository;

@Service
public class LeadDaoImpl implements LeadDao{
		
	@Autowired
	private LeadRepository leadr;
	
	@Override
	public List<Lead> allLead()
	{
		return leadr.findAll();
	}
	
	@Override
	public void saveLead(Lead lead)
	{
		leadr.save(lead);
	}
	
	@Override
	public Lead getLead(int id)
	{
		Lead lead=leadr.findById(id).orElse(null);

		return lead;
	}
	
	@Override
	public void deleteLead(Lead lead)
	{
		leadr.delete(lead);
	}
	
	
}
