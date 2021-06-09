package com.example.medial.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.medial.entity.User;
import com.example.medial.iservice.UserService;

@Controller
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserService users;
	
	@GetMapping("create")
	public String createUser(Model model)
	{
		User user=new User();
		model.addAttribute("user", user);
		return "mvcform";
	} 
	
	@PostMapping("/process")
	public String processReg(@ModelAttribute("user") User user)
	{
		//user.setPass(encoder.encode(user.getPass()));
		users.save(user);
		return "confirmed";
	}
	
	@GetMapping("/activate/{id}")
	public String setEnable(@PathVariable int id,Model model)
	{
		User user=users.getUser(id);
		model.addAttribute("id", id);
		if(user.isEnable())
			return "activate";
		else
			user.setEnable(true);
		users.save(user);
		
		return "complete";
	}
	
	@GetMapping("/deactivate/{id}")
	public String setDisable(@PathVariable int id,Model model)
	{
		User user=users.getUser(id);
		model.addAttribute("id", id);
		if(!user.isEnable())
			return "deactivate";
		else
			user.setEnable(false);
		users.save(user);
		
		return "complete";
	}
	
	@GetMapping("/con/{con}")
	public String getbyCountry(@PathVariable String con,Model model)
	{
		List<User> user=users.getByCon(con);
		model.addAttribute("user", user);
		model.addAttribute("con", con);
		
		return "bycountry";
	}

}
