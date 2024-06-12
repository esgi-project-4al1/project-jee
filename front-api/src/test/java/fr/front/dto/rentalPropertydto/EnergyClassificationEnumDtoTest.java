package fr.front.dto.rentalPropertydto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnergyClassificationEnumDtoTest {

    @Test
    public void testEnumValues() {

        EnergyClassificationEnumDto[] expectedValues = {
                EnergyClassificationEnumDto.A,
                EnergyClassificationEnumDto.B,
                EnergyClassificationEnumDto.C,
                EnergyClassificationEnumDto.D,
                EnergyClassificationEnumDto.E,
                EnergyClassificationEnumDto.F,
                EnergyClassificationEnumDto.G
        };

        assertArrayEquals(expectedValues, EnergyClassificationEnumDto.values());
    }

    @Test
    public void testEnumValueOf() {

        assertEquals(EnergyClassificationEnumDto.A, EnergyClassificationEnumDto.valueOf("A"));
        assertEquals(EnergyClassificationEnumDto.B, EnergyClassificationEnumDto.valueOf("B"));
        assertEquals(EnergyClassificationEnumDto.C, EnergyClassificationEnumDto.valueOf("C"));
        assertEquals(EnergyClassificationEnumDto.D, EnergyClassificationEnumDto.valueOf("D"));
        assertEquals(EnergyClassificationEnumDto.E, EnergyClassificationEnumDto.valueOf("E"));
        assertEquals(EnergyClassificationEnumDto.F, EnergyClassificationEnumDto.valueOf("F"));
        assertEquals(EnergyClassificationEnumDto.G, EnergyClassificationEnumDto.valueOf("G"));
    }

    @Test
    public void testEnumNotNull() {

        for (EnergyClassificationEnumDto value : EnergyClassificationEnumDto.values()) {
            assertNotNull(value);
        }
    }
}