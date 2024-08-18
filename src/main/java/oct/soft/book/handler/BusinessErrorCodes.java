package oct.soft.book.handler;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public enum BusinessErrorCodes {

	NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "No code"),
	INCORRECT_CURRENT_PASSWORD(300, HttpStatus.BAD_REQUEST, "Current password is incorrect"),
	NEW_PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST, "New password does not match"),
	ACCOUNT_LOCKED(302, HttpStatus.FORBIDDEN, "User account is locked"),
	ACCOUNT_DISABLED(303, HttpStatus.FORBIDDEN, "User account is disabled"),
	BAD_CREDENTIAL(304, HttpStatus.FORBIDDEN, "Login and / or password is incorrect")
	;

	@Getter
	private final int code;
	@Getter
	private final String description;
	@Getter
	private final HttpStatus httpStatusCode;

	private BusinessErrorCodes(int code, HttpStatus httpStatusCode, String description) {
		this.code = code;
		this.description = description;
		this.httpStatusCode = httpStatusCode;
	}

}
