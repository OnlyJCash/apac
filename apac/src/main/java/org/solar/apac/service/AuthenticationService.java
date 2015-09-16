/**
 *
 */
package org.solar.apac.service;

import org.solar.apac.domain.SessionToken;

import com.google.common.base.Optional;

/**
 * @author michele.mazzilli
 *
 */
public interface AuthenticationService {

	public Optional<SessionToken> login(String username, String password);

	public Optional<SessionToken> login(String username, String password, boolean isAdmin);

	public Optional<SessionToken> loginByFirstAccessToken(String token);

	public Optional<SessionToken> refresh(String token);

	public boolean isValidSessionToken(String token);

	public void invalidateSession(String token);
}
