package org.innovds.pays.web;

import org.innovds.pays.Repositories.*;
import org.innovds.pays.domain.*;
import org.innovds.data.web.AbstractReadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController  extends AbstractReadController<State, Long> {

    @Autowired
    private StateRepository stateRepository;
       
    public String listAll(Model model) {
        List<State> listStates = stateRepository.findAll();
        model.addAttribute("listStates", listStates);
           
        return "States";
    }


    @GetMapping("/country/{id}")
    public State get(@PathVariable int id) {
        return null;//stateRepository.findByCountry_id(id).get();
        
    }

}
