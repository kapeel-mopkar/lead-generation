package com.example.medial.iservice;

import java.util.List;

import com.example.medial.entity.User;

public interface UserService {
	
	void save(User user);
	User getUser(int id);
	List<User> getByCon(String con);
}
