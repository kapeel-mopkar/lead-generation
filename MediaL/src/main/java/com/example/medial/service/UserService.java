package com.example.medial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medial.entity.User;
import com.example.medial.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userr;
	
	public void save(User user)
	{
		userr.save(user);
	}
	
	public User getUser(int id)
	{
		return userr.findById(id).get();
	}
	
	public List<User> getByCon(String con)
	{
		List<User> user=userr.findByCountry(con);
		return user;
	}
}
