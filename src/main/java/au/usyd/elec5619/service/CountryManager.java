package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.Country;

public interface CountryManager {
    // CREATE

    public void addCountry(Country country);
    
    // READ

    public Country getCountry(long id);

    public Country getCountryByName(String country);
    // UPDATE

    public void updateCountry(Country country);

    // DELETE

    public void deleteCountry(long id);
}
