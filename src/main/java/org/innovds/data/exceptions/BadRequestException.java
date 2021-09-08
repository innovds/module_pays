package org.innovds.data.exceptions;

import org.springframework.http.HttpStatus;

//@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends FunctionalException {

	private static final long serialVersionUID = 1L;

	public BadRequestException() {
		super(HttpStatus.BAD_REQUEST, "io.bad_request");
	}

	public BadRequestException(String reason) {
		super(HttpStatus.BAD_REQUEST, reason);
	}

	public BadRequestException(String reason, Throwable cause) {
		super(HttpStatus.BAD_REQUEST, reason, cause);
	}

}
