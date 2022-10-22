package br.com.covid.brazil.api.service;

import br.com.covid.brazil.api.dto.CovidDataDTO;
import br.com.covid.brazil.api.logger.LogExecutionTime;
import br.com.covid.brazil.api.model.CovidData;
import br.com.covid.brazil.api.repository.CovidDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CovidDataService implements ICovidDataService {

    private CovidDataRepository covidDataRepository;
    private ModelMapper mapper;

    public CovidDataService(CovidDataRepository covidDataRepository, ModelMapper mapper) {
        this.covidDataRepository = covidDataRepository;
        this.mapper = mapper;
    }

    @LogExecutionTime
    public List<CovidData> getAllCovidData() {
        List<CovidData> covidDataList = new ArrayList<>();
        covidDataRepository.findAll().forEach(covidDataList::add);
        return covidDataList;
    }

    @LogExecutionTime
    public CovidData getCovidDataById(int id) {
        final var covidData = covidDataRepository.findById(id);
        if (covidData.isPresent()) {
            return covidData.get();
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @LogExecutionTime
    public CovidData saveOrUpdate(CovidData covidData) {
        return covidDataRepository.save(covidData);
    }

    @LogExecutionTime
    public void delete(int id) {
        covidDataRepository.deleteById(id);
    }

    @LogExecutionTime
    public CovidData salvarHistoricoConsulta(CovidDataDTO covidDataDTO) {
        final var covidData = this.mapper.map(covidDataDTO, CovidData.class);
        return saveOrUpdate(covidData);
    }
}
