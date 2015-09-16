/**
 *
 */
package org.solar.apac.rs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.solar.apac.exception.AuthenticationException;
import org.solar.apac.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author michele.mazzilli
 *
 */
public class AuthRestApiInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthRestApiInterceptor.class);

	@Autowired
	private AuthenticationService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean res = super.preHandle(request, response, handler);
		String sessionToken = request.getHeader("sessionToken");
		logger.debug("Session Token received: {}", sessionToken);
		Assert.hasText(sessionToken, "Session Token is empty");
		boolean isValid = service.isValidSessionToken(sessionToken);
		if (!isValid){
			throw new AuthenticationException("Session Token expired.");
		}
		return res;
	}
}
