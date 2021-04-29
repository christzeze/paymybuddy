package com.openclassroooms.paymybuddy.mapper;

import com.openclassroooms.paymybuddy.dto.UserDto;
import com.openclassroooms.paymybuddy.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends AbstractMapper<User, UserDto> {
    @Override
    User toEntity(UserDto dto);
}
