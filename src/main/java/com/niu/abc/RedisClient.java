package com.niu.abc;

import redis.clients.jedis.JedisCluster;

public class RedisClient {
	public static JedisCluster jc;
	private JedisCluster jc1;

	public JedisCluster getJc1() {
		System.out.println("jc1");
		return jc1;
	}

	public void setJc1(JedisCluster jc1) {
		this.jc1 = jc1;
		RedisClient.jc = jc1;
	}

}
