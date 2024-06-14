package fr.front.api;

import fr.front.dto.rentalPropertydto.PropertyDto;
import fr.front.dto.rentalPropertydto.RentAmountDto;
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

@Path("/rental-properties")
public class FrontApiResourceProperty {

    URI API_URL = URI.create("http://localhost:8081/rent-properties-api/rental-properties");
    private final MyHttpClient client;
    private final MyObjectMapper myObjectMapper;


    @Inject
    public FrontApiResourceProperty(MyHttpClient client, MyObjectMapper myObjectMapper) {
        this.client = client;
        this.myObjectMapper = myObjectMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRentalProperties() {
        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(API_URL)
                    .GET()
                    .build();

            HttpResponse<String> response = client.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            return handleStatusCode(response ,statusCode);
        } catch (IOException | InterruptedException e) {

            return Response.serverError().entity("Erreur lors de la récupération des propriétés").build();
        }
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePartialRentalProperty(@PathParam("id") String id, @Valid RentAmountDto rentAmountDto) {
        try {

            String requestBody = toJson(rentAmountDto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + "/" + id))
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .build();

            HttpResponse<String> response = client.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            return handleStatusCode(response ,statusCode);
        } catch (IOException | InterruptedException e) {
            return Response.serverError().entity("Erreur lors de la mise à jour partielle de la propriété").build();
        }
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRentalPropertyById(@PathParam("id") String id) {
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
            return Response.serverError().entity("Erreur lors de la récupération de la propriété").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRentalPropertyById(@PathParam("id") String id) {
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
            return Response.serverError().entity("Erreur lors de la suppression de la propriété").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRentalProperty(@Valid PropertyDto rentalProperty) {
        try {

            String requestBody = toJson(rentalProperty);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(API_URL)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .build();

            HttpResponse<String> response = client.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            return handleStatusCode(response ,statusCode);
        } catch (IOException | InterruptedException e) {
            return Response.serverError().entity("Erreur lors de la création de la propriété").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRentalProperty(@PathParam("id") String id, @Valid PropertyDto rentalProperty) {
        try {

            String requestBody = toJson(rentalProperty);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + "/" + id))
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .build();

            HttpResponse<String> response = client.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            return handleStatusCode(response ,statusCode);
        } catch (IOException | InterruptedException e) {
            return Response.serverError().entity("Erreur lors de la mise à jour de la propriété").build();
        }
    }


    public Response handleStatusCode(HttpResponse<String> response, int statusCode) {
        return switch (statusCode) {
            case 200 -> Response.ok(response.body()).build();
            case 201 -> Response.status(statusCode).build();
            case 204 -> Response.status(Response.Status.NO_CONTENT).build();
            case 400 -> Response.status(Response.Status.BAD_REQUEST).entity("Requête invalide").build();
            default ->
                    Response.status(statusCode).entity("Erreur lors de la l'ajout de la suppression ou la modification d'une property").build();
        };
    }

    private String toJson(PropertyDto rentalProperty) {
        try {
            return myObjectMapper.getObjectMapper().writeValueAsString(rentalProperty);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la conversion en JSON", e);
        }
    }

    private String toJson(RentAmountDto rentAmountDto){
        try {
            return myObjectMapper.getObjectMapper().writeValueAsString(rentAmountDto);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la conversion en JSON", e);
        }
    }
}
