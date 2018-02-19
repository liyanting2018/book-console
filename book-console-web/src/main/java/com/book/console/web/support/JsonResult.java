package com.book.console.web.support;

import java.io.Serializable;

import com.book.console.common.PtException;
import com.book.console.common.enums.ErrorMessage;

/**
 * 接口返回值json包装类型
 * com.pajk.skyeye.web.support
 * Created by zhengfeng on 2016-07-21 15:26.
 */
public class JsonResult implements Serializable {

    private String code;
    private String msg;
    private Object data;

    public JsonResult() {
    }

    public JsonResult(ErrorMessage message, Object data) {
        this.code = message.getCode();
        this.msg = message.getMsg();
        this.data = data;
    }

    public JsonResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult success() {
        return success(null);
    }

    public static JsonResult success(Object data) {
        return new JsonResult(ErrorMessage.SUCCESS, data);
    }

    public static JsonResult error(String code, String msg, Object data) {
        return new JsonResult(code, msg, data);
    }

    public static JsonResult error() {
        return error(ErrorMessage.SYSTEM_ERROR);
    }

    public static JsonResult error(ErrorMessage message) {
        return error(message, null);
    }

    public static JsonResult error(ErrorMessage message, Object data) {
        return new JsonResult(message.getCode(), message.getMsg(), data);
    }

    public static JsonResult error(PtException e) {
        return new JsonResult(e.getCode(), e.getMsg(), null);
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
