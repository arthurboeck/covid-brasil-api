package br.com.covid.brazil.api.controller;

import br.com.covid.brazil.api.utiltest.UnitBaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MessageController.class)
class MessageControllerTest extends UnitBaseTest {

    @Test
    void checkSayHelloIsUp() throws Exception {
        getMvc()
                .perform(MockMvcRequestBuilders.get("/api/v1/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Hello Dear World!"));
    }
}
