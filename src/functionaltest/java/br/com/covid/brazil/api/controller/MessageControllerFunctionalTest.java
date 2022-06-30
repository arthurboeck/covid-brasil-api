package br.com.covid.brazil.api.controller;

import br.com.covid.brazil.api.FunctionalBaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MessageControllerFunctionalTest extends FunctionalBaseTest {

    private static final String MESSAGE_PROP = "message";

    @Test
    @Tag("functional")
    void mustReturnHello() throws Exception {
        getMvc().perform(get("/api/v1/hello")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(MESSAGE_PROP, equalTo("Hello Dear World!")));
    }
}
