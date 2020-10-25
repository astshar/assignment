package com.mbrd.assignment.frontendservice.dao;

import java.util.List;

import com.mbrd.assignment.frontendservice.model.User;

public interface UserRepository {

	List<User> getAllUsers();

}
