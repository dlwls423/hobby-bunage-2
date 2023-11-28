package com.example.hobbybungae2.domain.common;

import com.example.hobbybungae2.global_exception.ErrorCode;
import com.example.hobbybungae2.global_exception.ErrorDetail;
import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final ErrorCode errorCode;
    private final ErrorDetail errorDetail;

    public DomainException(ErrorCode errorCode, ErrorDetail errorDetail) {
        this.errorCode = errorCode;
        this.errorDetail = errorDetail;
    }
}
