/**
 *
 */
package org.solar.apac.rs;

import org.solar.apac.domain.SessionToken;
import org.solar.apac.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Optional;

/**
 * @author michele.mazzilli
 *
 */
@RestController
@RequestMapping(value="/api/{version}/auth")
public class AuthenticationController  {

	@Autowired
	private AuthenticationService service;

	@RequestMapping(value="/token", method=RequestMethod.POST)
	public ResponseEntity<SessionToken> login(@PathVariable("version") String version, @RequestParam("usr") String username, @RequestParam("pwd") String password){
		Optional<SessionToken> oST = service.login(username, password);
		if (oST.isPresent()){
			return ResponseEntity.ok(oST.get());
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new SessionToken());
	}

	@RequestMapping(value="/admin/token", method=RequestMethod.POST)
	public ResponseEntity<SessionToken> beLogin(@PathVariable("version") String version, @RequestParam("usr") String username, @RequestParam("pwd") String password){
		Optional<SessionToken> oST = service.login(username, password, true);
		if (oST.isPresent()){
			return ResponseEntity.ok(oST.get());
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new SessionToken());
	}

	@RequestMapping(value="/refreshtoken", method=RequestMethod.POST)
	public ResponseEntity<SessionToken> beLogin(@PathVariable("version") String version,  @RequestHeader("sessionToken") String sessionToken){
		Optional<SessionToken> oST = service.refresh(sessionToken);
		if (oST.isPresent()){
			return ResponseEntity.ok(oST.get());
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new SessionToken());
	}

	@RequestMapping(value="/firstAccess/{firstAccessToken}", method=RequestMethod.GET)
	public ResponseEntity<SessionToken> firstAccess(@PathVariable("version") String v, @PathVariable("firstAccessToken") String firstAccessToken){
		Optional<SessionToken> oST = service.loginByFirstAccessToken(firstAccessToken);
		if (oST.isPresent()){
			return ResponseEntity.ok(oST.get());
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new SessionToken());
	}

	@RequestMapping(value="/token/invalidate", method=RequestMethod.POST)
	public ResponseEntity<SessionToken> invalidate(@PathVariable("version") String version, @RequestHeader("sessionToken") String sessionToken){
		service.invalidateSession(sessionToken);
		return ResponseEntity.ok().body(new SessionToken());
	}
}
