package br.com.covid.brazil.api.controller;

import br.com.covid.brazil.api.client.IBrasilIoService;
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

import static br.com.covid.brazil.api.util.UnitTestConstantes.*;
import static br.com.covid.brazil.api.util.UrlEnumTest.OBTER_DADOS_COVID_ESTADO_MUNICIO;
import static org.hamcrest.Matchers.*;
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
    private IBrasilIoService iBrasilIoService;

    @ParameterizedTest
    @ValueSource(strings = {"alegrete", "Alegrete", "ALEGRETE", "aLEGRETE"})
    @DisplayName("Deve Retornar Dados Covid com Sucesso - Variaçãoes do Parametro Municipio")
    void deveRetornarDadosCovidVariacaoParamMunicipio(String municipio) throws Exception {
        doReturn(retornoSucesso).when(iBrasilIoService).obterDadosCovid(anyString(), anyString());

        mockMvc
                .perform(MockMvcRequestBuilders.get(OBTER_DADOS_COVID_ESTADO_MUNICIO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, "RS")
                        .param(MUNICIPIO_PARAM_BODY, municipio))
                .andExpect(status().isOk())
                .andExpect(jsonPath(UF_PARAM_BODY, is(retornoSucesso.getUf())))
                .andExpect(jsonPath(MUNICIPIO_PARAM_BODY, is(retornoSucesso.getMunicipio())))
                .andExpect(jsonPath(CODIGO_IBGE_MUNICIPIO_BODY, is(retornoSucesso.getCodigoIbgeMunicipio().intValue())))
                .andExpect(jsonPath(RANKING_MUNICIPIO_BODY, is(retornoSucesso.getRankingMunicipio().intValue())))
                .andExpect(jsonPath(DATA_COLETA_DADOS_BODY, is(retornoSucesso.getDataColetaDados().format(DateTimeFormatter.ISO_LOCAL_DATE))))
                .andExpect(jsonPath(SEMANA_EPIDEMIOLOGICA_BODY, is(retornoSucesso.getSemanaEpidemiologica())))
                .andExpect(jsonPath(POPULACAO_ESTIMADA_BODY, is(retornoSucesso.getPopulacaoEstimada().intValue())))
                .andExpect(jsonPath(POPULACAO_ESTIMADA_2019_BODY, is(retornoSucesso.getPopulacaoEstimada2019().intValue())))
                .andExpect(jsonPath(CASOS_CONFIRMADOS_ULTIMO_DIA_BODY, is(retornoSucesso.getCasosConfirmadosUltimoDia().intValue())))
                .andExpect(jsonPath(CASOS_CONFIRMADOS_ULTIMO_DIA_POR_100_K_HABITANTES_BODY, is(retornoSucesso.getCasosConfirmadosUltimoDiaPor100kHabitantes().intValue())))
                .andExpect(jsonPath(DATA_DADO_BODY, is(retornoSucesso.getDataDado().format(DateTimeFormatter.ISO_LOCAL_DATE))))
                .andExpect(jsonPath(TAXA_MORTALIDADE_ULTIMO_DIA_BODY, is(retornoSucesso.getTaxaMortalidadeUltimoDia().intValue())))
                .andExpect(jsonPath(MORTES_ULTIMO_DIA_BODY, is(retornoSucesso.getMortesUltimoDia().intValue())))
                .andExpect(jsonPath(NOVOS_CASOS_BODY, is(retornoSucesso.getNovosCasos().intValue())))
                .andExpect(jsonPath(NOVAS_MORTES_BODY, is(retornoSucesso.getNovasMortes().intValue())));
    }

    @ParameterizedTest
    @ValueSource(strings = {"rs", "RS", "rS", "Rs"})
    @DisplayName("Deve Retornar Dados Covid com Sucesso - Variaçãoes do Parametro Estado")
    void deveRetornarDadosCovidVariacaoParamEstado(String estado) throws Exception {
        doReturn(retornoSucesso).when(iBrasilIoService).obterDadosCovid(anyString(), anyString());

        mockMvc
                .perform(MockMvcRequestBuilders.get(OBTER_DADOS_COVID_ESTADO_MUNICIO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, estado)
                        .param(MUNICIPIO_PARAM_BODY, "Alegrete"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(UF_PARAM_BODY, is(retornoSucesso.getUf())))
                .andExpect(jsonPath(MUNICIPIO_PARAM_BODY, is(retornoSucesso.getMunicipio())))
                .andExpect(jsonPath(CODIGO_IBGE_MUNICIPIO_BODY, is(retornoSucesso.getCodigoIbgeMunicipio().intValue())))
                .andExpect(jsonPath(RANKING_MUNICIPIO_BODY, is(retornoSucesso.getRankingMunicipio().intValue())))
                .andExpect(jsonPath(DATA_COLETA_DADOS_BODY, is(retornoSucesso.getDataColetaDados().format(DateTimeFormatter.ISO_LOCAL_DATE))))
                .andExpect(jsonPath(SEMANA_EPIDEMIOLOGICA_BODY, is(retornoSucesso.getSemanaEpidemiologica())))
                .andExpect(jsonPath(POPULACAO_ESTIMADA_BODY, is(retornoSucesso.getPopulacaoEstimada().intValue())))
                .andExpect(jsonPath(POPULACAO_ESTIMADA_2019_BODY, is(retornoSucesso.getPopulacaoEstimada2019().intValue())))
                .andExpect(jsonPath(CASOS_CONFIRMADOS_ULTIMO_DIA_BODY, is(retornoSucesso.getCasosConfirmadosUltimoDia().intValue())))
                .andExpect(jsonPath(CASOS_CONFIRMADOS_ULTIMO_DIA_POR_100_K_HABITANTES_BODY, is(retornoSucesso.getCasosConfirmadosUltimoDiaPor100kHabitantes().intValue())))
                .andExpect(jsonPath(DATA_DADO_BODY, is(retornoSucesso.getDataDado().format(DateTimeFormatter.ISO_LOCAL_DATE))))
                .andExpect(jsonPath(TAXA_MORTALIDADE_ULTIMO_DIA_BODY, is(retornoSucesso.getTaxaMortalidadeUltimoDia().intValue())))
                .andExpect(jsonPath(MORTES_ULTIMO_DIA_BODY, is(retornoSucesso.getMortesUltimoDia().intValue())))
                .andExpect(jsonPath(NOVOS_CASOS_BODY, is(retornoSucesso.getNovosCasos().intValue())))
                .andExpect(jsonPath(NOVAS_MORTES_BODY, is(retornoSucesso.getNovasMortes().intValue())));
    }

    @Test
    @DisplayName("Deve Retornar Not Found - Erro ao Obter Dados Covid")
    void deveRetornarNotFoundErroObterDadosCovid() throws Exception {
        doThrow(RuntimeException.class).when(iBrasilIoService).obterDadosCovid(anyString(), anyString());

        mockMvc
                .perform(MockMvcRequestBuilders.get(OBTER_DADOS_COVID_ESTADO_MUNICIO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, "RS")
                        .param(MUNICIPIO_PARAM_BODY, "Alegrete"))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("Deve Retornar Bad Request - Estado Inválido")
    void deveRetornarBadRequestEstadoInvalido(String estado) throws Exception {
        doReturn(retornoSucesso).when(iBrasilIoService).obterDadosCovid(anyString(), anyString());

        mockMvc
                .perform(MockMvcRequestBuilders.get(OBTER_DADOS_COVID_ESTADO_MUNICIO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, estado)
                        .param(MUNICIPIO_PARAM_BODY, "Alegrete"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(ERROR_BODY, containsString(CONSTRAINT_VIOLATION_EXCEPTION_MSG)))
                .andExpect(jsonPath(ERROR_BODY, containsString(UF_NULL_MSG)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("Deve Retornar Bad Request - Municipio Inválido")
    void deveRetornarBadRequestMunicipioInvalido(String municipio) throws Exception {
        doReturn(retornoSucesso).when(iBrasilIoService).obterDadosCovid(anyString(), anyString());

        mockMvc
                .perform(MockMvcRequestBuilders.get(OBTER_DADOS_COVID_ESTADO_MUNICIO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, "RS")
                        .param(MUNICIPIO_PARAM_BODY, municipio))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(ERROR_BODY, containsString(CONSTRAINT_VIOLATION_EXCEPTION_MSG)))
                .andExpect(jsonPath(ERROR_BODY, containsString(MUNICIPIO_NULL_MSG)));
    }

    @Test
    @DisplayName("Deve Retornar Bad Request - Estado Não Enviado")
    void deveRetornarBadRequestEstadoNaoEnviado() throws Exception {
        doReturn(retornoSucesso).when(iBrasilIoService).obterDadosCovid(anyString(), anyString());

        mockMvc
                .perform(MockMvcRequestBuilders.get(OBTER_DADOS_COVID_ESTADO_MUNICIO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(MUNICIPIO_PARAM_BODY, "Alegrete"))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(equalTo(UF_REQUIRED_MSG)));
    }

    @Test
    @DisplayName("Deve Retornar Bad Request - Municipio Não Enviado")
    void deveRetornarBadRequestMunicipioNaoEnviado() throws Exception {
        doReturn(retornoSucesso).when(iBrasilIoService).obterDadosCovid(anyString(), anyString());

        mockMvc
                .perform(MockMvcRequestBuilders.get(OBTER_DADOS_COVID_ESTADO_MUNICIO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, "RS"))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(equalTo(MUNICIPIO_REQUIRED_MSG)));
    }
}
