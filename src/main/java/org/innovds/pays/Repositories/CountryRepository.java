package org.innovds.pays.Repositories;

import org.innovds.pays.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

 
public interface CountryRepository extends JpaRepository<Country, Integer> {
 
}