package com.devteam.social_network.common.exception.common;

public abstract class ServiceBadException extends ServiceException{
    public ServiceBadException(String message) {
        super(message);
    }

    public ServiceBadException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getHttpErrorCode() {
        return 400;
    }

}
