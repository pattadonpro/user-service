package com.max.user.service;

import com.max.user.domain.dto.UserDTO;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    UserDTO findByEmail(String email);
}
