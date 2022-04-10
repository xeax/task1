package com.example.task.tools;

import com.example.task.user.User;
import com.example.task.user.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserToolImpl implements AuthenticatedUserTool {

	private final UserRepository userRepository;

	@Override
	public User getUser(){
		// TODO: Refactor after implementing authorization
		return userRepository.getById(1L);
	}
}
