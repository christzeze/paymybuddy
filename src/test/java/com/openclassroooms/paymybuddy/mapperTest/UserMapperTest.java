package com.openclassroooms.paymybuddy.mapperTest;


import com.openclassroooms.paymybuddy.dto.UserDto;
import com.openclassroooms.paymybuddy.mapper.UserMapper;
import com.openclassroooms.paymybuddy.model.Account;
import com.openclassroooms.paymybuddy.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



public class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void shouldMapUserToDto() {
        //given
        User user = new User(1,"john", "doe", "johndoe@gmail.com", "123456");

        //when
        UserDto userDto = userMapper.toDTO(user);

        //then
        assertThat(userDto).isNotNull();
        assertThat(userDto.getFirstName()).isEqualTo("john");
        assertThat(userDto.getLastName()).isEqualTo("doe");
        assertThat(userDto.getEmail()).isEqualTo("johndoe@gmail.com");
        assertThat(userDto.getPassword()).isEqualTo("123456");
    }

    @Test
    public void shouldMapUserToEntity() {
        //given
        UserDto userDto= new UserDto(1,"john", "doe", "johndoe@gmail.com", "123456");

        //when
        User user=userMapper.toEntity(userDto);

        //then
        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("john");
        assertThat(user.getLastName()).isEqualTo("doe");
        assertThat(user.getEmail()).isEqualTo("johndoe@gmail.com");
        assertThat(user.getPassword()).isEqualTo("123456");
    }

}
