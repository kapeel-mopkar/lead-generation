package com.example.medial.IDao;

import java.util.List;
import java.util.Map;

import com.example.medial.entity.Lead;

public interface LeadDao {

	List<Lead> allLead();
	void saveLead(Lead lead);
	Lead getLead(int id);
	void deleteLead(Lead lead);
	Map<Integer, Lead> rallLead();
	Lead rgetLead(int id);
	void rdeleteLead(Lead lead);
	void rsaveLead(Lead lead);
	
}
