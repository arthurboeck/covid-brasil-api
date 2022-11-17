package br.com.covid.brazil.api;

import br.com.covid.brazil.api.dto.CovidDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

@AutoConfigureMockMvc
@ActiveProfiles("funcional")
@AutoConfigureWireMock(port = 0, stubs = "classpath*:/mappings/**/*.json")
@SpringBootTest(classes = {Application.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public class BaseTestFuncional {

    @Autowired
    private MockMvc mvc;

    protected MockMvc getMvc() {
        return mvc;
    }

    public CovidDataDTO retornoSucesso =
            new CovidDataDTO(null, "RS", "Alegrete", 4300406L, 723L,
                    LocalDate.of(2022, 3, 24), "202212", 73028L,
                    73589L, 15009L, BigDecimal.valueOf(20552.39086),
                    LocalDate.of(2022, 3, 24), BigDecimal.valueOf(0.0216), 324L,
                    36L, 0L);
}

