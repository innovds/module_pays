package org.innovds.contact.web;

import org.innovds.contact.domain.City;
import org.innovds.data.web.AbstractReadController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cities")
public class CityController  extends AbstractReadController<City, Long> {
}
