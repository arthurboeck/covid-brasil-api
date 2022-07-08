package br.com.covid.brazil.api.util;

import br.com.covid.brazil.api.dto.CovidDataDTO;
import br.com.covid.brazil.api.model.CovidData;
import org.springframework.test.context.ActiveProfiles;
import wiremock.com.fasterxml.jackson.annotation.JsonInclude;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@ActiveProfiles("test")
public class UnitBaseTest {

    public CovidDataDTO retornoSucesso =
            new CovidDataDTO(1, "RS", "Alegrete", 123L, 123L, LocalDate.now(), "202212", 1222L, 12000L, 100L, BigDecimal.ONE, LocalDate.now(), BigDecimal.TEN, 1L, 2L, 3L);

    public CovidData covidDataRetorno =
            new CovidData(1, "RS", "Alegrete", 123L, 123L, LocalDate.now(), "202212", 1222L, 12000L, 100L, BigDecimal.ONE, LocalDate.now(), BigDecimal.TEN, 1L, 2L, 3L);

    public String toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper.writeValueAsString(object);
    }
}
