package fr.front.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.front.dto.rentalCarsdto.CarDto;
import fr.front.dto.rentalCarsdto.UpdateCarDtoRentalAmount;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FrontApiResourceCarsTest {

    @InjectMocks
    private FrontApiResourceCars frontApiResourceCars;

    @Mock
    private  MyHttpClient myHttpClient;

    @Mock
    private MyObjectMapper myObjectMapper;


    private final String jsonCarDto = """
                {
                    "brand": "BMW",
                    "model": "X",
                    "rentAmount": 790.9,
                    "securityDepositAmount": 1550.9,
                    "numberOfSeats": 5,
                    "numberOfDoors": 5,
                    "hasAirConditioning": true
                }""";

    @Test
    public void testGetAllRentalPropertiesSuccess() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);


        HttpClient clientMock = mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);


        Response response = frontApiResourceCars.getAllRentalCars();


        assertEquals(200, response.getStatus());
    }

    @Test
    public void testGetAllRentalCarsIOException() throws IOException, InterruptedException {

        HttpClient clientMock = mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new IOException());


        Response response = frontApiResourceCars.getAllRentalCars();


        assertEquals(500, response.getStatus());
        assertEquals("Erreur lors de la récupération de la voiture", response.getEntity());
    }

    @Test
    public void testGetAllRentalCarsInterruptedException() throws IOException, InterruptedException {

        HttpClient clientMock = mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new InterruptedException());


        Response response = frontApiResourceCars.getAllRentalCars();


        assertEquals(500, response.getStatus());
        assertEquals("Erreur lors de la récupération de la voiture", response.getEntity());
    }

    @Test
    public void testGetRentalCarsByIdSuccess() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);

        HttpClient clientMock = mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);


        Response response = frontApiResourceCars.getRentalCarsById("1");


        assertEquals(200, response.getStatus());
    }

    @Test
    public void testGetRentalCarsByIdErrorResponse() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(404);

        HttpClient clientMock = mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);


        Response response = frontApiResourceCars.getRentalCarsById("-1");


        assertEquals(404, response.getStatus());
        assertEquals("Erreur lors de la l'ajout de la suppression ou la modification d'une car", response.getEntity());
    }

    @Test
    public void testGetRentalCarsByIdIOException() throws IOException, InterruptedException {

        HttpClient clientMock = mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new IOException());


        Response response = frontApiResourceCars.getRentalCarsById("-1");


        assertEquals(500, response.getStatus());
        assertEquals("Erreur lors de la récupération de la voiture", response.getEntity());
    }

    @Test
    public void testGetRentalCarsByIdInterruptedException() throws IOException, InterruptedException {

        HttpClient clientMock = mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new InterruptedException());


        Response response = frontApiResourceCars.getRentalCarsById("v1");


        assertEquals(500, response.getStatus());
        assertEquals("Erreur lors de la récupération de la voiture", response.getEntity());
    }


    @Test
    public void testDeleteRentalCarsByIdSuccess() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(204);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);


        try (Response response = frontApiResourceCars.deleteRentalCarsById("123")) {


            assertEquals(204, response.getStatus());
        }
    }

    @Test
    public void testDeleteRentalCarsByIdErrorResponse() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(404);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);


        try (Response response = frontApiResourceCars.deleteRentalCarsById("123")) {


            assertEquals(404, response.getStatus());
            assertEquals("Erreur lors de la l'ajout de la suppression ou la modification d'une car", response.getEntity());
        }
    }

    @Test
    public void testDeleteRentalCarsByIdIOException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new IOException());


        try (Response response = frontApiResourceCars.deleteRentalCarsById("123")) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la suppression de la voiture", response.getEntity());
        }
    }

    @Test
    public void testDeleteRentalCarsByIdInterruptedException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new InterruptedException());


        try (Response response = frontApiResourceCars.deleteRentalCarsById("123")) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la suppression de la voiture", response.getEntity());
        }
    }

    @Test
    public void testCreateRentalCarsSuccess() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(201);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        CarDto carDto = new CarDto();

        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(carDto)).thenReturn(jsonCarDto);


        Response response = frontApiResourceCars.createRentalCars(carDto);


        assertEquals(201, response.getStatus());
    }

    @Test
    public void testCreateRentalCarsBadRequest() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(400);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        CarDto carDto = new CarDto();

        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(carDto)).thenReturn(jsonCarDto);


        Response response = frontApiResourceCars.createRentalCars(carDto);


        assertEquals(400, response.getStatus());
        assertEquals("Requête invalide", response.getEntity());
    }

    @Test
    public void testCreateRentalCarsErrorResponse() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(500);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        CarDto carDto = new CarDto();

        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(carDto)).thenReturn(jsonCarDto);


        Response response = frontApiResourceCars.createRentalCars(carDto);


        assertEquals(500, response.getStatus());
        assertEquals("Erreur lors de la l'ajout de la suppression ou la modification d'une car", response.getEntity());
    }

    @Test
    public void testCreateRentalCarsIOException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new IOException());

        CarDto carDto = new CarDto();

        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(carDto)).thenReturn(jsonCarDto);


        try (Response response = frontApiResourceCars.createRentalCars(carDto)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la création de la voiture", response.getEntity());
        }
    }

    @Test
    public void testCreateRentalCarsInterruptedException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new InterruptedException());

        CarDto carDto = new CarDto();

        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(carDto)).thenReturn(jsonCarDto);

        try (Response response = frontApiResourceCars.createRentalCars(carDto)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la création de la voiture", response.getEntity());
        }
    }

    @Test
    public void testUpdateRentalCarsSuccess() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        CarDto carDto = new CarDto();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(carDto)).thenReturn(jsonCarDto);


        try (Response response = frontApiResourceCars.updateRentalCars("123", carDto)) {


            assertEquals(200, response.getStatus());
        }
    }

    @Test
    public void testUpdateRentalCarsBadRequest() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(400);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        CarDto carDto = new CarDto();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(carDto)).thenReturn(jsonCarDto);


        try (Response response = frontApiResourceCars.updateRentalCars("123", carDto)) {


            assertEquals(400, response.getStatus());
            assertEquals("Requête invalide", response.getEntity());
        }
    }

    @Test
    public void testUpdateRentalCarsErrorResponse() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(500);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        CarDto carDto = new CarDto();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(carDto)).thenReturn(jsonCarDto);


        try (Response response = frontApiResourceCars.updateRentalCars("123", carDto)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la l'ajout de la suppression ou la modification d'une car", response.getEntity());
        }
    }

    @Test
    public void testUpdateRentalCarsIOException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new IOException());

        CarDto carDto = new CarDto();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(carDto)).thenReturn(jsonCarDto);


        try (Response response = frontApiResourceCars.updateRentalCars("123", carDto)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la mise à jour de la voiture", response.getEntity());
        }
    }

    @Test
    public void testUpdateRentalCarsInterruptedException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new InterruptedException());

        CarDto carDto = new CarDto();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(carDto)).thenReturn(jsonCarDto);


        try (Response response = frontApiResourceCars.updateRentalCars("123", carDto)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la mise à jour de la voiture", response.getEntity());
        }
    }

    @Test
    public void testUpdatePartialRentalCarsSuccess() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        UpdateCarDtoRentalAmount updateCarDtoRentalAmount = new UpdateCarDtoRentalAmount();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(updateCarDtoRentalAmount)).thenReturn(jsonCarDto);

        try (Response response = frontApiResourceCars.updatePartialRentalCars("123", updateCarDtoRentalAmount)) {

            assertEquals(200, response.getStatus());
        }
    }

    @Test
    public void testUpdatePartialRentalCarsBadRequest() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(400);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        UpdateCarDtoRentalAmount updateCarDtoRentalAmount = new UpdateCarDtoRentalAmount();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(updateCarDtoRentalAmount)).thenReturn(jsonCarDto);

        try (Response response = frontApiResourceCars.updatePartialRentalCars("123", updateCarDtoRentalAmount)) {


            assertEquals(400, response.getStatus());
            assertEquals("Requête invalide", response.getEntity());
        }
    }

    @Test
    public void testUpdatePartialRentalCarsErrorResponse() throws IOException, InterruptedException {

        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(500);

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);

        UpdateCarDtoRentalAmount updateCarDtoRentalAmount = new UpdateCarDtoRentalAmount();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(updateCarDtoRentalAmount)).thenReturn(jsonCarDto);


        try (Response response = frontApiResourceCars.updatePartialRentalCars("123", updateCarDtoRentalAmount)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la l'ajout de la suppression ou la modification d'une car", response.getEntity());
        }
    }

    @Test
    public void testUpdatePartialRentalCarsIOException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new IOException());

        UpdateCarDtoRentalAmount updateCarDtoRentalAmount = new UpdateCarDtoRentalAmount();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(updateCarDtoRentalAmount)).thenReturn(jsonCarDto);


        try (Response response = frontApiResourceCars.updatePartialRentalCars("123", updateCarDtoRentalAmount)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la mise à jour partielle de la voiture", response.getEntity());
        }
    }

    @Test
    public void testUpdatePartialRentalCarsInterruptedException() throws IOException, InterruptedException {

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        when(myHttpClient.getHttpClient()).thenReturn(clientMock);

        when(clientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new InterruptedException());

        UpdateCarDtoRentalAmount updateCarDtoRentalAmount = new UpdateCarDtoRentalAmount();
        ObjectMapper objectMappermock = Mockito.mock(ObjectMapper.class);
        when(myObjectMapper.getObjectMapper()).thenReturn(objectMappermock);
        when(objectMappermock.writeValueAsString(updateCarDtoRentalAmount)).thenReturn(jsonCarDto);


        try (Response response = frontApiResourceCars.updatePartialRentalCars("123", updateCarDtoRentalAmount)) {


            assertEquals(500, response.getStatus());
            assertEquals("Erreur lors de la mise à jour partielle de la voiture", response.getEntity());
        }
    }

}