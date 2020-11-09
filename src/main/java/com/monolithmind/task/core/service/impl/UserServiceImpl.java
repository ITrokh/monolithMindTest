package com.monolithmind.task.core.service.impl;

import com.monolithmind.task.core.model.User;
import com.monolithmind.task.core.model.dto.UserDto;
import com.monolithmind.task.core.repository.UserRepository;
import com.monolithmind.task.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto create(UserDto userDto) {
        User toSave = convertToEntity(userDto);
        toSave.setId(null);
        return convertToDto(userRepository.save(toSave));
    }

    @Override
    public UserDto update(UserDto userDto) {
        User toUpdate = convertToEntity(userDto);
        User existingInDb = getById(toUpdate.getId());
        existingInDb.setUserName(toUpdate.getUserName());
        return convertToDto(userRepository.save(toUpdate));
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %s not found!", id)));
    }

    @Override
    public UserDto remove(Long userId) {
        User toDelete = getById(userId);
        userRepository.delete(toDelete);
        return convertToDto(toDelete);
    }

    private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
