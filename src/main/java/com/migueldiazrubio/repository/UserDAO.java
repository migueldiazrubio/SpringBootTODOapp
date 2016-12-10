package com.migueldiazrubio.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.migueldiazrubio.model.User;

@Transactional
public interface UserDAO extends CrudRepository<User, Long> {
	
	public User findByUserName(String userName);
	
}