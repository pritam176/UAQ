package com.uaq.dao;

import java.io.Serializable;
import java.util.List;

import com.uaq.exception.UAQException;

/**
 * Defines chain of responsibility for all Database access.
 * 
 * @author nsharma
 * @param <E>
 *            Business object which can be used as the request to access service
 * @param <T>
 *            Business object which can be transformed from the response of
 *            service call
 */
public interface GenericDao<InputID extends Serializable, Output> {

	/**
	 * Returns an object by its primary key.
	 * 
	 * @param id
	 *            - The primary key to retrieve by.
	 * @return - An instance of the match type
	 */
	Output findByPrimaryKey(InputID id) throws UAQException;

	/**
	 * Returns all objects
	 * 
	 * @return list of objects
	 */
	List<Output> listAll() throws UAQException;

	/**
	 * Saves the Object
	 * 
	 * @param instance
	 */
	void save(Output instance) throws UAQException;

	/**
	 * Deletes the Object
	 * 
	 * @param instance
	 */
	void delete(Output instance) throws UAQException;

	String getDepartmentUrl(String name, String subType);
}
