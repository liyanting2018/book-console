package com.book.console.common;

import com.book.console.common.enums.ErrorMessage;

/**
 * Created by zino on 28/11/2016.
 */
@SuppressWarnings("serial")
public class PtException extends RuntimeException {
    private String code;
    private String msg;

    public PtException(ErrorMessage errorMessage) {
        this.code = errorMessage.getCode();
        this.msg = errorMessage.getMsg();
    }

    public PtException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public PtException(String message) {
        super(message);
        this.code = ErrorMessage.SYSTEM_ERROR.getCode();
        this.msg = message;
    }

    public PtException(String message, Throwable cause) {
        super(message, cause);
        this.code = ErrorMessage.SYSTEM_ERROR.getCode();
        this.msg = message;
    }

    public PtException(Throwable cause) {
        super(cause);
        this.code = ErrorMessage.SYSTEM_ERROR.getCode();
        this.msg = ErrorMessage.SYSTEM_ERROR.getMsg();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
