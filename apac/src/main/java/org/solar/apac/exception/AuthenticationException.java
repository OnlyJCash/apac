/**
 *
 */
package org.solar.apac.exception;

import org.springframework.http.HttpStatus;

/**
 * @author michele.mazzilli
 *
 */
public class AuthenticationException extends BaseException {

	private static final long serialVersionUID = 1111490469093914253L;

	public AuthenticationException() {
		super(HttpStatus.UNAUTHORIZED);
	}

	public AuthenticationException(String message) {
		super(HttpStatus.UNAUTHORIZED, message);
	}

	public AuthenticationException(String message, Throwable ex) {
		super(HttpStatus.UNAUTHORIZED, message, ex);
	}
}
