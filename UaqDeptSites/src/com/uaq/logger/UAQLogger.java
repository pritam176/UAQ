package com.uaq.logger;

import org.apache.log4j.Logger;

public class UAQLogger {

	private final Logger logger;

	private Long methodStartTime;

	public UAQLogger(@SuppressWarnings("rawtypes") final Class clazz) {
		this.logger = Logger.getLogger(clazz);

	}

	/**
	 * For business logging
	 * 
	 * @param name
	 */
	public UAQLogger(final String name) {
		this.logger = Logger.getLogger(name);
	}

	/**
	 * 
	 * @param message
	 */
	public void debug(final String message) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(message);
		}
	}

	/**
	 * @param message
	 * @param object
	 */
	public void debug(final String message, final Object object) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(message + object);
		}
	}

	/**
	 * @param message
	 * @param object
	 * @param ex
	 */
	public void debug(final String message, final Object object, final Throwable ex) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(message + object, ex);
		}
	}

	/**
	 * @param message
	 * @param parameter
	 * @param object
	 */
	public void debug(final String message, final String parameter, final Object object) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(message + parameter + object);
		}
	}

	/**
	 * @param message
	 * @param parameter
	 * @param object
	 * @param ex
	 */
	public void debug(final String message, final String parameter, final Object object, final Throwable ex) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(message + parameter + object, ex);
		}
	}

	/**
	 * 
	 * @param message
	 * @param ex
	 */
	public void debug(final String message, final Throwable ex) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(message, ex);
		}
	}

	public void enter(final String methodName) {
		methodStartTime = System.currentTimeMillis();
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(methodName);
		}
	}

	/**
	 * 
	 * @param message
	 */
	public void error(final String message) {
		this.logger.error(message);
	}

	/**
	 * @param message
	 * @param object
	 */
	public void error(final String message, final Object object) {
		this.logger.error(message + object);
	}

	/**
	 * @param message
	 * @param object
	 * @param ex
	 */
	public void error(final String message, final Object object, final Throwable ex) {
		this.logger.error(message + object, ex);
	}

	/**
	 * @param message
	 * @param parameter
	 * @param object
	 */
	public void error(final String message, final String parameter, final Object object) {
		this.logger.error(message + parameter + object);
	}

	/**
	 * @param message
	 * @param parameter
	 * @param object
	 * @param ex
	 */
	public void error(final String message, final String parameter, final Object object, final Throwable ex) {
		this.logger.error(message + parameter + object, ex);
	}

	/**
	 * 
	 * @param message
	 * @param ex
	 */
	public void error(final String message, final Throwable ex) {
		this.logger.error(message, ex);
	}

	public void exit(final String methodName) {
		StringBuffer msg = new StringBuffer();
		msg.append("UAQ Logger | Executed Method: ");
		msg.append('.');
		msg.append(methodName);
		msg.append(" in (ms): ");
		msg.append(System.currentTimeMillis() - methodStartTime);
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(msg);
		}
	}

	/**
	 * 
	 * @param message
	 */
	public void fatal(final String message) {
		this.logger.fatal(message);
	}

	/**
	 * @param message
	 * @param object
	 * @param ex
	 */
	public void fatal(final String message, final Object object, final Throwable ex) {
		this.logger.fatal(message + object, ex);
	}

	/**
	 * @param message
	 * @param object
	 */
	public void fatal(final String message, final Object object) {
		this.logger.fatal(message + object);
	}

	/**
	 * @param message
	 * @param parameter
	 * @param object
	 */
	public void fatal(final String message, final String parameter, final Object object) {
		this.logger.fatal(message + parameter + object);
	}

	/**
	 * @param message
	 * @param parameter
	 * @param object
	 * @param ex
	 */
	public void fatal(final String message, final String parameter, final Object object, final Throwable ex) {
		this.logger.fatal(message + parameter + object, ex);
	}

	/**
	 * 
	 * @param message
	 * @param ex
	 */
	public void fatal(final String message, final Throwable ex) {
		this.logger.fatal(message, ex);
	}

	/**
	 * 
	 * @param message
	 */
	public void info(final String message) {
		if (this.logger.isInfoEnabled()) {
			this.logger.info(message);
		}
	}

	/**
	 * @param message
	 * @param object
	 */
	public void info(final String message, final Object object) {
		if (this.logger.isInfoEnabled()) {
			this.logger.info(message + object);
		}
	}

	/**
	 * @param message
	 * @param object
	 * @param ex
	 */
	public void info(final String message, final Object object, final Throwable ex) {
		if (this.logger.isInfoEnabled()) {
			this.logger.info(message + object, ex);
		}
	}

	/**
	 * @param message
	 * @param parameter
	 * @param object
	 */
	public void info(final String message, final String parameter, final Object object) {
		if (this.logger.isInfoEnabled()) {
			this.logger.info(message + parameter + object);
		}
	}

	/**
	 * @param message
	 * @param parameter
	 * @param object
	 * @param ex
	 */
	public void info(final String message, final String parameter, final Object object, final Throwable ex) {
		if (this.logger.isInfoEnabled()) {
			this.logger.info(message + parameter + object, ex);
		}
	}

	/**
	 * 
	 * @param message
	 * @param ex
	 */
	public void info(final String message, final Throwable ex) {
		if (this.logger.isInfoEnabled()) {
			this.logger.info(message, ex);
		}
	}

	/**
	 * 
	 * @param message
	 */
	public void warn(final String message) {
		this.logger.warn(message);
	}

	/**
	 * @param message
	 * @param object
	 */
	public void warn(final String message, final Object object) {
		this.logger.warn(message + object);
	}

	/**
	 * @param message
	 * @param object
	 * @param ex
	 */
	public void warn(final String message, final Object object, final Throwable ex) {
		this.logger.warn(message + object, ex);
	}

	/**
	 * @param message
	 * @param parameter
	 * @param object
	 */
	public void warn(final String message, final String parameter, final Object object) {
		this.logger.warn(message + parameter + object);
	}

	/**
	 * @param message
	 * @param parameter
	 * @param object
	 * @param ex
	 */
	public void warn(final String message, final String parameter, final Object object, final Throwable ex) {
		this.logger.warn(message + parameter + object, ex);
	}

	/**
	 * 
	 * @param message
	 * @param ex
	 */
	public void warn(final String message, final Throwable ex) {
		this.logger.warn(message, ex);
	}

}
