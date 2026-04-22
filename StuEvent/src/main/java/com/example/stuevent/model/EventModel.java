package com.example.stuevent.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import java.lang.ScopedValue;

@Document(collection="events")
@Data
public class EventModel {
    @Id
    private String id;
    private String name;
    private Integer rNo;
    private String eventName;
    private String eventLocation;
    private String eventDescription;
}
