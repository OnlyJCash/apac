/**
 *
 */
package org.solar.apac.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import cz.jirutka.spring.exhandler.handlers.RestExceptionHandler;

/**
 * @author michele.mazzilli
 *
 */
public class CustomExceptionHandler implements RestExceptionHandler<BaseException, String> {

	@Override
	public ResponseEntity<String> handleException(BaseException exc, HttpServletRequest arg1) {
		return ResponseEntity.status(exc.getStatus()).body(exc.getMessage());
	}

}
