package fr.front.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.http.HttpClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class MyHttpClientTest {

    private MyHttpClient myHttpClient;

    @BeforeEach
    public void setUp() {
        myHttpClient = new MyHttpClient();
        myHttpClient.setup();
    }

    @Test
    public void testHttpClientIsInitialized() {
        assertNotNull(myHttpClient.getHttpClient());
    }
}
