package com.example.hobbybungae2.domain.hobby.exception;

import com.example.hobbybungae2.domain.common.DomainException;
import com.example.hobbybungae2.global_exception.ErrorCode;
import com.example.hobbybungae2.global_exception.ErrorDetail;

/* 중복 카테고리 존재  */
public class DuplicatedHobbyException extends DomainException {
    public DuplicatedHobbyException(String field, String value) {
        super(ErrorCode.DUPLICATED_HOBBY, new ErrorDetail(field, value));
    }
}
