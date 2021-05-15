package com.example.medial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medial.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public List<User> findByCountry(String con);
	public User findByUsername(String nm);
}
