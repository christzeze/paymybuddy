package com.openclassroooms.paymybuddy.controllerTest;

import com.openclassroooms.paymybuddy.dto.AccountDto;
import com.openclassroooms.paymybuddy.dto.BankDto;
import com.openclassroooms.paymybuddy.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class ContactControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @WithMockUser
    @Test
    public void shouldReturnStatusOkForContact() throws Exception {
        // then
        mockMvc.perform(get("/contact"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("contact"));
    }

    @WithMockUser
    @Test
    public void shouldSaveAContact() throws Exception {

        //given
        UserDto userDto = new UserDto(1, "john", "doe", "johndoe@gmail.com", "123456");
        BankDto bankDto = new BankDto(1, "Crédit agricole melun nord", "123 albert Street", "77000", "Melun");
        AccountDto accountDto = new AccountDto(3, "bb123456789", 250, bankDto, userDto, true);

        //then
        mockMvc.perform(post("/contact")
                .flashAttr("contact", accountDto))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/transaction"));
    }
}
