package com.biztrace.exception;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -4465683715329280383L;

    /** Default system error message */
    public static final long DEFAULT_EXCEPTION_ID = 1000000L;

    private long exceptionId;
    /**
     * Parameters to be set to the error message
     */
    private Object[] params;

    public BusinessException(final long exceptionId) {
        this.exceptionId = exceptionId;
    }
    public BusinessException(final long exceptionId, final Throwable e) {
        super(e);
        this.exceptionId = exceptionId;
    }
    /**
     * Constructor with three arguments: a long type code, and params need to be set to the error
     * message
     * @param exceptionId Error code for get message from properties.
     * @param params parameters used to replace the place-holder in exception message
     */
    public BusinessException(final long exceptionId, final Object... params) {
        this(exceptionId);
        this.params = params;
    }

    /**
     * Constructor with three arguments: a long type code, an throwable object and params need to be set to the error
     * message
     * @param sysCode Error code for get message from properties.
     * @param cause The exception object.
     * @param params parameters used to replace the place-holder in exception message
     */
    public BusinessException(final long exceptionId, final Throwable cause, final Object... params) {
        this(exceptionId, cause);
        this.params = params;
    }
    /**
     * @return the exceptionId
     */
    public long getExceptionId() {
        return this.exceptionId;
    }
    /**
     * @param exceptionId the exceptionId to set
     */
    public void setExceptionId(final long exceptionId) {
        this.exceptionId = exceptionId;
    }
    /**
     * @return the params
     */
    public Object[] getParams() {
        return this.params;
    }
    /**
     * @param params the params to set
     */
    public void setParams(final Object[] params) {
        this.params = params;
    }
}
