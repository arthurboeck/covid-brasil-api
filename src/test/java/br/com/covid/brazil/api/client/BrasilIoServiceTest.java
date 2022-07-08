package br.com.covid.brazil.api.client;

import br.com.covid.brazil.api.dto.CovidDataDTO;
import br.com.covid.brazil.api.util.UnitBaseTest;
import feign.FeignException;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;


@ExtendWith(MockitoExtension.class)
public class BrasilIoServiceTest extends UnitBaseTest {

    @Mock
    private IBrasilIoClient iBrasilIoClient;
    private BrasilIoService brasilIoService;

    @Mock
    private FeignException feignException;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(true);
        brasilIoService = new BrasilIoService("A", iBrasilIoClient);
    }

    @Test
    @DisplayName("Deve Retornar Sucesso Feign Client")
    void deveRetornarBrasiolIoComSucesso() throws NotFoundException {
        BrasilIoDTO retornoFeignClient = new BrasilIoDTO(List.of(retornoSucesso));
        doReturn(retornoFeignClient).when(iBrasilIoClient).getCovidData(anyString(), anyString(), anyString(), anyString());

        CovidDataDTO retorno = brasilIoService.obterDadosCovid("A", "A");
        assertEquals(retorno.getUf(), retornoSucesso.getUf());
        assertEquals(retorno.getMunicipio(), retornoSucesso.getMunicipio());
        assertEquals(retorno.getCodigoIbgeMunicipio(), retornoSucesso.getCodigoIbgeMunicipio());
        assertEquals(retorno.getRankingMunicipio(), retornoSucesso.getRankingMunicipio());
        assertEquals(retorno.getDataColetaDados(), retornoSucesso.getDataColetaDados());
        assertEquals(retorno.getSemanaEpidemiologica(), retornoSucesso.getSemanaEpidemiologica());
        assertEquals(retorno.getPopulacaoEstimada(), retornoSucesso.getPopulacaoEstimada());
        assertEquals(retorno.getPopulacaoEstimada2019(), retornoSucesso.getPopulacaoEstimada2019());
        assertEquals(retorno.getCasosConfirmadosUltimoDia(), retornoSucesso.getCasosConfirmadosUltimoDia());
        assertEquals(retorno.getCasosConfirmadosUltimoDiaPor100kHabitantes(), retornoSucesso.getCasosConfirmadosUltimoDiaPor100kHabitantes());
        assertEquals(retorno.getDataDado(), retornoSucesso.getDataDado());
        assertEquals(retorno.getTaxaMortalidadeUltimoDia(), retornoSucesso.getTaxaMortalidadeUltimoDia());
        assertEquals(retorno.getMortesUltimoDia(), retornoSucesso.getMortesUltimoDia());
        assertEquals(retorno.getNovosCasos(), retornoSucesso.getNovosCasos());
        assertEquals(retorno.getNovasMortes(), retornoSucesso.getNovasMortes());
    }

    @Test
    @DisplayName("Deve Retornar Not Found - Lista Vazia")
    void deveRetornarNotFoundBrasiolIoListaVazia() {
        BrasilIoDTO retornoVazio = new BrasilIoDTO(Collections.emptyList());
        doReturn(retornoVazio).when(iBrasilIoClient).getCovidData(anyString(), anyString(), anyString(), anyString());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> brasilIoService.obterDadosCovid("B", "B"));
        assertThat(exception.getMessage()).contains("Registro não encontrado:");
    }

    @Test
    @DisplayName("Deve Retornar Not Found Exception - Erro RuntimeException")
    void deveRetornarNotFoundExceptionErroRuntimeException() {
        doThrow(RuntimeException.class).when(iBrasilIoClient).getCovidData(anyString(), anyString(), anyString(), anyString());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> brasilIoService.obterDadosCovid("C", "C"));
        assertThat(exception.getMessage()).contains("Registro não encontrado:");
    }
}
