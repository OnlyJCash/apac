/**
 *
 */
package org.solar.apac.repository;

import org.solar.apac.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author michele.mazzilli
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

	public User findByEmailAndPassword(String email, String password);

	public User findByEmail(String email);

	public User findByFirstAccessToken(String firstAccessToken);
}
