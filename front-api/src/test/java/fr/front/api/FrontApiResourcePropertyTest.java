package fr.front.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.front.dto.rentalPropertydto.PropertyDto;
import fr.front.service.MyHttpClient;
import fr.front.service.MyObjectMapper;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FrontApiResourcePropertyTest {

    @InjectMocks
    private FrontApiResourceProperty frontApiResourceProperty;

    @Mock
    private MyHttpClient myHttpClient;

    @Mock
    private MyObjectMapper myObjectMapper;

    private final String jsonPropertyDto = """
            {
                "description":"Appartement bien situé près du métro et des commerces",
                "town": "Neuilly-sur-Seine",
                "address": "90 rue de la Victoire",
                "propertyType": "FLAT",
                "rentAmount": 1040.9,
                "securityDepositAmount": 1250.4,
                "area": 50.69,
                "numberOfBedrooms": 3,
                "floorNumber": 2,
                "numberOfFloors": 5,
                "constructionYear": 1989,
                "energyClassification": "B",
                "hasElevator": true,
                "hasIntercom": true,
                "hasBalcony": true,
                "hasParkingSpace": true
            }""";

    @Test
    public void testGetAllRentalPropertiesSuccess() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);


        HttpClient clientMock = mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);


        Response response = frontApiResourceProperty.getAllRentalProperties();


        assertEquals(200, response.getStatus());

    }

    @Test
    public void testgetAllRentalPropertiesIOException() throws IOException, InterruptedException {

        HttpClient clientMock = mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new IOException());


        Response response = frontApiResourceProperty.getAllRentalProperties();


        assertEquals(500, response.getStatus());
        assertEquals("Erreur lors de la récupération des propriétés", response.getEntity());
    }

    @Test
    public void testgetAllRentalPropertiesInterruptedException() throws IOException, InterruptedException {

        HttpClient clientMock = mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new InterruptedException());


        Response response = frontApiResourceProperty.getAllRentalProperties();

        assertEquals(500, response.getStatus());
        assertEquals("Erreur lors de la récupération des propriétés", response.getEntity());
    }

    @Test
    public void testGetRentalPropertyByIdSuccess() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);

        HttpClient clientMock = mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);


        Response response = frontApiResourceProperty.getRentalPropertyById("1");


        assertEquals(200, response.getStatus());
    }

    @Test
    public void testGetRentalPropertyByIdErrorResponse() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(404);

        HttpClient clientMock = mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);


        Response response = frontApiResourceProperty.getRentalPropertyById("-1");


        assertEquals(404, response.getStatus());
        assertEquals("Erreur lors de la l'ajout de la suppression ou la modification d'une property", response.getEntity());
    }

    @Test
    public void testgetRentalPropertyByIdIOException() throws IOException, InterruptedException {

        HttpClient clientMock = mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new IOException());


        Response response = frontApiResourceProperty.getRentalPropertyById("-1");


        assertEquals(500, response.getStatus());
        assertEquals("Erreur lors de la récupération de la propriété", response.getEntity());
    }

    @Test
    public void testgetRentalPropertyByIdInterruptedException() throws IOException, InterruptedException {

        HttpClient clientMock = mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new InterruptedException());


        Response response = frontApiResourceProperty.getRentalPropertyById("v1");


        assertEquals(500, response.getStatus());
        assertEquals("Erreur lors de la récupération de la propriété", response.getEntity());
    }


    @Test
    public void testdeleteRentalPropertyByIdSuccess() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(204);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);


        try (Response response = frontApiResourceProperty.deleteRentalPropertyById("123")) {


            assertEquals(204, response.getStatus());
        }
    }

    @Test
    public void testdeleteRentalPropertyByIdErrorResponse() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(404);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);


        try (Response response = frontApiResourceProperty.deleteRentalPropertyById("123")) {


            assertEquals(404, response.getStatus());
            assertEquals("Erreur lors de la l'ajout de la suppression ou la modification d'une property", response.getEntity());
        }
    }

    @Test
    public void testdeleteRentalPropertyByIdIOException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new IOException());


        try (Response response = frontApiResourceProperty.deleteRentalPropertyById("123")) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la suppression de la propriété", response.getEntity());
        }
    }

    @Test
    public void testdeleteRentalPropertyByIdInterruptedException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new InterruptedException());


        try (Response response = frontApiResourceProperty.deleteRentalPropertyById("123")) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la suppression de la propriété", response.getEntity());
        }
    }

    @Test
    public void testcreateRentalPropertySuccess() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(201);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        PropertyDto propertyDto = new PropertyDto();

        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(propertyDto)).thenReturn(jsonPropertyDto);


        Response response = frontApiResourceProperty.createRentalProperty(propertyDto);


        assertEquals(201, response.getStatus());
    }

    @Test
    public void testcreateRentalPropertyBadRequest() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(400);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        PropertyDto propertyDto = new PropertyDto();

        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(propertyDto)).thenReturn(jsonPropertyDto);


        Response response = frontApiResourceProperty.createRentalProperty(propertyDto);


        assertEquals(400, response.getStatus());
        assertEquals("Requête invalide", response.getEntity());
    }

    @Test
    public void testcreateRentalPropertyErrorResponse() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(500);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        PropertyDto propertyDto = new PropertyDto();

        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(propertyDto)).thenReturn(jsonPropertyDto);


        Response response = frontApiResourceProperty.createRentalProperty(propertyDto);


        assertEquals(500, response.getStatus());
        assertEquals("Erreur lors de la l'ajout de la suppression ou la modification d'une property", response.getEntity());
    }

    @Test
    public void testcreateRentalPropertyIOException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new IOException());

        PropertyDto propertyDto = new PropertyDto();

        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(propertyDto)).thenReturn(jsonPropertyDto);


        try (Response response = frontApiResourceProperty.createRentalProperty(propertyDto)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la création de la propriété", response.getEntity());
        }
    }

    @Test
    public void testcreateRentalPropertyInterruptedException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new InterruptedException());

        PropertyDto propertyDto = new PropertyDto();

        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(propertyDto)).thenReturn(jsonPropertyDto);

        try (Response response = frontApiResourceProperty.createRentalProperty(propertyDto)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la création de la propriété", response.getEntity());
        }
    }

    @Test
    public void testupdateRentalPropertySuccess() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        PropertyDto propertyDto = new PropertyDto();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(propertyDto)).thenReturn(jsonPropertyDto);


        try (Response response = frontApiResourceProperty.updateRentalProperty("123", propertyDto)) {


            assertEquals(200, response.getStatus());
        }
    }

    @Test
    public void testupdateRentalPropertyBadRequest() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(400);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        PropertyDto propertyDto = new PropertyDto();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(propertyDto)).thenReturn(jsonPropertyDto);


        try (Response response = frontApiResourceProperty.updateRentalProperty("123", propertyDto)) {


            assertEquals(400, response.getStatus());
            assertEquals("Requête invalide", response.getEntity());
        }
    }

    @Test
    public void testupdateRentalPropertyErrorResponse() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(500);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        PropertyDto propertyDto = new PropertyDto();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(propertyDto)).thenReturn(jsonPropertyDto);


        try (Response response = frontApiResourceProperty.updateRentalProperty("123", propertyDto)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la l'ajout de la suppression ou la modification d'une property", response.getEntity());
        }
    }

    @Test
    public void testupdateRentalPropertyIOException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new IOException());

        PropertyDto propertyDto = new PropertyDto();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(propertyDto)).thenReturn(jsonPropertyDto);


        try (Response response = frontApiResourceProperty.updateRentalProperty("123", propertyDto)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la mise à jour de la propriété", response.getEntity());
        }
    }

    @Test
    public void testupdateRentalPropertyInterruptedException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new InterruptedException());

        PropertyDto propertyDto = new PropertyDto();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(propertyDto)).thenReturn(jsonPropertyDto);


        try (Response response = frontApiResourceProperty.updateRentalProperty("123", propertyDto)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la mise à jour de la propriété", response.getEntity());
        }
    }

    @Test
    public void testupdatePartialRentalPropertySuccess() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        PropertyDto propertyDto = new PropertyDto();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(propertyDto)).thenReturn(jsonPropertyDto);


        try (Response response = frontApiResourceProperty.updatePartialRentalProperty("123", propertyDto)) {


            assertEquals(200, response.getStatus());
        }
    }

    @Test
    public void testupdatePartialRentalPropertyBadRequest() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(400);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        PropertyDto propertyDto = new PropertyDto();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(propertyDto)).thenReturn(jsonPropertyDto);


        try (Response response = frontApiResourceProperty.updatePartialRentalProperty("123", propertyDto)) {


            assertEquals(400, response.getStatus());
            assertEquals("Requête invalide", response.getEntity());
        }
    }

    @Test
    public void testupdatePartialRentalPropertyErrorResponse() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(500);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        PropertyDto propertyDto = new PropertyDto();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(propertyDto)).thenReturn(jsonPropertyDto);


        try (Response response = frontApiResourceProperty.updatePartialRentalProperty("123", propertyDto)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la l'ajout de la suppression ou la modification d'une property", response.getEntity());
        }
    }

    @Test
    public void testupdatePartialRentalPropertyIOException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new IOException());

        PropertyDto propertyDto = new PropertyDto();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(propertyDto)).thenReturn(jsonPropertyDto);


        try (Response response = frontApiResourceProperty.updatePartialRentalProperty("123", propertyDto)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la mise à jour partielle de la propriété", response.getEntity());
        }
    }

    @Test
    public void testupdatePartialRentalPropertyInterruptedException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new InterruptedException());

        PropertyDto propertyDto = new PropertyDto();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(propertyDto)).thenReturn(jsonPropertyDto);


        try (Response response = frontApiResourceProperty.updatePartialRentalProperty("123", propertyDto)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la mise à jour partielle de la propriété", response.getEntity());
        }
    }
}