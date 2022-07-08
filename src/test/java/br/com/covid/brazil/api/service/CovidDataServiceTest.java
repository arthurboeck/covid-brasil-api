package br.com.covid.brazil.api.service;

import br.com.covid.brazil.api.model.CovidData;
import br.com.covid.brazil.api.repository.CovidDataRepository;
import br.com.covid.brazil.api.util.UnitBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class CovidDataServiceTest extends UnitBaseTest {

    @Mock
    private ICovidDataService iCovidDataService;
    private CovidDataRepository covidDataRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(true);
    }

    @Test
    @DisplayName("Deve Retornar Lista CovidData Model")
    void deveRetornarListaModel() {
        doReturn(List.of(retornoCovidDataModel)).when(iCovidDataService).getAllCovidData();

        List<CovidData> retornoService = iCovidDataService.getAllCovidData();
        assertThat(retornoService).hasSize(1);
        assertEquals(retornoService.get(0), retornoCovidDataModel);
    }

    @Test
    @DisplayName("Deve Retornar CovidData Model")
    void deveRetornarModelPesquisaById() {
        doReturn(retornoCovidDataModel).when(iCovidDataService).getCovidDataById(anyInt());

        CovidData retornoService = iCovidDataService.getCovidDataById(1);
        assertEquals(retornoService, retornoCovidDataModel);
    }

    @Test
    @DisplayName("Deve Salvar ou Atualizar CovidData Model")
    void deveSalvarOuAtualziarModel() {
        doReturn(retornoCovidDataModel).when(iCovidDataService).saveOrUpdate(any());

        CovidData retornoService = iCovidDataService.saveOrUpdate(retornoCovidDataModel);
        assertEquals(retornoService, retornoCovidDataModel);
    }

    @Test
    @DisplayName("Deve Historico CovidData Model")
    void deveSalvarHistoricoModel() {
        doReturn(retornoCovidDataModel).when(iCovidDataService).salvarHistoricoConsulta(any());

        CovidData retornoService = iCovidDataService.salvarHistoricoConsulta(retornoCovidDataDto);
        assertEquals(retornoService, retornoCovidDataModel);
    }

    @Test
    @DisplayName("Deve Deletar CovidData Model")
    void deveDeletarHistorico() {
        doNothing().when(iCovidDataService).delete(anyInt());

        iCovidDataService.delete(1);
    }
}
