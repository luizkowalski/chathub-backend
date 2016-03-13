package com.inkdrop.app.controllers;

import java.io.IOException;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inkdrop.app.domain.models.User;
import com.inkdrop.app.services.GitHubService;

@RestController
@EnableAutoConfiguration
public class GitHubController {

	@Autowired
	GitHubService gitHubService;

	@RequestMapping(method = RequestMethod.POST, path="/github")
	public ResponseEntity<?> createUser(@PathParam("token") String token){
		try {
			User user =  gitHubService.createOrUpdateUser(token);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<String>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}