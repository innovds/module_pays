package org.innovds.contact.service;

import org.innovds.contact.domain.City;
import org.innovds.contact.domain.Country;
import org.innovds.data.service.AbstractGenericService;
import org.innovds.data.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class CountryService extends AbstractGenericService<Country, Long> implements CommandLineRunner {
    @Autowired
    private IGenericService<City, Long> cityService;

    // to run everytime the project launch
    @Override
    public void run(String... args) throws Exception {
        Country maroc = new Country("Maroc", "Mar", "Ma", 504, "212", "MAD",
                "DH", "المغرب", new HashSet<>());
        Country france = new Country("France", "Fra", "Fr", 250, "33", "EUR",
                "€", "France", new HashSet<>());

        maroc = this.repository.save(maroc);
        france = this.repository.save(france);
        City fes = new City("Fès", maroc);
        City rabat = new City("Rabat", maroc);
        City casablanca = new City("Casablanca", maroc);
        City tanger = new City("Tanger", maroc);
        City paris = new City("Paris", france);
        City leMans = new City("Le Mans", france);
        this.cityService.save(fes);
        this.cityService.save(rabat);
        this.cityService.save(casablanca);
        this.cityService.save(tanger);
        this.cityService.save(paris);
        this.cityService.save(leMans);
    }

}
