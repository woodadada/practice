package com.jw.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jw.practice.model.User;
import com.jw.practice.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * User 생성
	 * @param user
	 * @return
	 */
	public boolean createUser(User user) {
		
		try {
			userRepository.save(user);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * 식별 번호로 User 조회
	 * @param id
	 * @return
	 */
	public Optional<User> getUser(Long id){
		return userRepository.findById(id);
	}
	
	/**
	 * User update
	 * @param id
	 * @param user
	 * @return
	 */
	public User updateUser(Long id, User user){
		final Optional<User> fetchedUser = userRepository.findById(id);
		if(fetchedUser.isPresent()){
			user.setId(id);
			return userRepository.save(user);
		}
		else{
			return null;
		}
	}
	
	// Optional 추후 수정
	public User patchUser(Long id, User user){
		final Optional<User> fetchedUser = userRepository.findById(id);
		if(fetchedUser.isPresent()){
			if(user.getName() != null){
				fetchedUser.get().setName(user.getName());
			}
			if(user.getHobby() != null){
				fetchedUser.get().setHobby(user.getHobby());
			}
			return userRepository.save(fetchedUser.get());
		}
		else{
			return null;
		}
	}
	
	public boolean deleteUser(Long id){
		final Optional<User> fetchedUser = userRepository.findById(id);
		if(fetchedUser.isPresent()){
			userRepository.deleteById(id);
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(e -> users.add(e));
		
		return users;
	}
}
