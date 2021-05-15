package com.example.medial.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medial.entity.Lead;
import com.example.medial.repository.LeadRepository;
import com.example.medial.service.LeadService;

@RestController
@RequestMapping("leads")
public class LeadRest {
	
	@Autowired
	LeadService leads;
	
	@GetMapping("/")
	public List<Lead> get()
	{
		return leads.findAll();
	}
	
	@GetMapping("/lead/{id}")
	public Lead getLead(@PathVariable int id)
	{
		Lead lead=leads.findById(id);
		return lead;
	}
	
	@DeleteMapping("lead/{id}")
	public String delete(@PathVariable int id)
	{	
		Lead lead=leads.findById(id);
		leads.delete(lead);
		return "Lead with "+lead.getLead_id()+" is deleted";
	}
	
	@PostMapping("/save")
	public String save(@RequestBody Lead lead)
	{
		leads.save(lead);
		return "New lead is saved";
	}
	
	@PutMapping("/update")
	public String update(@RequestBody Lead lead)
	{
		leads.update(lead);
		return "Lead is updated";
	}
	
}

