package com.ssafy.yourwine.exception;

public class UserException extends RuntimeException{
    private final com.ssafy.yourwine.exception.ErrorCode errorCode;

    public  UserException(final String message, com.ssafy.yourwine.exception.ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public UserException(com.ssafy.yourwine.exception.ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public com.ssafy.yourwine.exception.ErrorCode getErrorCode(){
        return errorCode;
    }
}
