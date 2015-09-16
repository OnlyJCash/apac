/**
 *
 */
package org.solar.apac.rs;

import org.solar.apac.social.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michele.mazzilli
 *
 */
@RestController
public class SocialFacebookController {

	@Autowired
	private FacebookService facebookService;

	@RequestMapping(value="/get", method= RequestMethod.GET)
	public ResponseEntity<String> get(){
		facebookService.getFeed("855047511268896");
		return ResponseEntity.ok("Ciao");
	}
}
