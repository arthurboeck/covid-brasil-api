package br.com.covid.brazil.api.client;

import br.com.covid.brazil.api.dto.CovidDataDTO;

public interface IBrasilIoClient {

    CovidDataDTO getCovidData(String estado, String cidade);
}
