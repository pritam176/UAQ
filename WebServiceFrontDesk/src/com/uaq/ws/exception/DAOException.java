package com.uaq.ws.exception;

/**
 * @author nsharma
 * 
 */
public class DAOException extends UAQException {

	private static final long serialVersionUID = 1210980073352919845L;

	/**
	 * Calls the super class's constructor
	 * 
	 * @param msg
	 */
	public DAOException(String msg) {
		super(msg);
	}

	/**
	 * Calls the super class's constructor
	 * 
	 * @param msg
	 * @param th
	 */
	public DAOException(String msg, Throwable th) {
		super(msg, th);
	}

	/**
	 * Calls the super class's constructor
	 * 
	 * @param errEnum
	 * @param th
	 */
	public DAOException(UAQFaultCode errEnum, Throwable th) {
		super(errEnum, th);
	}

	/**
	 * Calls the super class's constructor
	 * 
	 * @param msg
	 * @param clazzName
	 * @param mthdName
	 * @param th
	 */
	public DAOException(UAQFaultCode msg, String clazzName, String mthdName, Throwable th) {
		super(msg, clazzName, mthdName, th);
	}
}
