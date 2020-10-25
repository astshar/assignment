package com.mbrd.assignment.datastorageservice.controller;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbrd.assignment.datastorageservice.model.User;
import com.mbrd.assignment.datastorageservice.service.StorageServiceImpl;

@RestController
public class StorageResource {

	@Autowired
	private StorageServiceImpl service;

	@GetMapping(value = "/users", produces = "application/json")
	public List<User> getAllUsers() throws IOException, JAXBException {

		List<User> userList = service.getCSVUserList();
		userList.addAll(service.getXMLUserList());

		return userList;

	}
}
