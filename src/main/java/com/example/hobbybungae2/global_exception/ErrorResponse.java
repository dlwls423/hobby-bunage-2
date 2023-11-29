package com.example.hobbybungae2.global_exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonFormat(shape = Shape.OBJECT)
public class ErrorResponse {
    private ErrorCode error;
    private List<ErrorDetail> errorDetails;

    public ErrorResponse(ErrorCode error, List<ErrorDetail> errorDetails) {
        this.error = error;
        this.errorDetails = errorDetails;
    }

    public static ErrorResponse of(final ErrorCode errorCode, final BindingResult bindingResult) {
        return new ErrorResponse(errorCode, ErrorDetail.of(bindingResult));
    }

    public static ErrorResponse of(ErrorCode errorCode, ErrorDetail errorDetail) {
        List<ErrorDetail> errorDetails = new ArrayList<>();
        errorDetails.add(errorDetail);
        return new ErrorResponse(errorCode, errorDetails);
    }
}
