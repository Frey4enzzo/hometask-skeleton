package ua.epam.spring.hometask.controller.user;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;;
import org.springframework.test.web.servlet.ResultActions;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.handler.error.ControllerErrorHandler;
import ua.epam.spring.hometask.service.UserService;

import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserServiceController.class)
public class UserServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private MessageSource defaultMessageSource;
    @MockBean
    private ControllerErrorHandler controllerErrorHandler;

    private static final String URL = "/users";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final Long DEFAULT_USERS_DB_COUNT = 5L;

    @WithMockUser(username = "qwerty", password = "password")
    @Test
    public void getAllUsersTest() throws Exception {
        //given:

        //when:
        ResultActions resultActions = mockMvc.perform(get(URL));

        //then:
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void failedAuthorizationTest() throws Exception {
        //given:

        //when:
        ResultActions resultActions = mockMvc.perform(get(URL));

        //then:
        resultActions.andExpect(status().isUnauthorized());
    }

    @WithMockUser(username = "qwerty", password = "password")
    @Test
    public void getUserTest() throws Exception {
        //given:
        when(userService.getById(1L)).thenReturn(new User());
        String userIdPath = "/1";

        //when:
        ResultActions resultActions = mockMvc.perform(get(URL + userIdPath));

        //then:
        resultActions.andExpect(status().isOk());
    }

    @WithMockUser(username = "qwerty", password = "password")
    @Test
    public void getUserNotFoundTest() throws Exception {
        //given:
        String userIdPath = "/0";

        //when:
        ResultActions resultActions = mockMvc.perform(get(URL + userIdPath));

        //then:
        resultActions.andExpect(status().isNotFound());
    }

    @WithMockUser(username = "qwerty", password = "password")
    @Test
    public void getUserByEmailTest() throws Exception {
        //given:
        String findEmailPath = "/find/qwerty@email.ru";
        when(userService.getUserByEmail("qwerty@email.ru")).thenReturn(new User());

        //when:
        ResultActions resultActions = mockMvc.perform(get(URL + findEmailPath));

        //then:
        resultActions.andExpect(status().isOk());
    }

}
