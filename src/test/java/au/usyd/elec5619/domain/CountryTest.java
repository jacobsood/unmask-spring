package au.usyd.elec5619.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

class CountryTest {
    private static Country country;

    @BeforeAll
    static void setUp() {
        country = new Country();
    }

    @Test
    void testSetAndGetId() {
        Long testId = 45498415L;
        assertNull(country.getId());
        country.setId(testId);
        assertEquals(testId, country.getId(), "Country Id is not being set correctly.");
    }

    void testSetAndGetCountry() {
        String testCountry = "Peru";
        assertNull(country.getCountry());
        country.setCountry(testCountry);
        assertEquals(testCountry, country.getCountry(), "Country is not being set correctly.");
    }
}
