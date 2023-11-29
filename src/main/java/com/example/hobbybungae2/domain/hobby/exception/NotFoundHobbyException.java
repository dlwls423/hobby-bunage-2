package com.example.hobbybungae2.domain.hobby.exception;

import com.example.hobbybungae2.domain.common.DomainException;
import com.example.hobbybungae2.global_exception.ErrorCode;
import com.example.hobbybungae2.global_exception.ErrorDetail;

/* 선택한 취미 카테고리가 없는 경우 */
public class NotFoundHobbyException extends DomainException {

	public NotFoundHobbyException(String field, String value, String reason) {
		super(ErrorCode.NOT_FOUND_HOBBY, new ErrorDetail(field, value, reason));
	}
}
