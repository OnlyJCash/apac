/**
 *
 */
package org.solar.apac.exception;

import org.springframework.http.HttpStatus;

/**
 * @author michele.mazzilli
 *
 */
public abstract class BaseException extends Exception {

	private static final long serialVersionUID = 3384172151996020457L;

	private HttpStatus status;

	public BaseException(HttpStatus status) {
		setStatus(status);
	}

	public BaseException(HttpStatus status, String message) {
		super(message);
		setStatus(status);
	}

	public BaseException(HttpStatus status, String message, Throwable exc) {
		super(message, exc);
		setStatus(status);
	}


	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	private void setStatus(HttpStatus status) {
		this.status = status;
	}

}
