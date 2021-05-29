package com.example.medial.IDao;

import java.util.List;

import com.example.medial.entity.Lead;

public interface LeadDao {

	List<Lead> allLead();
	void saveLead(Lead lead);
	Lead getLead(int id);
	void deleteLead(Lead lead);
	
}
