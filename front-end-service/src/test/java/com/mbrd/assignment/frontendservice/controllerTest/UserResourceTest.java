package com.mbrd.assignment.frontendservice.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mbrd.assignment.frontendservice.model.User;


public class UserResourceTest extends AbstractTest {
   @Override
   @Before
   public void setUp() {
      super.setUp();
   }
   @Test
   public void getAllUsersTest() throws Exception {
      String uri = "/users";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      User[] userList = super.mapFromJson(content, User[].class);
      assertTrue(userList.length > 0);
   }
   
   @Test
   public void getUsersByIdTest() throws Exception {
      String uri = "/users/11";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      User user = super.mapFromJson(content, User.class);
      assertTrue(user != null);
   }
   
   @Test
   public void createUserTest() throws Exception {
      String uri = "/users/format?fileType=XML";
      User user = new User();
      user.setId(101);
      user.setName("Kamla");
      user.setDob("13/03/1997");
      user.setAge(23);
      user.setSalary(30000);
      String inputJson = super.mapToJson(user);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(201, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals(content, "User is created successfully");
   }
   
   
   
   @Test
   public void updateUserTest() throws Exception {
      String uri = "/users/format?fileType=XML";
      User user = new User();
      user.setId(101);
      user.setName("Kamla");
      user.setDob("13/03/1997");
      user.setAge(23);
      user.setSalary(30000);
      String inputJson = super.mapToJson(user);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals(content, "user is updated successfully");
   }
   
}