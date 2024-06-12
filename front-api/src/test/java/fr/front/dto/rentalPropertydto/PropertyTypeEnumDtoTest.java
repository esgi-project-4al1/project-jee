package fr.front.dto.rentalPropertydto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTypeEnumDtoTest {

    @Test
    public void testEnumValues() {

        PropertyTypeEnumDto[] expectedValues = {
                PropertyTypeEnumDto.HOUSE,
                PropertyTypeEnumDto.FLAT,
        };

        assertArrayEquals(expectedValues, PropertyTypeEnumDto.values());
    }

    @Test
    public void testEnumValueOf() {

        assertEquals(PropertyTypeEnumDto.FLAT, PropertyTypeEnumDto.valueOf("FLAT"));
        assertEquals(PropertyTypeEnumDto.HOUSE, PropertyTypeEnumDto.valueOf("HOUSE"));

    }

    @Test
    public void testEnumNotNull() {

        for (PropertyTypeEnumDto value : PropertyTypeEnumDto.values()) {
            assertNotNull(value);
        }
    }
}