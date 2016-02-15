package com.uaq.service;

import com.uaq.exception.UAQException;

/**
 * Defines chain of responsibility for all Services.
 * 
 * @author nsharma
 * @param <E>
 *            Business object which can be used as the request to access service
 * @param <T>
 *            Business object which can be transformed from the response of
 *            service call
 */
public interface BaseService<I, O> {

	/**
	 * This method should be implemented.
	 * 
	 * @param object
	 * @return response that is sent back from the service call which is
	 *         constructed using results processing the request
	 * @throws UAQException
	 */
	public O execute(I inputObject) throws UAQException;

	public O executeSites(I inputObject) throws UAQException;

}
