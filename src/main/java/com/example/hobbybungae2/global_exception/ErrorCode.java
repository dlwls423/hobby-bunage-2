package com.example.hobbybungae2.global_exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Locale;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@JsonFormat(shape = Shape.OBJECT)
public enum ErrorCode {
	/* COMMON */
	INVALID_PARAM(HttpStatus.BAD_REQUEST, "invalid.input"),
	/* USER */
	NOT_FOUND_USER(HttpStatus.NOT_FOUND, "not.found.user"),
	NOT_MATCHING_USER(HttpStatus.FORBIDDEN, "not.matching.user"),
	DUPLICATED_USER(HttpStatus.CONFLICT, "duplicated.user"),
	PASSWORD_CONFIRM_FAIL(HttpStatus.BAD_REQUEST, "not.matching.password"),
	/* HOBBY CATEGORY */
	DUPLICATED_HOBBY(HttpStatus.CONFLICT, "duplicated.hobby"),
	NOT_FOUND_HOBBY(HttpStatus.NOT_FOUND, "not.found.hobby"),
	CANNOT_DELETE_HOBBY(HttpStatus.BAD_REQUEST, "cannot.delete.hobby"),
	/* STATE */
	NOT_FOUND_STATE(HttpStatus.NOT_FOUND, "not.found.state"),
	/* POST */
	NOT_FOUND_POST(HttpStatus.NOT_FOUND, "not.found.post"),
	INVALID_POST_MODIFIER(HttpStatus.FORBIDDEN, "invalid.post.modifier"),
	/* COMMENT */
	NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "not.found.comment"),
	INVALID_COMMENT_MODIFIER(HttpStatus.FORBIDDEN, "invalid.comment.modifier"),
	UNMATCHED_COMMENT_POST(HttpStatus.BAD_REQUEST, "unmatched.comment.post");

	private final HttpStatus code;

	private String message;

	ErrorCode(HttpStatus code, String message) {
		this.code = code;
		this.message = message;
	}

	@RequiredArgsConstructor
	@Component
	public static class ErrorReasonInjector {

		private final MessageSource messageSource;

//		@Value("${application.locale}")
//		private String locale;

		@PostConstruct
		public void postConstruct() {
			Arrays.stream(ErrorCode.values())
				.forEach(errorcode -> errorcode.message = messageSource.getMessage(errorcode.message,null, Locale.getDefault()));
		}
	}
}
