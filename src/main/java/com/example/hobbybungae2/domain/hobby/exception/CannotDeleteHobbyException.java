package com.example.hobbybungae2.domain.hobby.exception;

import com.example.hobbybungae2.domain.common.DomainException;
import com.example.hobbybungae2.global_exception.ErrorCode;
import com.example.hobbybungae2.global_exception.ErrorDetail;

public class CannotDeleteHobbyException extends DomainException {

    public CannotDeleteHobbyException(String field, String value) {
        super(ErrorCode.CANNOT_DELETE_HOBBY, new ErrorDetail(field, value));
    }

}
