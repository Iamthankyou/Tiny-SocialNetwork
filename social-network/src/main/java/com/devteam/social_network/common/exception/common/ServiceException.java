package com.devteam.social_network.common.exception.common;

public abstract class ServiceException extends RuntimeException{
    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message,Throwable cause){
        super(message,cause);
    }
    public abstract int getHttpErrorCode();
    public abstract String getErrorCode();
    public String getSummary(){
        StringBuilder builder = new StringBuilder();
        String error = this.getErrorCode();
        if (error != null){
            builder.append("error-\"").append(error).append("\"").append(", ");
        }
        String errorMessage = this.getMessage();
        if (errorMessage != null){
            builder.append("error_description-\"").append(errorMessage).append("\"");
        }
        return builder.toString();
    }

    public String toString(){
        return this.getSummary();
    }
}
