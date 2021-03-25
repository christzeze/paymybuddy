package com.openclassroooms.paymybuddy.mapper;

import com.openclassroooms.paymybuddy.dto.UserDto;
import com.openclassroooms.paymybuddy.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapper extends AbstratMapper<User,UserDto> {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public User toEntity(UserDto userDto) {
        if (userDto ==null){
            return null;
        }
        User userEntity=new User;
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setId(userDto.getId());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setAccounts(accountMapper.toEntity(userDto.getAccounts()));
        return userEntity;
    }

    @Override
    public UserDto toDto(User user) {
        if(user==null) {
            return null;
        }
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAccounts(user.getAccounts());
    }
}
