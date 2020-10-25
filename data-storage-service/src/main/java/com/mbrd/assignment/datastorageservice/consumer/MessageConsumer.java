package com.mbrd.assignment.datastorageservice.consumer;

import java.io.IOException;
import java.util.Objects;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.mbrd.assignment.datastorageservice.model.UserMessage;
import com.mbrd.assignment.datastorageservice.service.StorageService;

@Component
public class MessageConsumer {

	@Autowired
	StorageService service;

	@Autowired
	Queue queue;

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

	@RabbitListener(queues = "#{queue.getName()}")
	public void receive(Message msg) throws IOException, ClassNotFoundException, JAXBException {

		Gson gson = new Gson();
		String jsonString = new String(msg.getBody());
		UserMessage usermessage = gson.fromJson(jsonString, UserMessage.class);
		if (usermessage.getMethodType().equals("POST")) {
			String format = usermessage.getFormat();
			if (format.equals("CSV") && !Objects.isNull(format)) {
				service.saveUserCSV(usermessage.getUser());
			} else if (format.equals("XML") && !Objects.isNull(format)) {
				service.saveUserXML(usermessage.getUser());
			} else {
				LOGGER.info("File Format Not Supported!");
			}
		} else if (usermessage.getMethodType().equals("PUT")) {
			String format = usermessage.getFormat();
			if (format.equals("CSV") && !Objects.isNull(format)) {
				service.updateUserCSV(usermessage.getUser());
			} else if (format.equals("XML") && !Objects.isNull(format)) {
				service.updateUserXML(usermessage.getUser());
			} else {
				LOGGER.info("File Format Not Supported!");
			}
		}
	}
}
