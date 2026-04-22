package com.example.stulogin.service;

import com.example.stuevent.model.EventModel;
import com.example.stulogin.model.LoginModel;
import com.example.stulogin.model.RegisterModel;
import com.example.stulogin.repository.RegisterRepo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;


@Service
public class LoginService {
    public final RegisterRepo registerRepo;
    private final RestTemplate restTemplate;

    public LoginService(RegisterRepo repo, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.registerRepo = repo;
    }

    public List<EventModel> findStu(LoginModel login) {
        RegisterModel stuDetails = registerRepo.findByEmail(login.getEmail());
        if (stuDetails == null) {
            return List.of();
        }
        if (stuDetails.getPassword().equals(login.getPassword())) {
            String StuEventUrl = "http://localhost:8081/events/number/" + stuDetails.getRNo();
            /*ResponseEntity EventModel[] response =
                    restTemplate.getForEntity(StuEventUrl,EventModel[].class);*/
            ResponseEntity<List<EventModel>> response =
                    restTemplate.exchange(
                            StuEventUrl,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<EventModel>>() {
                            }
                    );
            if (response.getStatusCode().is2xxSuccessful()) {
                //EventModel eventModel[]= response.getBody();
                return response.getBody();
            }
        }
        return List.of();
    }

    public EventModel findStudent(LoginModel login) {
        RegisterModel stuDetails = registerRepo.findByEmail(login.getEmail());
        if (stuDetails == null) {
            return null;
        }
        RequestEntity<Void> reqEntity =
                RequestEntity.get(
                                URI.create("http://localhost:8080/events/" + stuDetails.getRNo())
                        ).accept(MediaType.APPLICATION_JSON).build();

        ResponseEntity<EventModel> response =
                restTemplate.exchange(reqEntity, EventModel.class);
        return response.getBody();
    }
}
