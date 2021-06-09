package com.example.medial.daoimpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.medial.entity.Lead;
import com.example.medial.idao.LeadDao;
import com.example.medial.repository.LeadRepository;

@Service
public class LeadDaoImpl implements LeadDao{
		
	private Logger logger=LoggerFactory.getLogger(LeadDaoImpl.class);
	
	@Autowired
	private LeadRepository leadr;
	
	private RedisTemplate<String, Lead> redisTemplate;
	private HashOperations hashOperation;
	
	public LeadDaoImpl(RedisTemplate<String, Lead> redisTemplate) {
		this.redisTemplate=redisTemplate;
		hashOperation=redisTemplate.opsForHash();
	}
	
	
	@Override
	public List<Lead> allLead()
	{
		return leadr.findAll();
	}
	
	@Override
	public Map<Integer,Lead> rallLead()
	{
		return hashOperation.entries("LEAD");
	}
	
	@Override
	public void saveLead(Lead lead)
	{
		leadr.save(lead);
	}
	
	@Override
	public void rsaveLead(Lead lead)
	{
		hashOperation.put("LEAD", lead.getLead_id(), lead);
	}
	
	@Override
	public Lead getLead(int id)
	{
		Lead lead=leadr.findById(id).orElse(null);
		return lead;
	}
	
	@Override
	public Lead rgetLead(int id)
	{
		logger.info("Calling from DB");
		Lead lead=(Lead) hashOperation.get("LEAD", id);
		return lead;
	}
	
	@Override
	public void deleteLead(Lead lead)
	{
		leadr.delete(lead);
	}
	
	@Override
	public void rdeleteLead(Lead lead)
	{
		hashOperation.delete("LEAD", lead.getLead_id());
	}
	
	
}
