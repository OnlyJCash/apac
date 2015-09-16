/**
 *
 */
package org.solar.apac.social.controller;

import org.solar.apac.social.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import facebook4j.FacebookException;
import facebook4j.auth.AccessToken;


/**
 * @author michele.mazzilli
 *
 */
@Controller("socialConnectController")
@RequestMapping(value="/connect")
public class ConnectController {

	@Autowired
	private FacebookService facebookService;

	@RequestMapping(value="/callback")
	public ResponseEntity<String> callback(@RequestParam("code") String code) throws FacebookException{
		AccessToken oAuthAccessToken = facebookService.getProvider().getOAuthAccessToken(code);
		facebookService.getProvider().setOAuthAccessToken(oAuthAccessToken);
		return ResponseEntity.ok("OK");
	}

}
