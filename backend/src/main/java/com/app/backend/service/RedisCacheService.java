package com.app.backend.service;

import com.app.backend.dto.Root;

public interface RedisCacheService {

	/**
	 * Store Employee object
	 * 
	 * @param employeeId as a key
	 * @param employee   as a value
	 * @return Employee
	 */
	Root storeData(String id, Root root);

	/**
	 * Retrieve  object
	 * 
	 * @param Id as a key
	 * @return Root
	 */
	Root retrieveData(String id);

	/**
	 * Flush  object using given id
	 *
	 * @param id as a key
	 */
	void flushCache(String id);

	/**
	 * Flush all data
	 * 
	 */
	void clearAll();

}