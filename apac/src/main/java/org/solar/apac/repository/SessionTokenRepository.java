/**
 *
 */
package org.solar.apac.repository;

import java.util.Date;

import org.solar.apac.domain.SessionToken;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author michele.mazzilli
 *
 */
public interface SessionTokenRepository extends CrudRepository<SessionToken, Long> {

	@Modifying
	@Query("Update SessionToken Set expiredAt = :newExpiredAt where token = :sessionToken and expiredAt > current_timestamp")
	public int setExpiredDateIfTokenIsValid(@Param("newExpiredAt") Date newExpiredAt, @Param("sessionToken") String sessionToken);

	@Modifying
	@Query("Delete SessionToken where token = :sessionToken")
	public int deleteByToken(@Param("sessionToken") String sessionToken);

	public SessionToken findByToken(String token);
}
