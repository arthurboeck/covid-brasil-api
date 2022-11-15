package br.com.covid.brazil.api.funcional;

import br.com.covid.brazil.api.BaseTestFuncional;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;

import static br.com.covid.brazil.api.util.MatchersConstantes.assertBodyDefaultData;
import static br.com.covid.brazil.api.util.MatchersConstantes.assertBodyDefaultDataPorPosicao;
import static br.com.covid.brazil.api.util.RotasEnum.*;
import static br.com.covid.brazil.api.util.UtilFuncionalConstantes.*;
import static java.lang.String.format;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ObterDadosCovidFuncionalTest extends BaseTestFuncional {

    @Test
    @Order(1)
    @Tag("functional")
    @DisplayName("Deve Realizar Consulta Externa na Brasil.IO")
    void deveRetornarDadosDoMunicipio() throws Exception {
        getMvc().perform(get(OBTER_DADOS_COVID_BRASIL_IO.getUrl())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, RS_PARAM)
                        .param(MUNICIPIO_PARAM_BODY, ALEGRE_PARAM))
                .andExpect(status().isOk())
                .andExpect(jsonPath(ID_PARAM_BODY, is(1)))
                .andExpect(assertBodyDefaultData(retornoSucesso));
    }

    @Test
    @Order(2)
    @Tag("functional")
    @DisplayName("Deve Listar Consultas Persistidas no Banco")
    void deveListarTodos() throws Exception {
        getMvc().perform(get(LISTAR_TODAS_CONSULTAS.getUrl())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, RS_PARAM)
                        .param(MUNICIPIO_PARAM_BODY, ALEGRE_PARAM))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath(format("$[%d].%s", 0, ID_PARAM_BODY), is(1)))
                .andExpect(assertBodyDefaultDataPorPosicao(0, retornoSucesso));
    }

    @Test
    @Order(3)
    @Tag("functional")
    @DisplayName("Deve Listar Por ID")
    void deveListarPorId() throws Exception {
        getMvc().perform(get(LISTAR_CONSULTA_BY_ID.getUrl().replace(KEY_ID, "1"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, RS_PARAM)
                        .param(MUNICIPIO_PARAM_BODY, ALEGRE_PARAM))
                .andExpect(status().isOk())
                .andExpect(jsonPath(format(ID_PARAM_BODY), is(1)))
                .andExpect(assertBodyDefaultData(retornoSucesso));
    }

    @Test
    @Tag("functional")
    @DisplayName("Deve Retornar NotFound Listar Por ID")
    void deveRetornarNotFoundListarPorId() throws Exception {
        getMvc().perform(get(LISTAR_CONSULTA_BY_ID.getUrl().replace(KEY_ID, "8"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, RS_PARAM)
                        .param(MUNICIPIO_PARAM_BODY, ALEGRE_PARAM))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(4)
    @Tag("functional")
    @DisplayName("Deve Deletar Por ID")
    void deveDeletarPorId() throws Exception {
        getMvc().perform(delete(DELETE_CONSULTA_BY_ID.getUrl().replace(KEY_ID, "1"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(5)
    @Tag("functional")
    @DisplayName("Deve Persistir Consulta ao Brasil.IO")
    void devePersistirDadosDoMunicipio() throws Exception {
        getMvc().perform(post(PERSISTIR_DADOS_COVID_BRASIL_IO.getUrl())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, RS_PARAM)
                        .param(MUNICIPIO_PARAM_BODY, ALEGRE_PARAM))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(ID_PARAM_BODY, is(2)))
                .andExpect(assertBodyDefaultData(retornoSucesso));
    }
}
