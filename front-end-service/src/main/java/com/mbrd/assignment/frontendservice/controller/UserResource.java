package com.mbrd.assignment.frontendservice.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbrd.assignment.frontendservice.model.User;
import com.mbrd.assignment.frontendservice.model.UserMessage;
import com.mbrd.assignment.frontendservice.service.UserService;

@RestController
public class UserResource {

	@Autowired
	UserService service;

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	Binding binding;

	@GetMapping(value = "/users", produces = "application/json")
	public List<User> getAllUsers() throws IOException, JAXBException {

		List<User> userList = service.getAllUsers();
		return userList;

	}

	@GetMapping(value = "/users/{id}", produces = "application/json")
	public ResponseEntity<Object> getUserById(@PathVariable int id) {
		
		User user=service.getUserById(id);
		if(Objects.isNull(user)) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@PostMapping(value = "/users/format", consumes = "application/json")
	public ResponseEntity<Object> createUser(@RequestParam String fileType, @Valid @RequestBody User user)
			throws JsonProcessingException {

		UserMessage message = new UserMessage();
		ObjectMapper Obj1 = new ObjectMapper();
		message.setUser(user);
		message.setFormat(fileType);
		message.setMethodType("POST");
		String type = Obj1.writeValueAsString(message);

		rabbitTemplate.convertAndSend(binding.getExchange(), binding.getRoutingKey(), type);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();
		
		return ResponseEntity.created(location).body("User is created successfully");
		
	}

	@PutMapping(value = "/users/format", consumes = "application/json")
	public ResponseEntity<Object> updateUser(@RequestParam String fileType, @Valid @RequestBody User user)
			throws JsonProcessingException {
		User usr = service.getUserById(user.getId());
		if (Objects.isNull(usr)) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		UserMessage message = new UserMessage();
		ObjectMapper Obj1 = new ObjectMapper();
		message.setUser(user);
		message.setFormat(fileType);
		message.setMethodType("PUT");
		String type = Obj1.writeValueAsString(message);

		rabbitTemplate.convertAndSend(binding.getExchange(), binding.getRoutingKey(), type);
		return ResponseEntity.status(HttpStatus.OK).body("user is updated successfully");

	}

}
