package br.com.covid.brazil.api.funcional;

import br.com.covid.brazil.api.BaseTestFuncional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static br.com.covid.brazil.api.util.MatchersConstantes.assertBodyDefaultData;
import static br.com.covid.brazil.api.util.MatchersConstantes.assertBodyDefaultDataPorPosicao;
import static br.com.covid.brazil.api.util.RotasEnum.*;
import static br.com.covid.brazil.api.util.TesteConstantes.*;
import static java.lang.String.format;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(value = "classpath:sql/InserirDadosFuncional.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/DeletarDadosFuncional.sql", executionPhase = AFTER_TEST_METHOD)
class ObterDadosCovidFuncionalTest extends BaseTestFuncional {

    @Test
    @Tag("funcional")
    @DisplayName("Deve Realizar Consulta Externa na Brasil.IO")
    void deveRetornarDadosDoMunicipio() throws Exception {
        getMvc().perform(get(OBTER_DADOS_COVID_BRASIL_IO.getUrl())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, RS_PARAM)
                        .param(MUNICIPIO_PARAM_BODY, ALEGRETE_PARAM))
                .andExpect(status().isOk())
                .andExpect(assertBodyDefaultData(retornoSucesso));
    }

    @Test
    @Tag("funcional")
    @DisplayName("Deve Retornar Not Found - Server Error Brasil.IO")
    void deveRetornarNotFoundErroObterDadosCovidBrasilIo() throws Exception {
        getMvc().perform(get(OBTER_DADOS_COVID_BRASIL_IO.getUrl())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, "MG")
                        .param(MUNICIPIO_PARAM_BODY, ALEGRETE_PARAM))
                .andExpect(status().isNotFound());
    }

    @Test
    @Tag("funcional")
    @DisplayName("Deve Listar Consultas Persistidas no Banco")
    void deveListarTodos() throws Exception {
        getMvc().perform(get(LISTAR_TODAS_CONSULTAS.getUrl())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath(format("$[%d].%s", 0, ID_PARAM_BODY), is(10)))
                .andExpect(assertBodyDefaultDataPorPosicao(0, retornoSucesso));
    }

    @Test
    @Tag("funcional")
    @DisplayName("Deve Listar Por ID")
    void deveListarPorId() throws Exception {
        getMvc().perform(get(format(LISTAR_CONSULTA_BY_ID.getUrl(), 10))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(format(ID_PARAM_BODY), is(10)))
                .andExpect(assertBodyDefaultData(retornoSucesso));
    }

    @Test
    @Tag("funcional")
    @DisplayName("Deve Retornar NotFound Listar Por ID")
    void deveRetornarNotFoundListarPorId() throws Exception {
        getMvc().perform(get(format(LISTAR_CONSULTA_BY_ID.getUrl(), 8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
