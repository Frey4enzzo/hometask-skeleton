package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.repository.UserRepository;
import ua.epam.spring.hometask.service.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserServiceImpl();

    private User user;

    @Before
    public void setUp() {
        user = new User("test", "test", "test@test.test");
    }

    @Test
    public void getAllTest() {
        //given:
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        //when:
        List<User> all = userService.getAll();

        //then:
        assertEquals(0, all.size());
    }

    @Test
    public void getByIdTest() {
        //given:
        when(userRepository.findById(anyLong())).thenReturn(ofNullable(user));

        //when:
        User findUser = userService.getById(1L);

        //then:
        assertEquals(user.getEmail(), findUser.getEmail());
    }

    @Test
    public void getByIdNotFoundTest() {
        //given:
        lenient().when(userRepository.findById(1L)).thenReturn(ofNullable(user));

        //when:
        User findUser = userService.getById(2L);

        //then:
        assertNull(findUser);
    }

    @Test
    public void getUserByEmailTest() {
        //given:
        when(userRepository.findByEmail(anyString())).thenReturn(user);

        //when:
        User findUser = userService.getUserByEmail("test@test.test");

        //then:
        assertEquals(user.getEmail(), findUser.getEmail());
    }

    @Test
    public void saveTest() {
        //given:
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(userRepository.save(any())).thenReturn(user);

        //when:
        User newUser = userService.save(user);

        //then:
        assertEquals(user.getEmail(), newUser.getEmail());
        assertEquals(user.getFirstName(), newUser.getFirstName());
    }

    @Test
    public void saveExistsUserTest() {
        //given:
        when(userRepository.findByEmail(anyString())).thenReturn(user);

        //when:
        User newUser = userService.save(user);

        assertNull(newUser);
    }

    @Test
    public void deleteUserTest() {
        //given:
        doNothing().when(userRepository).delete(user);

        //when:
        userService.delete(user);

        //then:
        verify(userRepository, times(1)).delete(user);
    }
}
