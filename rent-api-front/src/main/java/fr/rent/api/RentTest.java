package fr.rent.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/hello")
public class RentTest {

    @GET
    public String hello() {
        return "hello";
    }
}
