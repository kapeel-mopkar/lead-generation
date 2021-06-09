package com.example.medial.idao;

import java.util.List;

import com.example.medial.entity.User;

public interface UserDao {

	void saveUser(User user);
	User getUser(int id);
	List<User> getUserbyCon(String con);
	
}
