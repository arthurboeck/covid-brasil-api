package br.com.covid.brazil.api.funcional;

import br.com.covid.brazil.api.BaseTestFuncional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static br.com.covid.brazil.api.util.MatchersConstantes.assertBodyDefaultData;
import static br.com.covid.brazil.api.util.RotasEnum.PERSISTIR_DADOS_COVID_BRASIL_IO;
import static br.com.covid.brazil.api.util.TesteConstantes.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PersistirDadosCovidFuncionalTest extends BaseTestFuncional {

    @Test
    @Tag("functional")
    @DisplayName("Deve Persistir Consulta ao Brasil.IO")
    void devePersistirDadosDoMunicipio() throws Exception {
        getMvc().perform(post(PERSISTIR_DADOS_COVID_BRASIL_IO.getUrl())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(UF_PARAM_BODY, RS_PARAM)
                        .param(MUNICIPIO_PARAM_BODY, ALEGRE_PARAM))
                .andExpect(status().isCreated())
                .andExpect(assertBodyDefaultData(retornoSucesso));
    }
}
