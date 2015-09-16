/**
 *
 */
package org.solar.apac.service;

import java.util.List;

import org.solar.apac.domain.User;

import com.google.common.base.Optional;

/**
 * @author michele.mazzilli
 *
 */
public interface UserService {

	public Optional<User> getById(Long id);

	public List<User> getAll();

	public Optional<User> create(User user);

	public Optional<User> createByEmail(User user);

	public Optional<User> update(User user);


}
