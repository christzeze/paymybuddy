package com.openclassroooms.paymybuddy.mapper;

import com.openclassroooms.paymybuddy.dto.AccountDto;
import com.openclassroooms.paymybuddy.dto.UserDto;
import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class UserMapper extends AbstratMapper<User, UserDto> {


    @Autowired
    AccountMapper accountMapper;

    @Override
    public User toEntity(UserDto userDto) {
        if (userDto ==null){
            return null;
        }
        User userEntity=new User();
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setId(userDto.getId());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setAccounts(accountMapper.toEntities(userDto.getAccounts()));
        return userEntity;
    }

    @Override
    public UserDto toDTO(User user) {
        if(user==null) {
            return null;
        }
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        //userDto.setAccounts(accountMapper.toDTO(user.getAccounts()));
        return userDto;
    }
}
