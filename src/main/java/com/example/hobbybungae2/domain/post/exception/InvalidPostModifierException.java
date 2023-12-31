package com.example.hobbybungae2.domain.post.exception;


import com.example.hobbybungae2.domain.common.DomainException;
import com.example.hobbybungae2.global_exception.ErrorCode;
import com.example.hobbybungae2.global_exception.ErrorDetail;

/* 번개글 유저(작성자) & 로그인 유저 일치 검증 오류 */
public class InvalidPostModifierException extends DomainException {
    public InvalidPostModifierException(String field, String value, String reason) {
        super(ErrorCode.INVALID_POST_MODIFIER, new ErrorDetail(field, value, reason));
    }
}
