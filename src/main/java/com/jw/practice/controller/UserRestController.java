package com.jw.practice.controller;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.jw.practice.model.Message;
import com.jw.practice.model.StatusEnum;
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
	
	/**
	 * 회원 식별 번호로 회원 정보 조회
	 * 응답 상태, 메세지, 데이터 리턴 구조로 변경
	 * @param id
	 * @return
	 */
	@GetMapping("/user/{id}")
	public ResponseEntity<Message> userList(@PathVariable Long id) {
		Optional<User> user = userService.getUser(id);
		// NPE 대응 추가 필요
		Message message = new Message();
		HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        
		if(user.isPresent()){	//exist
			message.setData(user.get());
			message.setStatus(StatusEnum.OK);
			message.setMessage("성공 코드");
			return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
		}
		else{
			message.setStatus(StatusEnum.BAD_REQUEST);
			message.setMessage("실패 코드");
			return new ResponseEntity<Message>(message, headers, HttpStatus.NOT_FOUND);
		}		
	}
	
	/**
	 * 전체 회원 조회
	 * @return
	 */
	@GetMapping(value = "user/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> getAllUsers(){
		
		// NPE 대응 추가 필요
		List<User> list = userService.findAll();
		
		Message message = new Message();
		HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        
        message.setStatus(StatusEnum.OK);
		message.setMessage("성공 코드");
        message.setData(list);
        
        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
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
