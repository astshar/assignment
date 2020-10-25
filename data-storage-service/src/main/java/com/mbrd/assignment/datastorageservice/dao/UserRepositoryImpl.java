package com.mbrd.assignment.datastorageservice.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;
import com.mbrd.assignment.datastorageservice.model.User;
import com.mbrd.assignment.datastorageservice.model.Users;
import com.mbrd.assignment.datastorageservice.utility.GeneralUtility;
import com.opencsv.CSVWriter;

@Component
public class UserRepositoryImpl implements UserRepository {

	public void saveCSV(JsonNode jsonTree, File filePath, User user, String csvPath) throws IOException {
		if (!filePath.exists()) {

			Builder csvSchemaBuilder = CsvSchema.builder();
			jsonTree.fieldNames().forEachRemaining(fieldName -> {
				csvSchemaBuilder.addColumn(fieldName);
			});
			CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

			CsvMapper csvMapper = new CsvMapper();
			csvMapper.writerFor(JsonNode.class).with(csvSchema).writeValue(filePath, jsonTree);
		} else {
			String[] record = user.toString().split(",");
			CSVWriter csvWriter = new CSVWriter(new FileWriter(csvPath, true));
			csvWriter.writeNext(record);
			csvWriter.close();
		}
	}

	@Override
	public List<User> getCSVUserList(File filePath) throws IOException {
		CsvSchema csv = CsvSchema.emptySchema().withHeader();
		CsvMapper csvMapper = new CsvMapper();
		MappingIterator<User> mappingIterator = csvMapper.reader().forType(User.class).with(csv).readValues(filePath);
		return mappingIterator.readAll();

	}

	@Override
	public void saveXML(String xmlPath, Users users) throws JAXBException, FileNotFoundException {

		JAXBContext contextObj = JAXBContext.newInstance(Users.class);
		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshallerObj.marshal(users, new FileOutputStream(xmlPath));
	}

	@Override
	public List<User> getXMLUserList(File filePath) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Users users = (Users) jaxbUnmarshaller.unmarshal(filePath);
		return users.getUser();
	}

	@Override
	public void updateCSV(List<User> userList, String csvPath) throws IOException {
		File filePath = new File(csvPath);
		filePath.delete();
		String[] header = GeneralUtility.CSVHeader.split(",");
		CSVWriter csvWriter = new CSVWriter(new FileWriter(csvPath, true));
		csvWriter.writeNext(header);
		for (User u : userList) {
			String[] userRecord = u.toString().split(",");
			csvWriter.writeNext(userRecord);
		}
		csvWriter.close();

	}

}
