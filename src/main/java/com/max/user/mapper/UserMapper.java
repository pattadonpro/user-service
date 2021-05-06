package com.max.user.mapper;

import com.max.user.domain.User;
import com.max.user.domain.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User>{
}
