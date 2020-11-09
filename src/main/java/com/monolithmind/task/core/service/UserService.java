package com.monolithmind.task.core.service;

import com.monolithmind.task.core.model.User;
import com.monolithmind.task.core.model.dto.UserDto;

public interface UserService {
    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto);

    User getById(Long id);

    UserDto remove(Long userDto);
}
