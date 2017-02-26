package com.inkdrop;

import org.springframework.beans.factory.annotation.Autowired;

import com.inkdrop.domain.models.User;
import com.inkdrop.infrastructure.repositories.UserRepository;

public class TestHelper {

	@Autowired protected UserRepository userRepo;
	
	protected User createUser(){
		User u = new User();
		u.setLogin("testUser");
		u.setUid(1234);
		u.setBackendAccessToken("valid");
		
		return userRepo.save(u);
	}
	
}
