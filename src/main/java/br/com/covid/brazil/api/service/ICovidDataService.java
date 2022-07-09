package br.com.covid.brazil.api.service;

import br.com.covid.brazil.api.dto.CovidDataDTO;
import br.com.covid.brazil.api.model.CovidData;

import java.util.List;

public interface ICovidDataService {
    List<CovidData> getAllCovidData();

    CovidData getCovidDataById(int id);

    CovidData saveOrUpdate(CovidData covidData);

    void delete(int id);

    CovidData salvarHistoricoConsulta(CovidDataDTO covidDataDTO);
}
