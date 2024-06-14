package fr.front.api;

import fr.front.dto.rentalCarsdto.CarDto;
import fr.front.dto.rentalCarsdto.UpdateCarDtoRentalAmount;
import fr.front.service.MyHttpClient;
import fr.front.service.MyObjectMapper;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Path("/rental-cars")
public class FrontApiResourceCars {

    URI API_URL = URI.create("http://localhost:8082/rent-cars-api/rental-cars");
    private final MyHttpClient client;
    private final MyObjectMapper myObjectMapper;

    @Inject
    public FrontApiResourceCars(MyHttpClient client, MyObjectMapper myObjectMapper) {
        this.client = client;
        this.myObjectMapper = myObjectMapper;

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRentalCars() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(API_URL)
                    .GET()
                    .build();
            HttpResponse<String> response = client.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            return handleStatusCode(response ,statusCode);
        } catch (IOException | InterruptedException e) {
            return Response.serverError().entity("Erreur lors de la récupération de la voiture").build();
        }
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePartialRentalCars(@PathParam("id") String id, @Valid UpdateCarDtoRentalAmount updateCarDtoRentalAmount) {
        try {

            String requestBody = toJson(updateCarDtoRentalAmount);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + "/" + id))
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .build();

            HttpResponse<String> response = client.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            return handleStatusCode(response ,statusCode);
        } catch (IOException | InterruptedException e) {
            return Response.serverError().entity("Erreur lors de la mise à jour partielle de la voiture").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRentalCars(@PathParam("id") String id, @Valid CarDto carDto) {
        try {

            String requestBody = toJson(carDto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + "/" + id))
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .build();

            HttpResponse<String> response = client.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            return handleStatusCode(response ,statusCode);
        } catch (IOException | InterruptedException e) {
            return Response.serverError().entity("Erreur lors de la mise à jour de la voiture").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRentalCarsById(@PathParam("id") String id) {
        try {
            String urlWithId = API_URL + "/" + id;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlWithId))
                    .GET()
                    .build();

            HttpResponse<String> response = client.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            return handleStatusCode(response ,statusCode);
        } catch (IOException | InterruptedException e) {
            return Response.serverError().entity("Erreur lors de la récupération de la voiture").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRentalCarsById(@PathParam("id") String id) {

        try {
            String urlWithId = API_URL + "/" + id;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlWithId))
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            return handleStatusCode(response ,statusCode);
        } catch (IOException | InterruptedException e) {
            return Response.serverError().entity("Erreur lors de la suppression de la voiture").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRentalCars(@Valid CarDto carDto) {
        try {

            String requestBody = toJson(carDto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(API_URL)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .build();

            HttpResponse<String> response = client.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            return handleStatusCode(response ,statusCode);
        } catch (IOException | InterruptedException e) {
            return Response.serverError().entity("Erreur lors de la création de la voiture").build();
        }
    }

    public Response handleStatusCode(HttpResponse<String>  response, int statusCode) {
        return switch (statusCode) {
            case 200 -> Response.ok(response.body()).build();
            case 201 -> Response.status(statusCode).build();
            case 204 -> Response.status(Response.Status.NO_CONTENT).build();
            case 400 -> Response.status(Response.Status.BAD_REQUEST).entity("Requête invalide").build();
            default -> Response.status(statusCode).entity("Erreur lors de la l'ajout de la suppression ou la modification d'une car").build();
        };
    }


    private String toJson(CarDto carsDto) {
        try {
            return myObjectMapper.getObjectMapper().writeValueAsString(carsDto);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la conversion en JSON", e);
        }
    }

    private String toJson(UpdateCarDtoRentalAmount updateCarDtoRentalAmount){
        try {
            return myObjectMapper.getObjectMapper().writeValueAsString(updateCarDtoRentalAmount);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la conversion en JSON", e);
        }
    }

}
