package com.example.medial.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.medial.entity.Lead;
import com.example.medial.subscriber.Receiver;

@Configuration
public class Config {
	
	@Bean
	public JedisConnectionFactory connectionFactory()
	{
		JedisConnectionFactory jcf=new JedisConnectionFactory();
		return jcf;
	}
	
	@Bean
	public RedisTemplate<String, Lead> redisTemplate()
	{
		RedisTemplate<String, Lead> redisTemplate=new RedisTemplate<String, Lead>();
		redisTemplate.setConnectionFactory(connectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		return redisTemplate;
	}
	
	@Bean
	public ChannelTopic topic()
	{
		return new ChannelTopic("LeadTopic");
	}
	
	@Bean
	public MessageListenerAdapter messageListener()
	{
		return new MessageListenerAdapter(new Receiver());
	}
	
	@Bean
	public RedisMessageListenerContainer redisContainer()
	{
		RedisMessageListenerContainer container=new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.addMessageListener(messageListener(), topic());
		return container;
	}	
}
