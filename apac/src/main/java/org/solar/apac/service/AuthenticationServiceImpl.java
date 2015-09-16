/**
 *
 */
package org.solar.apac.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.solar.apac.domain.SessionToken;
import org.solar.apac.domain.User;
import org.solar.apac.domain.User.Role;
import org.solar.apac.repository.SessionTokenRepository;
import org.solar.apac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;

/**
 * @author michele.mazzilli
 *
 */
@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private SessionTokenRepository repo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private FirstAccessTokenService accessTokenService;

	@Override
	public Optional<SessionToken> login(String username, String password, boolean isAdmin) {
		password = DigestUtils.md5Hex(password);
		User user = userRepo.findByEmailAndPassword(username, password);
		if (null != user){
			if (isAdmin && Role.SIMPLE.equals(user.getRole())){
				return Optional.absent();
			}
			return createSessionToken(user);
		}
		return Optional.absent();
	}

	/* (non-Javadoc)
	 * @see org.solar.apac.service.AuthenticationService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public Optional<SessionToken> login(String username, String password) {
		return login(username, password, false);
	}

	@Override
	public Optional<SessionToken> loginByFirstAccessToken(String accessToken) {
		boolean isValid = accessTokenService.checkValidity(accessToken);
		if (isValid){
			User user = userRepo.findByFirstAccessToken(accessToken);
			return createSessionToken(user);
		}
		return Optional.absent();
	}


	private Optional<SessionToken> createSessionToken(User user) {
		if (null != user){
			SessionToken st = new SessionToken();
			st.setUser(user);
			st.setToken(UUID.randomUUID().toString()+"-"+user.getId());
			st.setExpiredAt(DateTime.now().plusMinutes(10).toDate());
			repo.save(st);
			return Optional.of(st);
		}
		return Optional.absent();
	}

	@Override
	public boolean isValidSessionToken(String token) {
		DateTime newExpiredAt = DateTime.now().plusMinutes(10);
		int updateRow = repo.setExpiredDateIfTokenIsValid(newExpiredAt.toDate(), token);
		return updateRow == 1;
	}

	@Override
	public void invalidateSession(String token) {
		repo.deleteByToken(token);
	}

	@Override
	public Optional<SessionToken> refresh(String token) {
		SessionToken sessionToken = repo.findByToken(token);
		if (null != sessionToken){
			sessionToken.setExpiredAt(DateTime.now().plusMinutes(10).toDate());
			sessionToken = repo.save(sessionToken);
			return Optional.of(sessionToken);
		}
		return Optional.absent();
	}

}
