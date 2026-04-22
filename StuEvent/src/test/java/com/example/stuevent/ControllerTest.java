package com.example.stuevent;
import com.example.stuevent.controller.Controller;
import com.example.stuevent.model.EventModel;
import com.example.stuevent.service.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    private MockMvc mockMvc;
    @Mock
    private Service service;
    @InjectMocks
    private Controller controller;
    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        System.out.println("BeforeEach");
    }
    @Test
    void testGetEvent_API() throws Exception {
        EventModel e1 = new EventModel();
        e1.setRNo(1);
        e1.setEventName("Workshop");
        when(service.getEventByRNo(1))
                .thenReturn(List.of(e1));
        mockMvc.perform(get("/events/number/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eventName").value("Workshop"));
    }
    @Test
    public void testCreateEvent() throws Exception {
        String eventJson = "{\"rNo\":1,\"eventName\":\"Workshop\"}";
        ResultActions result = mockMvc.perform(post("/events/")
                .contentType(MediaType.APPLICATION_JSON).content(eventJson));
        result.andExpect(status().isOk());
    }
    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }
}