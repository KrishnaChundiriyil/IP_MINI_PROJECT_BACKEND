package com.example.stuevent.service;

import com.example.stuevent.model.EventModel;
import com.example.stuevent.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    private final Repository repo;
    public Service(Repository repo) {
        this.repo = repo;
    }
    public EventModel saveEvent(EventModel model){
        return repo.save(model);
    }
    public List<EventModel> getAllEvents() {
        return repo.findAll();
    }

    public List<EventModel> getEventByRNo(Integer rNo) {
        return repo.findByrNo(rNo);
    }

    public void deleteEventByRNo(Integer rNo) {
        repo.deleteOneByrNo(rNo);
    }

    public EventModel getEventByName(String name) {
        return repo.findByName(name);
    }
}
