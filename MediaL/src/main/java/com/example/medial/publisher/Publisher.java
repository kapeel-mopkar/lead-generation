package com.example.medial.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.example.medial.entity.Lead;

@Service
public class Publisher {

	@Autowired
	private RedisTemplate<String, Lead> template;
	
	@Autowired
	private ChannelTopic topic;
	
	public String publish(Lead lead)
	{
		template.convertAndSend(topic.getTopic(), lead.toString());
		return "Published..!";
	}
}
