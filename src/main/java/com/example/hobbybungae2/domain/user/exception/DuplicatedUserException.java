package com.example.hobbybungae2.domain.user.exception;


import com.example.hobbybungae2.domain.common.DomainException;
import com.example.hobbybungae2.global_exception.ErrorCode;
import com.example.hobbybungae2.global_exception.ErrorDetail;

/* 중복회원(idName 중복) 존재 */
public class DuplicatedUserException extends DomainException {
    public DuplicatedUserException(String field, String value, String reason) {
        super(ErrorCode.DUPLICATED_USER, new ErrorDetail(field, value, reason));
    }
}
