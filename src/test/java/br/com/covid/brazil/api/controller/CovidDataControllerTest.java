package br.com.covid.brazil.api.controller;

import br.com.covid.brazil.api.client.IBrasilIoClient;
import br.com.covid.brazil.api.dto.CovidDataDTO;
import br.com.covid.brazil.api.util.UnitBaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CovidDataController.class)
class CovidDataControllerTest extends UnitBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBrasilIoClient iBrasilIoClient;

    @Test
    void deveRetornarDadosCovidDoMunicipio() throws Exception {
        CovidDataDTO retornoSucesso =
                new CovidDataDTO("RS", "Alegrete", 123L, 123L, LocalDate.now(), "202212", 1222L, 12000L, 100L, BigDecimal.ONE, LocalDate.now(), BigDecimal.TEN, 1L, 2L, 3L);

        doReturn(retornoSucesso).when(iBrasilIoClient).getCovidData(anyString(), anyString());

        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/covid")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("uf", "RS")
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
}
