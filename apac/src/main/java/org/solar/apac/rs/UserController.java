/**
 *
 */
package org.solar.apac.rs;

import java.util.List;

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
@RequestMapping("/api/{version}/users")
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> getById(@PathVariable("id") Long id){
		return null;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAll(@PathVariable("version") String version){
		return ResponseEntity.ok(service.getAll());
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<User> update(@RequestBody User user){
		Optional<User> oUpdate= service.update(user);
		if (oUpdate.isPresent()){
			return ResponseEntity.ok(oUpdate.get());
		}
		return ResponseEntity.badRequest().body(new User());
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User user){
		Optional<User> oCreate = service.create(user);
		if (oCreate.isPresent()){
			return ResponseEntity.ok(oCreate.get());
		}
		return ResponseEntity.badRequest().body(new User());
	}

}
