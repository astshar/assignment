package com.mbrd.assignment.datastorageservice.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.mbrd.assignment.datastorageservice.model.User;

public interface StorageService {

	void saveUserCSV(User user) throws IOException;

	List<User> getCSVUserList() throws IOException;

	void saveUserXML(User user) throws JAXBException, FileNotFoundException;

	List<User> getXMLUserList() throws JAXBException;

	void updateUserCSV(User user) throws IOException;

	void updateUserXML(User user) throws JAXBException, FileNotFoundException;

}
