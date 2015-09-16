/**
 *
 */
package org.solar.apac.social;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Post;

/**
 * @author michele.mazzilli
 *
 */
@Service
public class FacebookServiceImpl implements FacebookService {

	private Facebook facebook;

	/* (non-Javadoc)
	 * @see org.solar.apac.social.FacebookService#getFeed()
	 */
	@Override
	public List<Post> getFeed(String userId) {
		if (isExpired()){

		}
		try {
			return this.facebook.getFeed(userId);
		} catch (FacebookException e) {

		}
		return Lists.newArrayList();
	}

	private boolean isExpired(){
		return  this.facebook.getOAuthAccessToken() == null;
		//|| System.currentTimeMillis() > this.facebook.getOAuthAccessToken().getExpires();
	}

	@Override
	public Facebook getProvider() {
		return this.facebook;
	}

	@Override
	public void setProvider(Facebook provider) {
		this.facebook = provider;

	}

}
