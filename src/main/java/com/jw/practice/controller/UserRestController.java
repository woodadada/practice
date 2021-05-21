package com.jw.practice.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.jw.practice.model.User;
import com.jw.practice.service.UserService;

@RestController
@RequestMapping("/restful/api")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<Void> createUser(@RequestBody User user,UriComponentsBuilder builder) {
		user.setCreateDate(new Date());
		boolean flag = userService.createUser(user);
        if (flag == false) {
   	    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
           }
        //HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(builder.path("index").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> userList(@PathVariable Long id) {
		Optional<User> user = userService.getUser(id); 
		if(user.isPresent()){	//exist
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		}
		else{
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		User updateUser = userService.updateUser(id, user); 
		if(updateUser != null){	//exist
			return new ResponseEntity<User>(updateUser, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@PatchMapping("/user/{id}")
	public ResponseEntity<User> patchUser(@PathVariable Long id, @RequestBody User user) {
		User updateUser = userService.patchUser(id, user); 
		if(updateUser != null){	//exist
			return new ResponseEntity<User>(updateUser, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		if(userService.deleteUser(id)){	//exist
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else{
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}		
	}
}
