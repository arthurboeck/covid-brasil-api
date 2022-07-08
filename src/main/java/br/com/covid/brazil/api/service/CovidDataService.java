package br.com.covid.brazil.api.service;

import br.com.covid.brazil.api.client.IBrasilIoService;
import br.com.covid.brazil.api.dto.CovidDataDTO;
import br.com.covid.brazil.api.model.CovidData;
import br.com.covid.brazil.api.repository.CovidDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CovidDataService implements ICovidDataService {

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

    public CovidData salvarHistoricoConsulta(CovidDataDTO covidDataDTO) {
        final var covidData = this.mapper.map(covidDataDTO, CovidData.class);
        return saveOrUpdate(covidData);
    }
}
