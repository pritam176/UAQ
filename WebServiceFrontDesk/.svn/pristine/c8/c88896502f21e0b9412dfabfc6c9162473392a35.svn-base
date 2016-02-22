package com.uaq.ws.exception;

/**
 * @author nsharma
 * 
 */
public class UAQException extends Exception {

    private static final long serialVersionUID = -5073746142066895011L;

    private UAQFaultCode faultCode = null;

    private String className = null;

    private String methodName = null;

    /**
     * Calls the super class's constructor
     * 
     * @param msg
     * @param th
     */
    public UAQException(String msg, Throwable th) {
        super(msg, th);
    }

    /**
     * Calls the super class's constructor
     * 
     * @param msg
     */
    public UAQException(String msg) {
        super(msg);
    }

    /**
     * Calls the super class's constructor
     * 
     * @param th
     */
    public UAQException(Throwable th) {
        super(th);
    }

    /**
     * Calls the super class's constructor
     * 
     * @param msg
     * @param th
     */
    public UAQException(UAQFaultCode msg, Throwable th) {
        super(msg.toString(), th);
        faultCode = msg;
    }

    /**
     * @param msg
     * @param clazzName
     * @param mthdName
     * @param th
     */
    public UAQException(UAQFaultCode msg, String clazzName, String mthdName,
            Throwable th) {
        super(msg.toString(), th);
        faultCode = msg;
        className = clazzName;
        methodName = mthdName;
    }

    /**
     * @return
     */
    public UAQFaultCode getSystemFaultCode() {
        return faultCode;
    }

    /**
     * @return
     */
    public String getClassName() {
        return className;
    }

    /**
     * @return
     */
    public String getMethodName() {
        return methodName;
    }

}
