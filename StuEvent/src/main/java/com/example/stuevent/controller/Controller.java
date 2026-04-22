package com.example.stuevent.controller;

import com.example.stuevent.model.EventModel;
import com.example.stuevent.service.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class Controller {
    private final Service service;
    public Controller(Service service){
        this.service = service;
    }
    @PostMapping("/")
    public EventModel createEvent(@RequestBody EventModel model){
        return service.saveEvent(model);
    }
    @GetMapping("/")
    public List<EventModel> getEvents(){
        return service.getAllEvents();
    }
    @GetMapping("/number/{rNo}")
    public List<EventModel> getEvent(@PathVariable Integer rNo){
        return service.getEventByRNo(rNo);
    }
    @GetMapping("/{name}")
    public EventModel getEventByName(@PathVariable String name){
        return service.getEventByName(name);
    }
    @DeleteMapping("{rNo}")
    public String deleteEvent(@PathVariable Integer rNo){
        service.deleteEventByRNo(rNo);
        return "Successfully deleted";
    }
}