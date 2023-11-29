package com.example.hobbybungae2.domain.user.exception;

import com.example.hobbybungae2.domain.common.DomainException;
import com.example.hobbybungae2.global_exception.ErrorCode;
import com.example.hobbybungae2.global_exception.ErrorDetail;

public class NotMatchingPasswordException extends DomainException {
    public NotMatchingPasswordException(String field, String value) {
        super(ErrorCode.PASSWORD_CONFIRM_FAIL, new ErrorDetail(field, value));
    }
}