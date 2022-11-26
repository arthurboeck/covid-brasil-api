package br.com.covid.brazil.api.funcional;

import br.com.covid.brazil.api.BaseTestFuncional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static br.com.covid.brazil.api.util.RotasEnum.ACTUATOR;
import static br.com.covid.brazil.api.util.TesteConstantes.TAG_STATUS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class DisponibilidadeFuncionalTest extends BaseTestFuncional {

    @Test
    @Tag("funcional")
    @DisplayName("Deve Validar Disponibildiade - HealthCheck")
    void deveValidarDisponibilidade() throws Exception {
        getMvc().perform(get(ACTUATOR.getUrl()))
                .andExpect(jsonPath(TAG_STATUS, Matchers.equalTo("UP")));
    }
}
