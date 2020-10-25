package com.mbrd.assignment.datastorageservice.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.JsonNode;
import com.mbrd.assignment.datastorageservice.model.User;
import com.mbrd.assignment.datastorageservice.model.Users;

public interface UserRepository {
	void saveCSV(JsonNode jsonTree, File filePath, User user, String csvPath) throws IOException;

	List<User> getCSVUserList(File filePath) throws IOException;

	void saveXML(String xmlPath, Users users) throws JAXBException, FileNotFoundException;

	List<User> getXMLUserList(File filePath) throws JAXBException;

	void updateCSV(List<User> userList, String csvPath) throws IOException;
}
