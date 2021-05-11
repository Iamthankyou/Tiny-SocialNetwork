package com.devteam.social_network.common.exception;

import com.devteam.social_network.common.exception.common.ServiceBadException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
@Setter
public abstract class AppException extends ServiceBadException {

    String errorCode;
    String message;
    List<String> errorField;
    String type;
    public AppException(String error,String message) {
        super(message);
        this.errorCode = error;
        this.message = message;
    }

    public AppException(String error){
        super("");
        this.errorCode = error;
    }

    public AppException(String errorCode,String message,List<String> errorField){
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.errorField = errorField;
    }


}
