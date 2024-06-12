package fr.front.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyObjectMapperTest {

    private MyObjectMapper myObjectMapper;

    @BeforeEach
    public void setUp() {
        myObjectMapper = new MyObjectMapper();
        myObjectMapper.setup();
    }

    @Test
    public void testHttpClientIsInitialized() {
        assertNotNull(myObjectMapper.getObjectMapper());
    }
}