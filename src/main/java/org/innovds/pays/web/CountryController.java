package org.innovds.pays.web;

import org.innovds.pays.Repositories.*;
import org.innovds.pays.domain.*;
import org.innovds.data.web.AbstractReadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController  extends AbstractReadController<Country, Long> {

    @Autowired
    private CountryRepository countryRepository;
       
    public String listAll(Model model) {
        List<Country> listCountries = countryRepository.findAll();
        model.addAttribute("listCountries", listCountries);
           
        return "Countries";
    }
}
