package com.example.medial.secuirity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.medial.entity.User;
import com.example.medial.repository.UserRepository;
import com.example.medial.serviceImpl.UserServiceImpl;




@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	UserRepository userr;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userr.findByUsername(username);
		
		MyUserDetail myuserdet=new MyUserDetail(user);
		
		return myuserdet;
		
	}

}
