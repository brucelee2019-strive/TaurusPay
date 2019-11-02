package com.mchaw.tauruspay.common.exception;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/29 0029 23:15
 */
public class EmptyException extends CustomException{
    static final long serialVersionUID = -7034897190745766939L;

    public EmptyException() {
        super(ExceptionCode.NULL);
    }

    public EmptyException(String message) {
        super(ExceptionCode.NULL,message);
    }

    public EmptyException(String message, Throwable cause) {
        super(ExceptionCode.NULL,message,cause);
    }
}
