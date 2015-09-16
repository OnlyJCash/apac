/**
 *
 */
package org.solar.apac.rs;

import org.solar.apac.common.Version;
import org.solar.apac.domain.User;
import org.solar.apac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Optional;

/**
 * @author michele.mazzilli
 *
 */
@RestController
@RequestMapping("/api/{version}")
public class UserRegistrationController {

	@Autowired
	private UserService service;

	/**
	 *
	 * @param v
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<User> register(@PathVariable("version") String v, @RequestBody User user){
		Version version = Version.by(v);
		switch (version) {
		case V_1:
			Optional<User> oUser = service.createByEmail(user);
			if (oUser.isPresent()){
				return ResponseEntity.ok(oUser.get());
			}
			break;
		default:
			return ResponseEntity.badRequest().body(new User());
		}
		return null;
	}

}
