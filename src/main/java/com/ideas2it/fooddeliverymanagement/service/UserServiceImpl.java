package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.mapper.UserMapper;
import com.ideas2it.fooddeliverymanagement.model.User;
import com.ideas2it.fooddeliverymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper = new UserMapper();

    @Override
    public Optional<UserDTO> addUser(UserDTO userDTO) {

        User user = userMapper.convertUser(userDTO);

        return Optional.ofNullable(userMapper.convertUserDTO(userRepository.save(user)));
    }
}
