package org.innovds.pays.web;

import org.innovds.pays.Repositories.*;
import org.innovds.pays.domain.*;
import org.innovds.data.web.AbstractReadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cities")
public class CityController  extends AbstractReadController<City, Long> {

    @Autowired
    private CityRepository cityRepository;
       
    public String listAll(Model model) {
        List<City> listCities = cityRepository.findAll();
        model.addAttribute("listCities", listCities);
           
        return "Cities";
    }
  

    @GetMapping("/state/{id}")
    public State get(@PathVariable int id) {
        return null;
        
    }
}
