package com.example.hobbybungae2.domain.post.exception;


import com.example.hobbybungae2.domain.common.DomainException;
import com.example.hobbybungae2.global_exception.ErrorCode;
import com.example.hobbybungae2.global_exception.ErrorDetail;

/* 번개글이 없는 경우 */
public class NotFoundPostException extends DomainException {

	public NotFoundPostException(String field, String value) {
		super(ErrorCode.NOT_FOUND_POST, new ErrorDetail(field, value));
	}
}
