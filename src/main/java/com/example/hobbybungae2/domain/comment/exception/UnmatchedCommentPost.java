package com.example.hobbybungae2.domain.comment.exception;


import com.example.hobbybungae2.domain.common.DomainException;
import com.example.hobbybungae2.global_exception.ErrorCode;
import com.example.hobbybungae2.global_exception.ErrorDetail;

/* 댓글이 해당 게시글의 댓글이 아닌 경우 */
public class UnmatchedCommentPost extends DomainException {

    public UnmatchedCommentPost(String field, String value) {
        super(ErrorCode.UNMATCHED_COMMENT_POST, new ErrorDetail(field, value));
    }
}
