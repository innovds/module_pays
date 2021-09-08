package org.innovds.pays.Repositories;

import org.innovds.pays.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findByState(int id);
}