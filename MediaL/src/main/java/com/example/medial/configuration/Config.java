package com.example.medial.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.example.medial.entity.Lead;

@Configuration
public class Config {
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory()
	{
		JedisConnectionFactory jcf=new JedisConnectionFactory();
		return jcf;
	}
	
	@Bean
	public RedisTemplate<String, Lead> redisTemplate()
	{
		RedisTemplate<String, Lead> redisTemplate=new RedisTemplate<String, Lead>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
}
