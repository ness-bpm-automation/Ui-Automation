package com.ness.automation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ness.automation.model.User;
import com.ness.automation.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void createUser(User aUser) {

		if (userRepository.findOne(aUser.getUsername()) != null) {
			throw new IllegalArgumentException("The username is not available.");
		}

		userRepository.save(aUser);
	}

}
