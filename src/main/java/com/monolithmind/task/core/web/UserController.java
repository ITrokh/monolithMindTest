package com.monolithmind.task.core.web;

import com.monolithmind.task.core.model.dto.UserDto;
import com.monolithmind.task.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
@Slf4j
@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping(value = PathConstants.USERS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto){
        log.info("Creating user {}", userDto);
        UserDto created = userService.create(userDto);
        log.info("Created user {}", userDto);
        return created;
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto userDto){
        log.info("Updating user {}", userDto);
        UserDto updated = userService.update(userDto);
        log.info("Updated user {}", userDto);
        return updated;
    }

    @DeleteMapping(value = PathConstants.USERS_ID)
    public UserDto remove(@PathVariable Long userId){
        log.info("Deleting user with id {}", userId);
        UserDto deleted = userService.remove(userId);
        log.info("Deleted user with {}", deleted);
        return deleted;
    }
}
