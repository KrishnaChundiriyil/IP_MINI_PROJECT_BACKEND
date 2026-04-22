package com.example.stuevent;
import com.example.stuevent.model.EventModel;
import com.example.stuevent.repository.Repository;
import com.example.stuevent.service.Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    @Mock
    private Repository repo;
    @InjectMocks
    private Service service;
    @Test
    public void testEventService() {
        Repository repo = mock(Repository.class);
        Service service = new Service(repo);
        EventModel e1 = new EventModel();
        e1.setRNo(1);
        e1.setEventName("Workshop");
        EventModel e2 = new EventModel();
        e2.setRNo(1);
        e2.setEventName("Hackathon");
        List<EventModel> eventsArray = List.of(e1, e2);
        when(repo.findByrNo(1)).thenReturn(eventsArray);
        List<EventModel> result = service.getEventByRNo(1);
        assertEquals(2, result.size());
        assertEquals("Workshop", result.get(0).getEventName());
        assertEquals("Hackathon", result.get(1).getEventName());
    }
    @Test
    void testRNoValue() {
        EventModel event = new EventModel();
        event.setRNo(10);
        assertEquals(10, event.getRNo());
    }
    @Test
    void testNameValue() {
        EventModel event = new EventModel();
        event.setName("SRK");
        assertEquals("SRK", event.getName());
    }
}
