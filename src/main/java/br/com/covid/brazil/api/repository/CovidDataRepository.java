package br.com.covid.brazil.api.repository;

import br.com.covid.brazil.api.model.CovidData;
import org.springframework.data.repository.CrudRepository;

public interface CovidDataRepository extends CrudRepository<CovidData, Integer> {
}
