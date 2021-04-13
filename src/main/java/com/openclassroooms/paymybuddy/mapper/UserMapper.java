package com.openclassroooms.paymybuddy.mapper;

import com.openclassroooms.paymybuddy.dto.UserDto;
import com.openclassroooms.paymybuddy.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper extends AbstractMapper<User, UserDto> {
    @Override
    //@Mapping(target = "accounts", ignore = true)
    User toEntity(UserDto dto);
}
