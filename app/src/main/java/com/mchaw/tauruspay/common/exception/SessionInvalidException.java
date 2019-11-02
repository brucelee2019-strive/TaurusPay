package com.mchaw.tauruspay.common.exception;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/29 0029 23:31
 */
public class SessionInvalidException extends CustomException{
    static final long serialVersionUID = -7034897190745766939L;

    public SessionInvalidException() {
        super(ExceptionCode.SESSIONINVALID);
    }

    public SessionInvalidException(String message) {
        super(ExceptionCode.SESSIONINVALID,message);
    }

    public SessionInvalidException(String message,Throwable cause){
        super(ExceptionCode.SESSIONINVALID,message,cause);
    }
}
