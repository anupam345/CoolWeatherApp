package com.app.backend.service;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.app.backend.dto.Root;
import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisCacheServiceImp implements RedisCacheService {

	
	private JedisPool jedisPool;

	private final Gson gson = new Gson();

	//TTL(Time to live) of session data in seconds 
	@Value("${redis.sessiondata.ttl}")
	private int sessiondataTTL;

	// Acquire Jedis instance from the jedis pool.
	private Jedis acquireJedisInstance() {

		return jedisPool.getResource();
	}

	// Releasing the current Jedis instance once completed the job.
	private void releaseJedisInstance(Jedis jedis) {

		if (jedis != null) {
			jedis.close();
			jedis = null;
		}
	}


	@Override
	public Root storeData(String id, Root root) {
		Jedis jedis = null;

		try {

			jedis = acquireJedisInstance();

			String json = gson.toJson(root);
			jedis.set(id, json);
			jedis.expire(id, sessiondataTTL);

		} catch (Exception e) {
			releaseJedisInstance(jedis);
			throw new RuntimeException(e);

		} finally {
			releaseJedisInstance(jedis);
		}

		return root;
	}

	@Override
	public Root retrieveData(String id) {
		Jedis jedis = null;
		try {

			jedis = acquireJedisInstance();

			List<String> keys = jedis.lrange(id, 0, -1);
			if (!CollectionUtils.isEmpty(keys)) {
				// add the list key in as well
				keys.add(id);

				// delete the keys and list
				jedis.del(keys.toArray(new String[keys.size()]));
			}
		} catch (Exception e) {
			releaseJedisInstance(jedis);
			throw new RuntimeException(e);

		} finally {
			releaseJedisInstance(jedis);
		}
		return  null;
	}

	@Override
	public void flushCache(String id) {
		Jedis jedis = null;
		try {

			jedis = acquireJedisInstance();
			jedis.flushAll();

		} catch (Exception e) {
			releaseJedisInstance(jedis);
			throw new RuntimeException(e);

		} finally {
			releaseJedisInstance(jedis);
		}
				
	}

	@Override
	public void clearAll() {
		Jedis jedis = null;
		try {

			jedis = acquireJedisInstance();
			jedis.flushAll();

		} catch (Exception e) {
			releaseJedisInstance(jedis);
			throw new RuntimeException(e);

		} finally {
			releaseJedisInstance(jedis);
		}
		
	}

}
