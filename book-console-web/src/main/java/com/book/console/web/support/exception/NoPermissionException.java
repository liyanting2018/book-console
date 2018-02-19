package com.book.console.web.support.exception;


import com.book.console.common.PtException;
import com.book.console.common.enums.ErrorMessage;

/**
 * Created by zhengfeng on 2016-07-26 16:39.
 */
public class NoPermissionException extends PtException {
    public NoPermissionException() {
        super(ErrorMessage.NO_PERMISSION);
    }
}
