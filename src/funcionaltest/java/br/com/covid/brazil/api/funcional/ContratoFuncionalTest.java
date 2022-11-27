package br.com.covid.brazil.api.funcional;

import br.com.covid.brazil.api.BaseTestFuncional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static br.com.covid.brazil.api.util.RotasEnum.OBTER_DADOS_COVID_BRASIL_IO;
import static br.com.covid.brazil.api.util.TesteConstantes.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ContratoFuncionalTest extends BaseTestFuncional {

    @Test
    @Tag("funcional")
    @DisplayName("Deve Validar Contrato - Obter Dados Covid Brasil.IO")
    void deveRetornarSucessoAoValidarContratoObterDadosBrasilIo() throws Exception {
        String response =
                getMvc().perform(get(OBTER_DADOS_COVID_BRASIL_IO.getUrl())
                                .contentType(MediaType.APPLICATION_JSON)
                                .param(UF_PARAM_BODY, RS_PARAM)
                                .param(MUNICIPIO_PARAM_BODY, ALEGRE_PARAM))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        assertThat(response, matchesJsonSchemaInClasspath("schemas/obterDadosCovidBrasilIo_schema.json"));
    }
}
