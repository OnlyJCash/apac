/**
 *
 */
package org.solar.apac.social;

import java.util.List;

import facebook4j.Facebook;
import facebook4j.Post;


/**
 * @author michele.mazzilli
 *
 */
public interface FacebookService {

	public Facebook getProvider();

	public void setProvider(Facebook provider);

	public List<Post> getFeed(String userId);
}
