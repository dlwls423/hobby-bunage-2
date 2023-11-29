package com.example.hobbybungae2.domain.comment.exception;

import com.example.hobbybungae2.domain.common.DomainException;
import com.example.hobbybungae2.global_exception.ErrorCode;
import com.example.hobbybungae2.global_exception.ErrorDetail;

/* 댓글유저 & 로그인 유저 일치 검증 오류  */
public class InvalidCommentModifier extends DomainException {

    public InvalidCommentModifier(String field, String value) {
        super(ErrorCode.INVALID_COMMENT_MODIFIER, new ErrorDetail(field, value));
    }
}
