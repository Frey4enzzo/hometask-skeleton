package ua.epam.spring.hometask.controller.event;

import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import ua.epam.spring.hometask.controller.ControllerTestConfig;
import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.handler.error.ControllerErrorHandler;
import ua.epam.spring.hometask.service.EventService;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EventServiceController.class)
@ImportAutoConfiguration(ControllerTestConfig.class)
public class EventServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EventService eventService;
    @Autowired
    private MessageSource defaultMessageSource;
    @MockBean
    ControllerErrorHandler controllerErrorHandler;

    private Event event;
    private static final String URL = "/events";

    @Before
    public void setUp() {
        event = new Event();
        event.setName("Test");
        event.setPrice(10);
        event.setRating(EventRating.MID);
        Set<AirDate> airDates = new HashSet<>();
        airDates.add(new AirDate(LocalDateTime.now()));
        event.setAirDates(airDates);
    }

    @Test
    public void getAllEventsTest() throws Exception {
        //given:

        //when:
        ResultActions resultActions = mockMvc.perform(get(URL));

        //then:
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void getEventTest() throws Exception {
        //given:
        when(eventService.getById(1L)).thenReturn(event);
        String eventIdPath = "/1";

        //when:
        ResultActions resultActions = mockMvc.perform(get(URL + eventIdPath));

        //then:
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void getEventNotFoundTest() throws Exception {
        //given:
        when(eventService.getById(1L)).thenReturn(null);
        String eventIdPath = "/1";

        //when:
        ResultActions resultActions = mockMvc.perform(get(URL + eventIdPath));

        //then:
        resultActions.andExpect(status().isNotFound());
    }

}
