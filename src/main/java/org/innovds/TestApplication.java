package org.innovds;

import org.innovds.pays.Repositories.CountryRepository;
import org.innovds.pays.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class TestApplication implements CommandLineRunner {
 
    @Autowired
    private CountryRepository countryRepository;
     
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
 
    @Override
    public void run(String... args) throws Exception {
        List<Country> countries = countryRepository.findAll();
        Optional<Country> country = countryRepository.findById(10);
        countries.forEach(System.out::println);
        System.out.println(country);
    }
 
}