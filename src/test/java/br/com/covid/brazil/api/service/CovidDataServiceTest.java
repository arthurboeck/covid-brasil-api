package br.com.covid.brazil.api.service;

import br.com.covid.brazil.api.model.CovidData;
import br.com.covid.brazil.api.repository.CovidDataRepository;
import br.com.covid.brazil.api.util.UnitBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CovidDataServiceTest extends UnitBaseTest {

    @Mock
    private CovidDataRepository covidDataRepository;

    @InjectMocks
    private CovidDataService covidDataService;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve Retornar Lista CovidData Model")
    void deveRetornarListaModel() {
        doReturn(List.of(retornoCovidDataModel)).when(covidDataRepository).findAll();

        List<CovidData> retornoService = covidDataService.getAllCovidData();
        assertThat(retornoService).hasSize(1);
        assertEquals(retornoService.get(0), retornoCovidDataModel);
    }

    @Test
    @DisplayName("Deve Retornar CovidData Model")
    void deveRetornarModelPesquisaById() {
        doReturn(Optional.of(retornoCovidDataModel)).when(covidDataRepository).findById(anyInt());

        CovidData retornoService = covidDataService.getCovidDataById(1);
        assertEquals(retornoService, retornoCovidDataModel);
    }

    @Test
    @DisplayName("Deve Salvar ou Atualizar CovidData Model")
    void deveSalvarOuAtualziarModel() {
        doReturn(retornoCovidDataModel).when(covidDataRepository).save(any());

        CovidData retornoService = covidDataService.saveOrUpdate(retornoCovidDataModel);
        assertEquals(retornoService, retornoCovidDataModel);
    }

    @Test
    @DisplayName("Deve Historico CovidData Model")
    void deveSalvarHistoricoModel() {
        doReturn(retornoCovidDataModel).when(mapper).map(retornoCovidDataDto, CovidData.class);
        doReturn(retornoCovidDataModel).when(covidDataRepository).save(retornoCovidDataModel);

        CovidData retornoService = covidDataService.salvarHistoricoConsulta(retornoCovidDataDto);
        assertEquals(retornoService, retornoCovidDataModel);
    }

    @Test
    @DisplayName("Deve Deletar CovidData Model")
    void deveDeletarHistorico() {
        doNothing().when(covidDataRepository).deleteById(anyInt());
        covidDataService.delete(1);
    }
}
