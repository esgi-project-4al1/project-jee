package com.rent.car.rentproperty.controller;

import com.rent.car.rentproperty.dto.EnergyClassificationEnumDto;
import com.rent.car.rentproperty.dto.RentalPropertiesDto;
import com.rent.car.rentproperty.dto.RentalPropertyDto;
import com.rent.car.rentproperty.dto.RentalPropertyTypeEnumDto;
import com.rent.car.rentproperty.service.RentalPropertyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.rent.car.rentproperty.utils.TestUtils.readResource;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@WebMvcTest(RentalPropertyController.class)
class RentalPropertyControllerTest {

    @Value("classpath:/json/goodRentalProperty.json")
    private Resource rentalProperty;

    @Value("classpath:/json/goodUpdateRentalAmount.json")
    private Resource updateRentalAmount;

    @Value("classpath:/json/goodRentalProperties.json")
    private Resource rentalProperties;

    @Value("classpath:/json/goodRentalPropertie.json")
    private Resource rentalPropertie;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalPropertyService rentalPropertyService;

    @Test
    void shouldGetAllRentalProperties() throws Exception {
        List<RentalPropertiesDto> rentalPropertiesDtoList =  List.of(createRentalPropertiesDto());
        when(this.rentalPropertyService.findAllRentalProperties()).thenReturn(rentalPropertiesDtoList);
        mockMvc.perform(get("/rent-properties-api/rental-properties"))
                .andExpect(status().isOk())
                .andExpect(content().json(readResource(rentalProperties)));

        verify(rentalPropertyService).findAllRentalProperties();
        verifyNoMoreInteractions(rentalPropertyService);
    }

    @Test
    void shouldGetRentalProperties() throws Exception {
        RentalPropertiesDto rentalPropertiesDto =  createRentalPropertiesDto();
        String id = "1";
        when(this.rentalPropertyService.findRentalProperty(1)).thenReturn(rentalPropertiesDto);
        mockMvc.perform(get("/rent-properties-api/rental-properties/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(readResource(rentalPropertie)));

        verify(rentalPropertyService).findRentalProperty(1);
        verifyNoMoreInteractions(rentalPropertyService);
    }

    @Test
    void postRentalProperties() throws Exception {
        mockMvc.perform(post("/rent-properties-api/rental-properties")
                .contentType(APPLICATION_JSON_VALUE)
                .content(readResource(rentalProperty)))
                .andExpect(status().isCreated());
        verify(rentalPropertyService).saveRentalProperty(any());
        verifyNoMoreInteractions(rentalPropertyService);
    }


    @Test
    void patchRentalProperties() throws Exception {
        String id = "1";
        mockMvc.perform(patch("/rent-properties-api/rental-properties/{id}", id)
                .contentType(APPLICATION_JSON_VALUE)
                .content(readResource(updateRentalAmount)))
                .andExpect(status().isOk());
        verify(rentalPropertyService).updateRentalAmount(eq(1), any());
        verifyNoMoreInteractions(rentalPropertyService);
    }

    @Test
    void deleteRentalProperties() throws Exception {
        String id = "1";
        mockMvc.perform(delete("/rent-properties-api/rental-properties/{id}", id))
                .andExpect(status().isNoContent());
        verify(rentalPropertyService).deleteRentalProperty(1);
        verifyNoMoreInteractions(rentalPropertyService);
    }

    @Test
    void putRentalProperties() throws Exception {
        String id = "1";
        mockMvc.perform(put("/rent-properties-api/rental-properties/{id}", id)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalProperty)))
                .andExpect(status().isOk());
        verify(rentalPropertyService).updateRentalProperty(eq(1), any());
        verifyNoMoreInteractions(rentalPropertyService);
    }

    @Test
    void deleteRentalPropertiesNotGoodRequest() throws Exception {
        mockMvc.perform(delete("/rent-properties-api/rental-properties/"))
                .andExpect(status().isNotFound());
    }

    private RentalPropertyDto createRentalPropertyDto(){
        return new RentalPropertyDto(
                "Appartement bien situé près du métro et des commerces 2",
                "Neuilly-sur-Seine",
                "address",
                RentalPropertyTypeEnumDto.FLAT,
                10.0,
                12.0,
                10.0,
                1,
                10,
                20,
                "2022",
                EnergyClassificationEnumDto.A,
                true,
                true,
                true,
                true

        );
    }

    private RentalPropertiesDto createRentalPropertiesDto(){
        return new RentalPropertiesDto(
                "90 rue de la Victoire",
                50.69,
                "Appartement bien situé près du métro et des commerces",
                "FLAT",
                1040.9,
                1040.9,
                "Neuilly-sur-Seine"
        );
    }

}