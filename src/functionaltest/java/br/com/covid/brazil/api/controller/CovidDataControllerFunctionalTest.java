package br.com.covid.brazil.api.controller;

import br.com.covid.brazil.api.FunctionalBaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CovidDataControllerFunctionalTest extends FunctionalBaseTest {

    @Test
    @Tag("functional")
    void deveRetornarDadosDoMunicipio() throws Exception {
        getMvc().perform(get("/api/v1/covid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("uf", "rs")
                        .param("municipio", "Alegrete"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
