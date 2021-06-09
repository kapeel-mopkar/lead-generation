package com.example.medial.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medial.entity.User;
import com.example.medial.idao.UserDao;
import com.example.medial.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private UserRepository userr;
	
	@Override
	public void saveUser(User user)
	{
		userr.save(user);
	}
	
	@Override
	public User getUser(int id)
	{
		return userr.findById(id).get();
	}
	
	@Override
	public List<User> getUserbyCon(String con)
	{
		return userr.findByCountry(con);
	}
	
}
