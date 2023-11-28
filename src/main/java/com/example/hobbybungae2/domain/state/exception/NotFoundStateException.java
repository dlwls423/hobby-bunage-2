package com.example.hobbybungae2.domain.state.exception;

import com.example.hobbybungae2.domain.common.DomainException;
import com.example.hobbybungae2.global_exception.ErrorCode;
import com.example.hobbybungae2.global_exception.ErrorDetail;

/* 선택한 지역이 없는 경우 */
public class NotFoundStateException extends DomainException {

	public NotFoundStateException(String field, String value, String reason) {
		super(ErrorCode.NOT_FOUND_STATE, new ErrorDetail(field, value, reason));
	}

	public NotFoundStateException(String field, String value) {
		super(ErrorCode.NOT_FOUND_STATE, new ErrorDetail(field, value));
	}
}
