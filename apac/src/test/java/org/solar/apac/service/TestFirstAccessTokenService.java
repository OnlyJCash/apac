/**
 *
 */
package org.solar.apac.service;

import org.junit.Assert;
import org.junit.Test;
import org.solar.apac.SpringTestContextInitialized;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author michele.mazzilli
 *
 */
public class TestFirstAccessTokenService extends SpringTestContextInitialized {

	@Autowired
	private FirstAccessTokenService service;

	@Test
	public void testGetAndValidity_success(){
		String hash = service.get(50L);

		Assert.assertNotNull(hash);
		Assert.assertTrue(hash.endsWith("50"));

		Assert.assertTrue(service.checkValidity(hash));
	}
}
