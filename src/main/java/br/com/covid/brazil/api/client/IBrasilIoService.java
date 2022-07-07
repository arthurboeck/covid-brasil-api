package br.com.covid.brazil.api.client;

import br.com.covid.brazil.api.dto.CovidDataDTO;
import javassist.NotFoundException;

public interface IBrasilIoService {

    CovidDataDTO obterDadosCovid(String estado, String municipio) throws NotFoundException;
}
