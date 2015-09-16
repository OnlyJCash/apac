/**
 *
 */
package org.solar.apac.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.solar.apac.domain.User;
import org.solar.apac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

/**
 * @author michele.mazzilli
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	@Autowired
	private FirstAccessTokenService accessTokenService;


	@Override
	public Optional<User> getById(Long id) {
		return Optional.fromNullable(repo.findOne(id));
	}

	@Override
	public Optional<User> createByEmail(User user) {
		User dbUser = repo.findByEmail(user.getEmail());
		if (null == dbUser){
			dbUser = repo.save(user);
		}
		String firstAccessToken = accessTokenService.get(dbUser.getId());
		dbUser.setFirstAccessToken(firstAccessToken);
		// TODO Send Email with link
		repo.save(dbUser);
		return Optional.fromNullable(dbUser);
	}

	/* (non-Javadoc)
	 * @see org.solar.apac.service.ApacUserService#create(org.solar.apac.domain.ApacUser)
	 */
	@Override
	public Optional<User> create(User user) {
		if (!StringUtils.isEmpty(user.getPassword())){
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		}
		return Optional.fromNullable(repo.save(user));
	}

	/* (non-Javadoc)
	 * @see org.solar.apac.service.ApacUserService#update(org.solar.apac.domain.ApacUser)
	 */
	@Override
	public Optional<User> update(User user) {
		if (StringUtils.hasText(user.getFirstAccessToken())){
			// FirstAccess
			user.setFirstAccessToken(null);
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		}
		return Optional.fromNullable(repo.save(user));
	}

	@Override
	public List<User> getAll() {
		return Lists.newArrayList(repo.findAll());
	}


}
