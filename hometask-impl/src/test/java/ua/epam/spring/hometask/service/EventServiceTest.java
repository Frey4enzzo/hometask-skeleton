package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.epam.spring.hometask.domain.AirDate;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.repository.EventRepository;
import ua.epam.spring.hometask.service.impl.EventServiceImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @Mock
    EventRepository eventRepository;

    @InjectMocks
    EventService eventService = new EventServiceImpl();

    Event event;
    LocalDateTime currentTime = LocalDateTime.now();

    @Before
    public void setUp() {
        event = new Event();
        event.setName("Test");
        event.setPrice(10);
        event.setRating(EventRating.MID);
        Set<AirDate> airDates = new HashSet<>();
        airDates.add(new AirDate(currentTime));
        airDates.add(new AirDate(currentTime.plusDays(1)));
        event.setAirDates(airDates);
    }

    @Test
    public void getAllTest() {
        //given:
        when(eventRepository.findAll()).thenReturn(new ArrayList<>());

        //when:
        List<Event> allEvents = eventService.getAll();

        //then:
        assertEquals(0, allEvents.size());
    }

    @Test
    public void getByIdTest() {
        //given:
        when(eventRepository.findById(anyLong())).thenReturn(ofNullable(event));

        //when:
        Event findEvent = eventService.getById(1L);

        //then:
        assertEquals(event.getName(), findEvent.getName());
        assertEquals(event.getPrice(), findEvent.getPrice(), 0);
    }

    @Test
    public void getByIdNotFoundTest() {
        //given:
        lenient().when(eventRepository.findById(1L)).thenReturn(ofNullable(event));

        //when:
        Event findEvent = eventService.getById(2L);

        //then:
        assertNull(findEvent);
    }

    @Test
    public void getByNameTest() {
        //given:
        when(eventRepository.findByName("test")).thenReturn(event);

        //when:
        Event findEvent = eventService.getByName("test");

        //then:
        assertEquals(event.getName(), findEvent.getName());
    }

    @Test
    public void getByNameNotFoundTest() {
        //given:
        lenient().when(eventRepository.findByName("test")).thenReturn(event);

        //when:
        Event findEvent = eventService.getByName("test name");

        //then:
        assertNull(findEvent);
    }

    @Test
    public void getNextEventsTest() {
        //given:
        when(eventRepository.findAll()).thenReturn(Arrays.asList(event));

        //when:
        Set<Event> nextEvents = eventService.getNextEvents(currentTime.plusDays(1).plusMinutes(1));

        assertEquals(1, nextEvents.size());
    }

    @Test
    public void getForDateRangeTest() {
        //given:
        when(eventRepository.findAll()).thenReturn(Arrays.asList(event));

        //when:
        Set<Event> inRangeEvents = eventService.getForDateRange(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));

        //then:
        assertEquals(1, inRangeEvents.size());
    }

    @Test
    public void saveTest() {
        //given:
        when(eventRepository.findByName(anyString())).thenReturn(null);
        when(eventRepository.save(any())).thenReturn(event);

        //when:
        Event newEvent = eventService.save(event);

        //then:
        assertEquals(event.getName(), newEvent.getName());
        assertEquals(event.getPrice(), newEvent.getPrice(), 0);
    }

    @Test
    public void saveAlreadyExistsTest() {
        //given:
        when(eventRepository.findByName(anyString())).thenReturn(event);

        //when:
        Event newEvent = eventService.save(event);

        assertNull(newEvent);
    }

    @Test
    public void deleteTest() {
        //given:
        doNothing().when(eventRepository).delete(event);

        //when:
        eventService.delete(event);

        //then:
        verify(eventRepository, times(1)).delete(event);
    }
}
