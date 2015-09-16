/**
 *
 */
package org.solar.apac.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

/**
 * @author michele.mazzilli
 *
 */
@Service
public class FirstAccessTokenServiceImpl implements FirstAccessTokenService {

	private static final int PLUS_DAY_TO_EXPIRED_DATE = 7;
	private static final String USER_SEPARATOR = "|u";
	private static final String SEPARATOR = "-";
	private static final String SECRET_KEY = FirstAccessTokenServiceImpl.class.getCanonicalName();

	/* (non-Javadoc)
	 * @see org.solar.apac.service.FirstAccessTokenService#get(java.lang.Long)
	 */
	@Override
	public String get(Long userId) {
		DateTime expiredDay = DateTime.now().plusDays(PLUS_DAY_TO_EXPIRED_DATE);
		String hash = createHash(userId, expiredDay);
		return hash+SEPARATOR+expiredDay.getMillis()+USER_SEPARATOR+userId;
	}

	private String createHash(Long userId, DateTime expiredDay) {
		return DigestUtils.md5Hex(SECRET_KEY + SEPARATOR + expiredDay.getMillis() + SEPARATOR + userId);
	}

	/* (non-Javadoc)
	 * @see org.solar.apac.service.FirstAccessTokenService#checkValidiy(java.lang.String)
	 */
	@Override
	public boolean checkValidity(String accessToken) {
		String[] split = accessToken.split(SEPARATOR);
		String hash = split[0];
		String expiredDayAndUserId = split[1];
		long expiredDay = Long.parseLong(expiredDayAndUserId.split("\\"+USER_SEPARATOR)[0]);
		String userId = expiredDayAndUserId.split("\\"+USER_SEPARATOR)[1];
		String calcHash = createHash(Long.parseLong(userId), new DateTime(expiredDay));
		return calcHash.equals(hash) && System.currentTimeMillis() <= expiredDay;
	}

}
