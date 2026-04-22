package com.example.stuevent.repository;

import com.example.stuevent.model.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


@org.springframework.stereotype.Repository
public interface Repository extends MongoRepository<EventModel,String> {
    List<EventModel> findByrNo(Integer rNo);
    void deleteOneByrNo(Integer rNo);
    EventModel findByName(String name);
}
