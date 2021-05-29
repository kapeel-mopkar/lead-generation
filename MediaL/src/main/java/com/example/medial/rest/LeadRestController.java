package com.example.medial.rest;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/")
	public List<Lead> getAllLead()
	{
		logger.info("List of Leads :");
		return leads.findAll();
	}
	
	@GetMapping("/lead/{id}")
	public Lead getLead(@PathVariable int id) throws UserNotFoundException
	{
		Lead lead=leads.findById(id);
		if(lead==null)
		{
			logger.warn("lead with id :"+id+" does not exist.");
			throw new UserNotFoundException("id: "+ id);
		}
		else {
		logger.info("Showing lead with lead id :"+id);
		return lead;
		}	
	}	
	

	@DeleteMapping("lead/{id}")
	public String delete(@PathVariable int id)
	{	
		logger.info("Deleting lead with lead id :"+id);
		Lead lead=leads.findById(id);
		leads.delete(lead);
		return "Lead with "+lead.getLead_id()+" is deleted";
	}
	
	@PostMapping("/save")
	public Lead save(@RequestBody Lead lead)
	{
		logger.info("Saving Lead :");
		leads.save(lead);
		logger.info("Lead with id :"+lead.getLead_id()+" has been saved");
		return lead;
	//	return "New lead is saved";
	}
	
	@PutMapping("/update")
	public String update(@RequestBody Lead lead)
	{
		logger.info("Updating Lead :");
		leads.update(lead);
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

