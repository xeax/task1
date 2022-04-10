package com.example.task.user;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto register(UserDto userDto) {
        String login = userDto.getLogin();
        Optional<User> existingUser = userRepository.findByLogin(login);
        if (existingUser.isPresent()){
            throw new UserLoginIsAlreadyInUseException(login);
        }
        User quote = userMapper.map(userDto);
        User savedQuote = userRepository.saveAndFlush(quote);
        return userMapper.map(savedQuote);
    }
}
