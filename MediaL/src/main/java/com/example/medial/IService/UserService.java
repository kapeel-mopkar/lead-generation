package com.example.medial.IService;

import java.util.List;

import com.example.medial.entity.User;

public interface UserService {
	
	void save(User user);
	User getUser(int id);
	List<User> getByCon(String con);
}
