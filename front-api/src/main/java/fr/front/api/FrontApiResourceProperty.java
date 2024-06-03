package fr.front.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.front.api.rentalPropertydto.PropertyDto;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Path("/rental-properties")
public class FrontApiResourceProperty {

    // ajouter un constructeur et faire l'initialisation dans le constructeur
    URI API_URL = URI.create("http://localhost:8081/rent-properties-api/rental-properties");
    HttpClient client = HttpClient.newHttpClient();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRentalProperties() {
        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(API_URL)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Vérifier le code de statut HTTP
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                // Renvoyer la réponse reçue
                return Response.ok(response.body()).build();
            } else {
                // Gérer les erreurs de statut HTTP
                return Response.status(statusCode).entity("Erreur lors de la récupération des propriétés de location").build();
            }
        } catch (IOException | InterruptedException e) {
            // Gérer les erreurs
            return Response.serverError().entity("Erreur lors de la récupération des propriétés de location").build();
        }
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePartialRentalProperty(@PathParam("id") String id, @Valid PropertyDto rentalProperty) {
        try {

            String requestBody = toJson(rentalProperty);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + "/" + id))
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode == 200) {
                return Response.ok().build();
            } else if (statusCode == 400) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Requête invalide").build();
            } else {
                return Response.status(statusCode).entity("Erreur lors de la mise à jour partielle de la propriété").build();
            }
        } catch (IOException | InterruptedException e) {
            return Response.serverError().entity("Erreur lors de la mise à jour partielle de la propriété").build();
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

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode == 200) {
                return Response.ok().build();
            } else if (statusCode == 400) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Requête invalide").build();
            } else {
                return Response.status(statusCode).entity("Erreur lors de la mise à jour de la propriéte").build();
            }
        } catch (IOException | InterruptedException e) {
            return Response.serverError().entity("Erreur lors de la mise à jour de la propriété").build();
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

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode == 200) {
                return Response.ok(response.body()).build();
            } else {
                return Response.status(statusCode).entity("Erreur lors de la récupération de la propriété").build();
            }
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

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode == 204) {
                return Response.status(Response.Status.NO_CONTENT).build();
            } else {
                return Response.status(statusCode).entity("Erreur lors de la suppression de la propriété").build();
            }
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

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode == 201) {
                return Response.status(statusCode).build();
            } else if (statusCode == 400) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Requête invalide").build();
            } else {
                return Response.status(statusCode).entity("Erreur lors de la création de la propriété").build();
            }
        } catch (IOException | InterruptedException e) {
            return Response.serverError().entity("Erreur lors de la création de la propriété").build();
        }
    }


    private String toJson(PropertyDto rentalProperty) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(rentalProperty);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la conversion en JSON", e);
        }
    }
}
