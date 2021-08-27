package org.innovds.contact.web;

import org.innovds.contact.domain.City;
import org.innovds.data.web.AbstractReadController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cities")
public class CityController  extends AbstractReadController<City, Long> {

    @Autowired
    private CityRepository cityRepository;
       
    public String listAll(Model model) {
        List<City> listCities = CityRepository.findAll();
        model.addAttribute("listCities", listCities);
           
        return "Cities";
    }
  

    @GetMapping("/state/{id}")
    public State get(@PathVariable int id) {
        return StateRepository.findByState_id(id).get();
        
    }
}
