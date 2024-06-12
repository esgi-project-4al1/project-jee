package fr.front.dto.rentalPropertydto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PropertiesDtoTest {

    @Test
    public void testPropertiesDtoCreation() {
        // Création d'une instance de PropertiesDto
        PropertiesDto properties = new PropertiesDto(
                "ESGI",
                100.0,
                "Beautiful property",
                "Apartment",
                1500.0,
                3000.0,
                "PARIS"
        );

        // Vérification des valeurs des champs
        assertEquals("ESGI", properties.address());
        assertEquals(100.0, properties.area());
        assertEquals("Beautiful property", properties.description());
        assertEquals("Apartment", properties.propertyTypeDto());
        assertEquals(1500.0, properties.rentAmount());
        assertEquals(3000.0, properties.securityDepositAmount());
        assertEquals("PARIS", properties.town());
    }

    @Test
    public void testPropertiesDtoEquality() {
        // Création de deux instances identiques de PropertiesDto
        PropertiesDto properties1 = new PropertiesDto(
                "ESGI",
                100.0,
                "Beautiful property",
                "Apartment",
                1500.0,
                3000.0,
                "PARIS"
        );
        PropertiesDto properties2 = new PropertiesDto(
                "ESGI",
                100.0,
                "Beautiful property",
                "Apartment",
                1500.0,
                3000.0,
                "PARIS"
        );

        // Vérification de l'égalité des instances
        assertEquals(properties1, properties2);
        assertEquals(properties1.hashCode(), properties2.hashCode());
    }

    @Test
    public void testPropertiesDtoToString() {
        // Création d'une instance de PropertiesDto
        PropertiesDto properties = new PropertiesDto(
                "ESGI",
                100.0,
                "Beautiful property",
                "Apartment",
                1500.0,
                3000.0,
                "PARIS"
        );

        // Vérification du contenu de la méthode toString()
        String expectedToString = "PropertiesDto[address=ESGI, area=100.0, description=Beautiful property, propertyTypeDto=Apartment, rentAmount=1500.0, securityDepositAmount=3000.0, town=PARIS]";
        assertEquals(expectedToString, properties.toString());
    }

    @Test
    public void testPropertiesDtoNullValues() {
        // Création d'une instance de PropertiesDto avec des valeurs nulles
        PropertiesDto properties = new PropertiesDto(
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        // Vérification des valeurs des champs
        assertNull(properties.address());
        assertNull(properties.area());
        assertNull(properties.description());
        assertNull(properties.propertyTypeDto());
        assertNull(properties.rentAmount());
        assertNull(properties.securityDepositAmount());
        assertNull(properties.town());
    }
}
