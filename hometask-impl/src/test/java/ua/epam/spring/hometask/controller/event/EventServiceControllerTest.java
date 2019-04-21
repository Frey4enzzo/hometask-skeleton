package ua.epam.spring.hometask.controller.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import ua.epam.spring.hometask.controller.ControllerTestConfig;
import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.handler.error.ControllerErrorHandler;
import ua.epam.spring.hometask.service.EventService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    private MessageSource messageSource;
    @MockBean
    ControllerErrorHandler controllerErrorHandler;

    private Event event;
    private static final String URL = "/events";
    private static final String EVENT_SUCCESS_CREATE = "Новое событие успешно добавлено";
    private static final String EVENT_CREATED_FAILED_EXISTS = "Такое событие уже существует";
    LocalDateTime currentTime = LocalDateTime.now();

    @Before
    public void setUp() {
        event = new Event();
        event.setName("Test");
        event.setPrice(10);
        event.setRating(EventRating.MID);
        Set<AirDate> airDates = new HashSet<>();
        airDates.add(new AirDate(currentTime));
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

    @Test
    public void addEventTest() throws Exception {
        //given:
        when(eventService.save(any())).thenReturn(event);
        String addEventPath = "/add";

        //when:
        ResultActions resultActions = mockMvc.perform(post(URL + addEventPath)
                .locale(new Locale("ru", "RU"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(event)));

        //then:
        resultActions.andExpect(status().isOk()).andExpect(content().string(EVENT_SUCCESS_CREATE));
    }

    @Test
    public void addEventAlreadyExistsTest() throws Exception {
        //given:
        when(eventService.save(any())).thenReturn(null);
        String addEventPath = "/add";

        //when:
        ResultActions resultActions = mockMvc.perform(post(URL + addEventPath)
                .locale(new Locale("ru", "RU"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(event)));

        //then:
        resultActions.andExpect(status().isOk()).andExpect(content().string(EVENT_CREATED_FAILED_EXISTS));
    }

    @Test
    public void deleteEventTest() throws Exception {
        when(eventService.getById(1L)).thenReturn(event);
        String deleteEventPath = "/delete/1";

        //when:
        ResultActions resultActions = mockMvc.perform(get(URL + deleteEventPath));

        //then:
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void deleteEventNotFoundTest() throws Exception {
        when(eventService.getById(1L)).thenReturn(null);
        String deleteEventPath = "/delete/1";

        //when:
        ResultActions resultActions = mockMvc.perform(get(URL + deleteEventPath));

        //then:
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    public void getNextEventsTest() throws Exception {
        //given:
        String nextEventsPath = "/next/" + currentTime;
        when(eventService.getNextEvents(currentTime)).thenReturn(new HashSet<>());

        //then:
        ResultActions resultActions = mockMvc.perform(get(URL + nextEventsPath));

        //then:
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void getEventsForDateRangeTest() throws Exception {
        //given:
        String eventRangePath = "/range";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);

        //then:
        ResultActions resultActions = mockMvc.perform(get(URL + eventRangePath)
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString()));

        //then:
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void addAirDateForEventTest() throws Exception {
        //given:
        String path = "/add/1/airdate/" + currentTime;

        //then:
        ResultActions resultActions = mockMvc.perform(post(URL + path));

        //then:
        resultActions.andExpect(status().isOk()).andExpect(content().string("Новая дата успешно добавлена к событию"));
    }

    @Test
    public void deleteAirDateForEventTest() throws Exception {
        //given:
        String path = "/delete/1/airdate/" + currentTime;

        //then:
        ResultActions resultActions = mockMvc.perform(post(URL + path));

        //then:
        resultActions.andExpect(status().isOk()).andExpect(content().string("Дата успешно удалена"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
