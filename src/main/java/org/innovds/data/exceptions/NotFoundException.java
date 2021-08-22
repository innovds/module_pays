package org.innovds.data.exceptions;

import org.springframework.http.HttpStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends FunctionalException {

	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super(HttpStatus.NOT_FOUND, "entity.not_found");
	}

	public NotFoundException(String reason) {
		super(HttpStatus.NOT_FOUND, reason);
	}

	public NotFoundException(String reason, Throwable cause) {
		super(HttpStatus.NOT_FOUND, reason, cause);
	}

}
