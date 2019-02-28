package com.iv.tensquare.user.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisClusterConfig {
	@Value("${spring.redis.cluster.nodes}")
	private String redisClusterNodes;
	@Value("${spring.redis.cluster.max-redirects}")
	private int redisClusterMaxRedirects;
	
	@Bean
	@ConfigurationProperties(prefix = "spring.redis.pool")
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		return config;
	}

	@Bean
	public RedisClusterConfiguration getJedisCluster() {
		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
		redisClusterConfiguration.setMaxRedirects(redisClusterMaxRedirects);
		List<RedisNode> nodeList = new ArrayList<RedisNode>();
		System.out.println("redisClusterNodes:" + redisClusterNodes);
		String[] cNodes = redisClusterNodes.split(",");
		// 分割出集群节点
		for (String node : cNodes) {
			String[] hp = node.split(":");
			nodeList.add(new RedisNode(hp[0], Integer.parseInt(hp[1])));
		}
		redisClusterConfiguration.setClusterNodes(nodeList);
		return redisClusterConfiguration;
	}

	@Bean
	public JedisConnectionFactory getJedisConnectionFactory(RedisClusterConfiguration redisClusterConfiguration, JedisPoolConfig jedisPoolConfig) {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
		return jedisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory factory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		return redisTemplate;
	}

}
