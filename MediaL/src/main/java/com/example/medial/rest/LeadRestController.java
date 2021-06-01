package com.example.medial.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medial.IService.LeadService;
import com.example.medial.entity.Lead;
import com.example.medial.rest.exception.UserNotFoundException;

@RestController
@RequestMapping("leads")
public class LeadRestController {
	
	Logger logger =  LoggerFactory.getLogger(LeadRestController.class);
	
	@Autowired
	private LeadService leads;
	
	private RedisTemplate<String, Lead> redisTemplate;
	private HashOperations hashOperation;
	
	public LeadRestController(RedisTemplate<String, Lead> redisTemplate) {
		this.redisTemplate=redisTemplate;
		hashOperation=redisTemplate.opsForHash();
	}
	
	@GetMapping("/")
	public List<Lead> getAllLead()
	{
		logger.info("List of Leads :");
		return leads.findAll();
		//return (List<Lead>) hashOperation.entries("LEAD");
	}
	
	@GetMapping("/map")
	public Map<Integer, Lead> getAll(){
		return leads.rfindAll();
	}
	
	@GetMapping("/lead/{id}")
	public Lead getLead(@PathVariable int id) throws UserNotFoundException
	{
		Lead lead=null;
		lead=leads.rfindById(id);
	//	lead=leads.findById(id);
		if(lead==null)
		{
			logger.warn("Not found from redis CHECKING FROM mysql");
			lead=leads.findById(id);
			if(lead!=null) {
				logger.info("Found from mysql,adding into redis");
				hashOperation.put("LEAD", lead.getLead_id(), lead);
			}
			else {
				logger.warn("lead with id :"+id+" does not exist.");
				throw new UserNotFoundException("id: "+ id);
			}
		}

		logger.info("Showing lead with lead id :"+id);
		return lead;
		
	}	
	

	@DeleteMapping("lead/{id}")
	public String delete(@PathVariable int id)
	{	
		logger.info("Deleting lead with lead id :"+id);
	//	Lead lead=leads.findById(id);
	//	leads.delete(lead);
		
		Lead lead=leads.rfindById(id);
		Lead lead2=leads.findById(id);
		if( (lead!=null)&&(lead2!=null)&&(lead.getLead_id()==lead2.getLead_id()) ) {
			logger.info("Lead is present in mysql also.Deleting it");
		leads.rdelete(lead);
		leads.delete(lead2);
		}
		
		leads.rdelete(lead);
		
		return "Lead with "+lead.getLead_id()+" is deleted";
	}
	
	@PostMapping("/save")
	public Lead save(@RequestBody Lead lead)
	{
		logger.info("Saving Lead :");
	//	leads.save(lead);
		leads.rsave(lead);
		logger.info("Lead with id :"+lead.getLead_id()+" has been saved");
		return lead;
	}
	
	
	@PutMapping("/update")
	public String update(@RequestBody Lead lead)
	{
		logger.info("Updating Lead :");
		leads.rsave(lead);
		logger.info("Lead is updated");
		return "Lead is updated";
	}
	
/*	@GetMapping("/lead/{id}")
	public ResponseEntity<Lead> getLead(@PathVariable int id)
	{
		Lead lead=leads.findById(id);
		if(lead==null) 	{
			logger.debug("lead with id :"+id+" does not exist.");
			return new ResponseEntity<Lead>(HttpStatus.NOT_FOUND);
		}
		logger.info("Showing lead with lead id :"+id);
		return new ResponseEntity<Lead>(lead,HttpStatus.OK);
	}	*/
	
}

