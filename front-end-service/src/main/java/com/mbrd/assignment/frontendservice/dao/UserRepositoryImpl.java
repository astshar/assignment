package com.mbrd.assignment.frontendservice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mbrd.assignment.frontendservice.model.User;

@Component
public class UserRepositoryImpl implements UserRepository {

	@Value("${data.storage.url}")
	private String dataStorageUrl;

	@Override
	public List<User> getAllUsers() {

		ResponseEntity<List<User>> response = new RestTemplate().exchange(dataStorageUrl + "/users", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<User>>() {
				});
		return response.getBody();

	}

}
