package com.mbrd.assignment.frontendservice.service;

import java.util.List;

import com.mbrd.assignment.frontendservice.model.User;

public interface UserService {
	List<User> getAllUsers();

	User getUserById(int id);

}
