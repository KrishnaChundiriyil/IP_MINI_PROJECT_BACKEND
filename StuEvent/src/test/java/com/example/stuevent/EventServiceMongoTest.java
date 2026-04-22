package com.example.stuevent;

import com.example.stuevent.model.EventModel;
import com.example.stuevent.repository.Repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventServiceMongoTest {
    @Autowired
    private Repository repo;
    @Test
    void testSaveEvent_MongoDB() {
        EventModel event = new EventModel();
        event.setRNo(300);
        event.setEventName("Mongo Workshop");
        repo.save(event);
        List<EventModel> result = repo.findByrNo(200);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals("Mongo Workshop", result.get(0).getEventName());
    }
}