package ua.epam.spring.hometask.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;;
import org.springframework.test.web.servlet.ResultActions;
import ua.epam.spring.hometask.controller.ControllerTestConfig;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.handler.error.ControllerErrorHandler;
import ua.epam.spring.hometask.service.UserService;
import java.util.Locale;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserServiceController.class, secure = false)
@ImportAutoConfiguration(ControllerTestConfig.class)
public class UserServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private MessageSource messageSource;
    @MockBean
    private ControllerErrorHandler controllerErrorHandler;

    private User user = new User("test", "test", "test@test.test");

    private static final String URL = "/users";
    private static final String USER_SUCCESS_CREATE = "Новый пользователь успешно добавлен";
    private static final String USER_ALREADY_EXISTS = "Такой пользователь уже существует";

    @Test
    public void getAllUsersTest() throws Exception {
        //given:

        //when:
        ResultActions resultActions = mockMvc.perform(get(URL));

        //then:
        resultActions.andExpect(status().isOk());
    }


    @Test
    public void getUserTest() throws Exception {
        //given:
        when(userService.getById(1L)).thenReturn(user);
        String userIdPath = "/1";

        //when:
        ResultActions resultActions = mockMvc.perform(get(URL + userIdPath));

        //then:
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void getUserNotFoundTest() throws Exception {
        //given:
        String userIdPath = "/0";

        //when:
        ResultActions resultActions = mockMvc.perform(get(URL + userIdPath));

        //then:
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    public void getUserByEmailTest() throws Exception {
        //given:
        String findEmailPath = "/find/qwerty@email.ru";
        when(userService.getUserByEmail("qwerty@email.ru")).thenReturn(user);

        //when:
        ResultActions resultActions = mockMvc.perform(get(URL + findEmailPath));

        //then:
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void getUserByEmailNotFoundTest() throws Exception {
        //given:
        String findEmailPath = "/find/qwerty@email.ru";
        when(userService.getUserByEmail("qwerty@email.ru")).thenReturn(null);

        //when:
        ResultActions resultActions = mockMvc.perform(get(URL + findEmailPath));

        //then:
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    public void addUserTest() throws Exception {
        //given:
        String addPath = "/add";
        when(userService.save(user)).thenReturn(user);

        //when:
        ResultActions resultActions = mockMvc.perform(post(URL + addPath)
                .contentType(MediaType.APPLICATION_JSON)
                .locale(new Locale("ru", "RU"))
                .content(asJsonString(user)));

        //then:
        resultActions.andExpect(status().isOk()).andExpect(content().string(USER_SUCCESS_CREATE));
    }

    @Test
    public void addUserExistsTest() throws Exception {
        //given:
        String addPath = "/add";
        when(userService.save(any())).thenReturn(null);

        //when:
        ResultActions resultActions = mockMvc.perform(post(URL + addPath)
                .contentType(MediaType.APPLICATION_JSON)
                .locale(new Locale("ru", "RU"))
                .content(asJsonString(user)));

        //then:
        resultActions.andExpect(status().isOk()).andExpect(content().string(USER_ALREADY_EXISTS));
    }

    @Test
    public void deleteUserTest() throws Exception {
        //given:
        String deletePath = "/delete/1";
        when(userService.getById(1L)).thenReturn(user);

        //when:
        ResultActions resultActions = mockMvc.perform(post(URL + deletePath));

        //then:
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void deleteNotFoundUserTest() throws Exception {
        //given:
        String deletePath = "/delete/1";
        when(userService.getById(1L)).thenReturn(null);

        //when:
        ResultActions resultActions = mockMvc.perform(post(URL + deletePath));

        //then:
        resultActions.andExpect(status().isNotFound());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
