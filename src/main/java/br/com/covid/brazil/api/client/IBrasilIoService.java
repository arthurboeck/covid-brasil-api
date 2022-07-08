package br.com.covid.brazil.api.client;

import br.com.covid.brazil.api.dto.CovidDataDTO;

public interface IBrasilIoService {

    CovidDataDTO obterDadosCovid(String estado, String municipio) throws RuntimeException;
}
