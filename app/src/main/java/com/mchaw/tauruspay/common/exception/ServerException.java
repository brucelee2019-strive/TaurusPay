package com.mchaw.tauruspay.common.exception;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/29 0029 23:27
 */
public class ServerException extends CustomException{
    static final long serialVersionUID = -7034897190745766939L;

    public ServerException(){
        super(ExceptionCode.SERVER);
    }

    public ServerException(String message){
        super(ExceptionCode.SERVER,message);
    }

    public ServerException(String message, Throwable cause) {
        super(ExceptionCode.SERVER,message,cause);
    }
}
