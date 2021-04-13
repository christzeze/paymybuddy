package com.openclassroooms.paymybuddy.mapperTest;


import com.openclassroooms.paymybuddy.dto.UserDto;
import com.openclassroooms.paymybuddy.mapper.UserMapper;
import com.openclassroooms.paymybuddy.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class AccountMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void shouldMapUserToDto() {
        //given
        User user = new User("john", "doe", "johndoe@gmail.com", "123456");

        //when
        UserDto userDto = userMapper.toDTO(user);

        //then
        assertThat(userDto).isNotNull();
        assertThat(userDto.getFirstName()).isEqualTo("john");
        assertThat(userDto.getLastName()).isEqualTo("doe");
        assertThat(userDto.getEmail()).isEqualTo("johndoe@gmail.com");
        assertThat(userDto.getPassword()).isEqualTo("123456");
        assertThat(userDto.getIban()).isEqualTo("aa123456789");
        assertThat(userDto.getBank().getName()).isEqualTo("crédit agricole");
    }

}
