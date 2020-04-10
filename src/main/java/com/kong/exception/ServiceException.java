package com.kong.exception;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String id;
    private Object[] params;
    private String debug;

    public ServiceException(String id) {
        this.id = id;
    }

    public ServiceException(String id, String message) {
        super(message);
        this.id = id;
    }

    public ServiceException(String id, Object... params) {
        this.id = id;
        this.params = params;
    }

    public ServiceException(Throwable cause, String id, String message) {
        super(message, cause);
        this.id = id;
    }

    public ServiceException(Throwable cause, String id, Object... params) {
        super(cause);
        this.id = id;
        this.params = params;
    }

    public ServiceException(Throwable e) {
        super(e);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object[] getParams() {
        return this.params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String getDebug() {
        return this.debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }
}
