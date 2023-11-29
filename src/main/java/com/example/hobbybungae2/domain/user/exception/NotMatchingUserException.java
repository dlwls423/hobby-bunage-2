package com.example.hobbybungae2.domain.user.exception;


import com.example.hobbybungae2.domain.common.DomainException;
import com.example.hobbybungae2.global_exception.ErrorCode;
import com.example.hobbybungae2.global_exception.ErrorDetail;

public class NotMatchingUserException extends DomainException {

	public NotMatchingUserException(String field, String value) {
		super(ErrorCode.NOT_MATCHING_USER, new ErrorDetail(field, value));
	}
}