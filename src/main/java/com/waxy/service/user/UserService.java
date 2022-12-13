package com.waxy.service.user;

import com.waxy.database.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    Optional<UserDTO> findUserByUsername(String username);
}
