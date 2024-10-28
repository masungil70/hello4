package org.kosa.hello.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Configuration
@EnableRedisHttpSession()
public class RedisConfig {
	@Value("${spring.data.redis.host}")
	private String redisHost;
	
	@Value("${spring.data.redis.port}")
	private int redisPort;
	
//	@Value("${spring.data.redis.password}")
//	private String redisPassword;
	
	@Bean 
	public RedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(redisHost);
		redisStandaloneConfiguration.setPort(redisPort);
//		redisStandaloneConfiguration.setPassword(redisPassword);
		return new LettuceConnectionFactory(redisStandaloneConfiguration);
	}
	
	@Bean
	public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
		return new GenericJackson2JsonRedisSerializer();
	}
	
	@Bean 
	public StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		//redis 연결객체 설정  
		stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
		
		//key 저장 방법 설정
		stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
		
		//value 저장 방법 설정
		stringRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		
		//default값 저장 방법 설정
		stringRedisTemplate.setDefaultSerializer(new StringRedisSerializer());
		
		return stringRedisTemplate;
	}
}
