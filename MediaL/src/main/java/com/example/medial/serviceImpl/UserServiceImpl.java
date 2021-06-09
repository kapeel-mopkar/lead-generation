package com.example.medial.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medial.entity.User;
import com.example.medial.idao.UserDao;
import com.example.medial.iservice.UserService;
import com.example.medial.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userd;
	
	@Override
	public void save(User user)
	{
		userd.saveUser(user);
	}
	
	@Override
	public User getUser(int id)
	{
		User user=userd.getUser(id);
		if(user==null)
			throw new RuntimeException("USER NOT FOUND");
		
		return user;
	}
	
	@Override
	public List<User> getByCon(String con)
	{
		List<User> user=userd.getUserbyCon(con);
		return user;
	}
}
