package com.mbrd.assignment.frontendservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mbrd.assignment.frontendservice.dao.UserRepository;
import com.mbrd.assignment.frontendservice.model.User;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;

	@Override
	public List<User> getAllUsers() {
		return repository.getAllUsers();

	}

	@Override
	public User getUserById(int id) {
		List<User> userList = repository.getAllUsers();
		return userList.stream().filter(u -> (u.getId() == id)).findAny().orElse(null);

	}
}
