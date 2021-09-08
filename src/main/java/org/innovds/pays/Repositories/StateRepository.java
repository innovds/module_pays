package org.innovds.pays.Repositories;

import org.innovds.pays.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Integer> {


    List<State> findByCountry(int id);
}