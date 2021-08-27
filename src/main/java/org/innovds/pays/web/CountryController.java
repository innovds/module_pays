package org.innovds.contact.web;

import org.innovds.contact.domain.Country;
import org.innovds.data.web.AbstractReadController;
import org.springframework.web.bind.annotation.*;

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
