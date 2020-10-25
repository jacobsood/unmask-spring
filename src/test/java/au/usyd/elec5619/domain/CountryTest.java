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
    void testSetAndGetCountry() {
        String testCountry = "Australia";
        assertNull(country.getCountry());
        country.setCountry(testCountry);
        assertEquals(testCountry, country.getCountry(), "Country is not being set correctly");
    }
}
