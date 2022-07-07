package br.com.covid.brazil.api.controller;

import br.com.covid.brazil.api.client.IBrasilIoClient;
import br.com.covid.brazil.api.util.UnitBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CovidDataController.class)
class CovidDataControllerTest extends UnitBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBrasilIoClient iBrasilIoClient;

    @ParameterizedTest
    @ValueSource(strings = {"alegrete", "Alegrete", "ALEGRETE", "aLEGRETE"})
    @DisplayName("Deve Retornar Dados Covid com Sucesso - Variaçãoes do Parametro Municipio")
    void deveRetornarDadosCovidVariacaoParamMunicipio(String municipio) throws Exception {
        doReturn(retornoSucesso).when(iBrasilIoClient).getCovidData(anyString(), anyString());

        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/covid")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("uf", "RS")
                        .param("municipio", municipio))
                .andExpect(status().isOk())
                .andExpect(jsonPath("uf", is(retornoSucesso.getUf())))
                .andExpect(jsonPath("municipio", is(retornoSucesso.getMunicipio())))
                .andExpect(jsonPath("codigoIbgeMunicipio", is(retornoSucesso.getCodigoIbgeMunicipio().intValue())))
                .andExpect(jsonPath("rankingMunicipio", is(retornoSucesso.getRankingMunicipio().intValue())))
                .andExpect(jsonPath("dataColetaDados", is(retornoSucesso.getDataColetaDados().format(DateTimeFormatter.ISO_LOCAL_DATE))))
                .andExpect(jsonPath("semanaEpidemiologica", is(retornoSucesso.getSemanaEpidemiologica())))
                .andExpect(jsonPath("populacaoEstimada", is(retornoSucesso.getPopulacaoEstimada().intValue())))
                .andExpect(jsonPath("populacaoEstimada2019", is(retornoSucesso.getPopulacaoEstimada2019().intValue())))
                .andExpect(jsonPath("casosConfirmadosUltimoDia", is(retornoSucesso.getCasosConfirmadosUltimoDia().intValue())))
                .andExpect(jsonPath("casosConfirmadosUltimoDiaPor100kHabitantes", is(retornoSucesso.getCasosConfirmadosUltimoDiaPor100kHabitantes().intValue())))
                .andExpect(jsonPath("dataDado", is(retornoSucesso.getDataDado().format(DateTimeFormatter.ISO_LOCAL_DATE))))
                .andExpect(jsonPath("taxaMortalidadeUltimoDia", is(retornoSucesso.getTaxaMortalidadeUltimoDia().intValue())))
                .andExpect(jsonPath("mortesUltimoDia", is(retornoSucesso.getMortesUltimoDia().intValue())))
                .andExpect(jsonPath("novosCasos", is(retornoSucesso.getNovosCasos().intValue())))
                .andExpect(jsonPath("novasMortes", is(retornoSucesso.getNovasMortes().intValue())));
    }

    @ParameterizedTest
    @ValueSource(strings = {"rs", "RS", "rS", "Rs"})
    @DisplayName("Deve Retornar Dados Covid com Sucesso - Variaçãoes do Parametro Estado")
    void deveRetornarDadosCovidVariacaoParamEstado(String estado) throws Exception {
        doReturn(retornoSucesso).when(iBrasilIoClient).getCovidData(anyString(), anyString());

        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/covid")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("uf", estado)
                        .param("municipio", "Alegrete"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("uf", is(retornoSucesso.getUf())))
                .andExpect(jsonPath("municipio", is(retornoSucesso.getMunicipio())))
                .andExpect(jsonPath("codigoIbgeMunicipio", is(retornoSucesso.getCodigoIbgeMunicipio().intValue())))
                .andExpect(jsonPath("rankingMunicipio", is(retornoSucesso.getRankingMunicipio().intValue())))
                .andExpect(jsonPath("dataColetaDados", is(retornoSucesso.getDataColetaDados().format(DateTimeFormatter.ISO_LOCAL_DATE))))
                .andExpect(jsonPath("semanaEpidemiologica", is(retornoSucesso.getSemanaEpidemiologica())))
                .andExpect(jsonPath("populacaoEstimada", is(retornoSucesso.getPopulacaoEstimada().intValue())))
                .andExpect(jsonPath("populacaoEstimada2019", is(retornoSucesso.getPopulacaoEstimada2019().intValue())))
                .andExpect(jsonPath("casosConfirmadosUltimoDia", is(retornoSucesso.getCasosConfirmadosUltimoDia().intValue())))
                .andExpect(jsonPath("casosConfirmadosUltimoDiaPor100kHabitantes", is(retornoSucesso.getCasosConfirmadosUltimoDiaPor100kHabitantes().intValue())))
                .andExpect(jsonPath("dataDado", is(retornoSucesso.getDataDado().format(DateTimeFormatter.ISO_LOCAL_DATE))))
                .andExpect(jsonPath("taxaMortalidadeUltimoDia", is(retornoSucesso.getTaxaMortalidadeUltimoDia().intValue())))
                .andExpect(jsonPath("mortesUltimoDia", is(retornoSucesso.getMortesUltimoDia().intValue())))
                .andExpect(jsonPath("novosCasos", is(retornoSucesso.getNovosCasos().intValue())))
                .andExpect(jsonPath("novasMortes", is(retornoSucesso.getNovasMortes().intValue())));
    }

    @Test
    @DisplayName("Deve Retornar Not Found - Erro ao Obter Dados Covid")
    void deveRetornarNotFoundErroObterDadosCovid() throws Exception {
        doThrow(RuntimeException.class).when(iBrasilIoClient).getCovidData(anyString(), anyString());

        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/covid")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("uf", "RS")
                        .param("municipio", "Alegrete"))
                .andExpect(status().isNotFound());
    }
}
