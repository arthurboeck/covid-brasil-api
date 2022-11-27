package br.com.covid.brazil.api.funcional;

import br.com.covid.brazil.api.BaseTestFuncional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static br.com.covid.brazil.api.util.RotasEnum.DELETE_CONSULTA_BY_ID;
import static java.lang.String.format;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(value = "classpath:sql/InserirDadosFuncional.sql", executionPhase = BEFORE_TEST_METHOD)
class DeletarDadosCovidFuncionalTest extends BaseTestFuncional {

    @Test
    @Tag("funcional")
    @DisplayName("Deve Deletar Por ID - Registro já existente na base")
    void deveDeletarPorId() throws Exception {
        getMvc().perform(delete(format(DELETE_CONSULTA_BY_ID.getUrl(), 10))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
