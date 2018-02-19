package com.book.console.web.support.exception;

import com.book.console.common.PtException;
import com.book.console.common.enums.ErrorMessage;

/**
 * Created by zhengfeng on 2016-07-26 16:37.
 */
public class UnLoginException extends PtException {
    public UnLoginException() {
        super(ErrorMessage.UN_LOGIN);
    }
}
