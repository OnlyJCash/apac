/**
 *
 */
package org.solar.apac.service;

/**
 * @author michele.mazzilli
 *
 */
public interface FirstAccessTokenService {

	public String get(Long userId);

	public boolean checkValidity(String accessToken);
}
