package fr.front.service;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import lombok.Getter;

import java.net.http.HttpClient;

@Getter
@Singleton
public class MyHttpClient {

    private HttpClient httpClient;

    @PostConstruct
    public void setup() {
        this.httpClient = HttpClient.newHttpClient();
    }
}

