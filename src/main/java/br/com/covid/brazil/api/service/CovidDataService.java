package br.com.covid.brazil.api.service;

import br.com.covid.brazil.api.client.IBrasilIoService;
import br.com.covid.brazil.api.model.CovidData;
import br.com.covid.brazil.api.repository.CovidDataRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CovidDataService {

    @Autowired
    CovidDataRepository covidDataRepository;

    @Autowired
    IBrasilIoService iBrasilIoClient;

    @Autowired
    ModelMapper mapper;

    public List<CovidData> getAllCovidData() {
        List<CovidData> covidDataList = new ArrayList<>();
        covidDataRepository.findAll().forEach(covidDataList::add);
        return covidDataList;
    }

    public CovidData getCovidDataById(int id) {
        final var covidData = covidDataRepository.findById(id);
        if (covidData.isPresent()) {
            return covidData.get();
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    public CovidData saveOrUpdate(CovidData covidData) {
        return covidDataRepository.save(covidData);
    }

    public void delete(int id) {
        covidDataRepository.deleteById(id);
    }

    public CovidData obterDadosCovid(String uf, String municipio) throws NotFoundException {
        final var covidDataDTO = iBrasilIoClient.obterDadosCovid(uf, municipio);
        final var covidData = this.mapper.map(covidDataDTO, CovidData.class);
        return saveOrUpdate(covidData);
    }
}
