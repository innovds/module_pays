package org.innovds.data.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FunctionalException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	public FunctionalException(HttpStatus status, String reason, Throwable cause) {
		super(status, reason, cause);
	}

	public FunctionalException(HttpStatus status, String reason) {
		super(status, reason);
	}

	public FunctionalException(HttpStatus status) {
		super(status);
	}

	public FunctionalException(int rawStatusCode, String reason, Throwable cause) {
		super(rawStatusCode, reason, cause);
	}

}
