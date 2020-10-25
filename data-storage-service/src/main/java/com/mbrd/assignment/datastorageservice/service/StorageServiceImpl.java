package com.mbrd.assignment.datastorageservice.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbrd.assignment.datastorageservice.dao.UserRepository;
import com.mbrd.assignment.datastorageservice.model.User;
import com.mbrd.assignment.datastorageservice.model.Users;

@Component
public class StorageServiceImpl implements StorageService {

	@Autowired
	UserRepository repository;

	@Value("${csv.file.path}")
	String csvPath;

	@Value("${xml.file.path}")
	String xmlPath;

	@Override
	public void saveUserCSV(User user) throws IOException {

		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = Obj.writeValueAsString(user);
		JsonNode jsonTree = new ObjectMapper().readTree(jsonStr);
		File filePath = new File(csvPath);
		repository.saveCSV(jsonTree, filePath, user, csvPath);
	}

	@Override
	public List<User> getCSVUserList() throws IOException {
		File filePath = new File(csvPath);
		return repository.getCSVUserList(filePath);
	}

	@Override
	public void saveUserXML(User user) throws JAXBException, FileNotFoundException {

		File filePath = new File(xmlPath);
		List<User> userList = null;
		if (filePath.exists()) {
			userList = repository.getXMLUserList(filePath);
			userList.add(user);
		} else {
			userList = new ArrayList<>();
			userList.add(user);
		}

		Users users = new Users(1, userList);
		repository.saveXML(xmlPath, users);
	}

	@Override
	public List<User> getXMLUserList() throws JAXBException {

		File filePath = new File(xmlPath);
		return repository.getXMLUserList(filePath);

	}

	@Override
	public void updateUserCSV(User user) throws IOException {
		File filePath = new File(csvPath);
		List<User> userList = repository.getCSVUserList(filePath);
		userList.stream().forEach(u -> {
			if (u.getId() == user.getId()) {
				u.setName(user.getName());
				u.setDob(user.getDob());
				u.setAge(user.getAge());
				u.setSalary(user.getSalary());
			}
		});

		repository.updateCSV(userList, csvPath);
	}

	@Override
	public void updateUserXML(User user) throws JAXBException, FileNotFoundException {
		File filePath = new File(xmlPath);
		List<User> userList = repository.getXMLUserList(filePath);

		userList.stream().forEach(u -> {
			if (u.getId() == user.getId()) {
				u.setName(user.getName());
				u.setDob(user.getDob());
				u.setAge(user.getAge());
				u.setSalary(user.getSalary());
			}
		});

		filePath.delete();
		Users users = new Users(1, userList);
		repository.saveXML(xmlPath, users);

	}
}