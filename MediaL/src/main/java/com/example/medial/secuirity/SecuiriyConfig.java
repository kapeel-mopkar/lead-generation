package com.example.medial.secuirity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecuiriyConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailService;
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailService);
	    }
	 

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	        		.antMatchers("/user/**").hasRole("ADMIN")
	        		.antMatchers("/leads/**").permitAll()
	                .and().formLogin()
	                .and().csrf().disable();
	    }
	 
		/*
		 * @Bean public PasswordEncoder getPasswordEncoder() { // return
		 * NoOpPasswordEncoder.getInstance(); // return new BCryptPasswordEncoder();
		 * DelegatingPasswordEncoder delPasswordEncoder=
		 * (DelegatingPasswordEncoder)PasswordEncoderFactories.
		 * createDelegatingPasswordEncoder(); BCryptPasswordEncoder
		 * bcryptPasswordEncoder =new BCryptPasswordEncoder();
		 * delPasswordEncoder.setDefaultPasswordEncoderForMatches(bcryptPasswordEncoder)
		 * ; return delPasswordEncoder; }
		 */
	    
	/*    @Bean
	    public BCryptPasswordEncoder passwordEncoder()
	    {
	    	return new BCryptPasswordEncoder();
	    }	*/
	    
	    @Bean
	    public PasswordEncoder getPasswordEncoder() {
	    	return NoOpPasswordEncoder.getInstance();
	    }
}
