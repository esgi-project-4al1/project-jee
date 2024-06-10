package fr.front.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import lombok.Getter;

@Getter
@Singleton
public class MyObjectMapper {

    private ObjectMapper objectMapper;

    @PostConstruct
    public void setup(){
        this.objectMapper = new ObjectMapper();
    }
}
