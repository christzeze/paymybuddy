package com.openclassroooms.paymybuddy.controllerTest;

import com.openclassroooms.paymybuddy.mapper.ContactMapper;
import com.openclassroooms.paymybuddy.repository.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class ForeignContactControllerTest {

    private final ContactMapper contactMapper = Mappers.getMapper(ContactMapper.class);

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ContactRepository contactRepository;

    @WithMockUser
    @Test
    public void shouldReturnStatusOkForForeignContact() throws Exception {
        // then
        mockMvc.perform(get("/foreigncontact"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("foreigncontact"));
    }

}
