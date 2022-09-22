package br.com.covid.brazil.api.controller;

import br.com.covid.brazil.api.client.IBrasilIoService;
import br.com.covid.brazil.api.dto.CovidDataDTO;
import br.com.covid.brazil.api.providers.ObterDadosCovidBrasilIoBadRequestProvider;
import br.com.covid.brazil.api.providers.ObterDadosCovidBrasilIoSucessoProvider;
import br.com.covid.brazil.api.service.ICovidDataService;
import br.com.covid.brazil.api.util.UnitBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static br.com.covid.brazil.api.util.MatcherConstantes.assertBodyDefaultData;
import static br.com.covid.brazil.api.util.MatcherConstantes.assertBodyDefaultDataPorPosicao;
import static br.com.covid.brazil.api.util.UnitTestConstantes.*;
import static br.com.covid.brazil.api.util.UrlEnumTest.*;
import static java.lang.String.format;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CovidDataController.class)
class CovidDataControllerTest extends UnitBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICovidDataService iCovidDataService;

    @MockBean
    private IBrasilIoService iBrasilIoService;

    @MockBean
    private ModelMapper mapper;

    @ParameterizedTest
    @ArgumentsSource(ObterDadosCovidBrasilIoSucessoProvider.class)
    @DisplayName("Deve Retornar Dados Covid com Sucesso ao Consultar BrasilIo - Variaçãoes dos Parametro Estado/Municipio")
    void deveRetornarDadosCovidVariacaoParamMunicipioConsultarBrasilIo(String estado, String municipio) throws Exception {
        doReturn(retornoCovidDataDto).when(iBrasilIoService).obterDadosCovid(anyString(), anyString());
        doReturn(retornoCovidDataModel).when(iCovidDataService).salvarHistoricoConsulta(retornoCovidDataDto);
        doReturn(retornoCovidDataDto).when(mapper).map(retornoCovidDataModel, CovidDataDTO.class);

        mockMvc
                .perform(MockMvcRequestBuilders.get(OBTER_DADOS_COVID_BRASIL_IO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, estado)
                        .param(MUNICIPIO_PARAM_BODY, municipio))
                .andExpect(status().isOk())
                .andExpect(assertBodyDefaultData(retornoCovidDataDto));
    }

    @Test
    @DisplayName("Deve Retornar Not Found - Erro ao Obter Dados Covid / RuntimeException")
    void deveRetornarNotFoundErroObterDadosCovidBrasilIo() throws Exception {
        doThrow(RuntimeException.class).when(iBrasilIoService).obterDadosCovid(anyString(), anyString());

        mockMvc
                .perform(MockMvcRequestBuilders.get(OBTER_DADOS_COVID_BRASIL_IO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, RS_PARAM)
                        .param(MUNICIPIO_PARAM_BODY, ALEGRE_PARAM))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest(name = "[{index}] {3}")
    @ArgumentsSource(ObterDadosCovidBrasilIoBadRequestProvider.class)
    @DisplayName("Deve Retornar Bad Request ao Consultar BrasilIo - Parametros Inválidos")
    void deveRetornarBadRequestParametrosInvalidosConsultaBrasilIo(String estado, String municipio, String erro, String msg) throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get(OBTER_DADOS_COVID_BRASIL_IO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, estado)
                        .param(MUNICIPIO_PARAM_BODY, municipio))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(ERROR_BODY, containsString(CONSTRAINT_VIOLATION_EXCEPTION_MSG)))
                .andExpect(jsonPath(ERROR_BODY, containsString(erro)));
    }


    @Test
    @DisplayName("Deve Retornar Bad Request ao Consultar BrasilIo - Estado Não Enviado")
    void deveRetornarBadRequestEstadoNaoEnviadoConsultaBrasilIo() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get(OBTER_DADOS_COVID_BRASIL_IO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(MUNICIPIO_PARAM_BODY, ALEGRE_PARAM))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(equalTo(UF_REQUIRED_MSG)));
    }

    @Test
    @DisplayName("Deve Retornar Bad Request ao Consultar BrasilIo - Municipio Não Enviado")
    void deveRetornarBadRequestMunicipioNaoEnviadoConsultaBrasilIo() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get(OBTER_DADOS_COVID_BRASIL_IO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, RS_PARAM))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(equalTo(MUNICIPIO_REQUIRED_MSG)));
    }

    @ParameterizedTest
    @ArgumentsSource(ObterDadosCovidBrasilIoSucessoProvider.class)
    @DisplayName("Deve Persistir Dados Covid com Sucesso - Variaçãoes dos Parametro Estado/Municipio")
    void devePersistirDadosCovidVariacaoParamMunicipio(String estado, String municipio) throws Exception {
        doReturn(retornoCovidDataDto).when(iBrasilIoService).obterDadosCovid(anyString(), anyString());
        doReturn(retornoCovidDataModel).when(iCovidDataService).salvarHistoricoConsulta(retornoCovidDataDto);
        doReturn(retornoCovidDataDto).when(mapper).map(retornoCovidDataModel, CovidDataDTO.class);

        mockMvc
                .perform(MockMvcRequestBuilders.post(PERSISTIR_DADOS_COVID_BRASIL_IO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, estado)
                        .param(MUNICIPIO_PARAM_BODY, municipio))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(ID_PARAM_BODY, is(1)))
                .andExpect(assertBodyDefaultData(retornoCovidDataDto));
    }

    @Test
    @DisplayName("Deve Retornar Not Found ao Persistir - Erro ao Persistir Dados Covid / RuntimeException")
    void deveRetornarNotFoundErroPersistirDadosCovid() throws Exception {
        doReturn(retornoCovidDataDto).when(iBrasilIoService).obterDadosCovid(anyString(), anyString());
        doThrow(RuntimeException.class).when(iCovidDataService).salvarHistoricoConsulta(retornoCovidDataDto);

        mockMvc
                .perform(MockMvcRequestBuilders.post(PERSISTIR_DADOS_COVID_BRASIL_IO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, RS_PARAM)
                        .param(MUNICIPIO_PARAM_BODY, ALEGRE_PARAM))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest(name = "[{index}] {3}")
    @ArgumentsSource(ObterDadosCovidBrasilIoBadRequestProvider.class)
    @DisplayName("Deve Retornar Bad Request ao Persistir - Parametros Inválidos")
    void deveRetornarBadRequestParametrosInvalidosAoPersistir(String estado, String municipio, String erro, String msg) throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get(OBTER_DADOS_COVID_BRASIL_IO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, estado)
                        .param(MUNICIPIO_PARAM_BODY, municipio))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(ERROR_BODY, containsString(CONSTRAINT_VIOLATION_EXCEPTION_MSG)))
                .andExpect(jsonPath(ERROR_BODY, containsString(erro)));
    }


    @Test
    @DisplayName("Deve Retornar Bad Request ao Persistir - Estado Não Enviado")
    void deveRetornarBadRequestEstadoNaoEnviadoAoPersistir() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.post(PERSISTIR_DADOS_COVID_BRASIL_IO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(MUNICIPIO_PARAM_BODY, ALEGRE_PARAM))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(equalTo(UF_REQUIRED_MSG)));
    }

    @Test
    @DisplayName("Deve Retornar Bad Request ao Persistir - Municipio Não Enviado")
    void deveRetornarBadRequestMunicipioNaoEnviadoAoPersistir() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.post(PERSISTIR_DADOS_COVID_BRASIL_IO.getUrl())
                        .accept(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, RS_PARAM))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(equalTo(MUNICIPIO_REQUIRED_MSG)));
    }

    @Test
    @DisplayName("Deve Listar Apenas Uma Consulta Existente")
    void deveListarApenasUmaConsultaExistente() throws Exception {
        doReturn(List.of(retornoCovidDataModel)).when(iCovidDataService).getAllCovidData();
        doReturn(List.of(retornoCovidDataDto)).when(mapper)
                .map(List.of(retornoCovidDataModel),
                        new TypeToken<List<CovidDataDTO>>() {
                        }.getType());

        mockMvc
                .perform(MockMvcRequestBuilders.get(LISTAR_TODAS_CONSULTAS.getUrl())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath(format("$[%d].%s", 0, ID_PARAM_BODY), is(1)))
                .andExpect(assertBodyDefaultDataPorPosicao(0, retornoCovidDataDto));
    }

    @Test
    @DisplayName("Deve Listar Duas Consultas Existente")
    void deveListarApenasDuasConsultaExistente() throws Exception {
        doReturn(List.of(retornoCovidDataModel, retornoCovidDataModel)).when(iCovidDataService).getAllCovidData();
        doReturn(List.of(retornoCovidDataDto, retornoCovidDataDto)).when(mapper)
                .map(List.of(retornoCovidDataModel, retornoCovidDataModel),
                        new TypeToken<List<CovidDataDTO>>() {
                        }.getType());

        mockMvc
                .perform(MockMvcRequestBuilders.get(LISTAR_TODAS_CONSULTAS.getUrl())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath(format("$[%d].%s", 0, ID_PARAM_BODY), is(1)))
                .andExpect(assertBodyDefaultDataPorPosicao(0, retornoCovidDataDto))
                .andExpect(jsonPath(format("$[%d].%s", 1, ID_PARAM_BODY), is(1)))
                .andExpect(assertBodyDefaultDataPorPosicao(1, retornoCovidDataDto));
    }

    @Test
    @DisplayName("Deve Retornar Lista Vazia")
    void deveRetornarListaVazia() throws Exception {
        doReturn(List.of(retornoCovidDataModel, retornoCovidDataModel)).when(iCovidDataService).getAllCovidData();
        doReturn(List.of()).when(mapper)
                .map(List.of(retornoCovidDataModel, retornoCovidDataModel),
                        new TypeToken<List<CovidDataDTO>>() {
                        }.getType());

        mockMvc
                .perform(MockMvcRequestBuilders.get(LISTAR_TODAS_CONSULTAS.getUrl())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    @DisplayName("Deve Retornar Not Found ao Listar Todas Consultas - Erro ao Listar Todos Dados Covid / RuntimeException")
    void deveRetornarNotFoundErroListarTodasConsultas() throws Exception {
        doReturn(List.of(retornoCovidDataModel, retornoCovidDataModel)).when(iCovidDataService).getAllCovidData();
        doThrow(RuntimeException.class).when(mapper)
                .map(List.of(retornoCovidDataModel, retornoCovidDataModel),
                        new TypeToken<List<CovidDataDTO>>() {
                        }.getType());

        mockMvc
                .perform(MockMvcRequestBuilders.get(LISTAR_TODAS_CONSULTAS.getUrl())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve Retornar Listagem Por ID")
    void deveRetornarListagemPorId() throws Exception {
        doReturn(retornoCovidDataModel).when(iCovidDataService).getCovidDataById(anyInt());
        doReturn(retornoCovidDataDto).when(mapper).map(retornoCovidDataModel, CovidDataDTO.class);

        mockMvc
                .perform(MockMvcRequestBuilders.get(LISTAR_CONSULTA_BY_ID.getUrl().replace(KEY_ID, "1"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(ID_PARAM_BODY, is(1)))
                .andExpect(assertBodyDefaultData(retornoCovidDataDto));
    }

    @Test
    @DisplayName("Deve Retornar Not Found - Erro ao Listar Dados Covid / RuntimeException")
    void deveRetornarNotFoundErroAoListar() throws Exception {
        doThrow(RuntimeException.class).when(iCovidDataService).getCovidDataById(anyInt());

        mockMvc
                .perform(MockMvcRequestBuilders.get(LISTAR_CONSULTA_BY_ID.getUrl().replace(KEY_ID, "1"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve Deletar Por ID")
    void deveDeletarPorId() throws Exception {
        doNothing().when(iCovidDataService).delete(anyInt());

        mockMvc
                .perform(MockMvcRequestBuilders.delete(DELETE_CONSULTA_BY_ID.getUrl().replace(KEY_ID, "1"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve Retornar Not Found - Erro ao Deletar Dados Covid / NullPointerException")
    void deveRetornarNotFoundErroAoDeletar() throws Exception {
        doThrow(new NullPointerException("Erroou")).when(iCovidDataService).delete(anyInt());

        mockMvc
                .perform(MockMvcRequestBuilders.delete(DELETE_CONSULTA_BY_ID.getUrl().replace(KEY_ID, "1"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
