package org.innovds.contact.web;

import org.innovds.contact.domain.City;
import org.innovds.data.web.AbstractReadController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/states")
public class StateController  extends AbstractReadController<State, Long> {

    @Autowired
    private StateRepository stateRepository;
       
    public String listAll(Model model) {
        List<State> listStates = StateRepository.findAll();
        model.addAttribute("listStates", listStates);
           
        return "States";
    }


    @GetMapping("/country/{id}")
    public State get(@PathVariable int id) {
        return StateRepository.findByCountry_id(id).get();
        
    }

}
