package br.com.covid.brazil.api.util;

import org.springframework.test.context.ActiveProfiles;
import wiremock.com.fasterxml.jackson.annotation.JsonInclude;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@ActiveProfiles("test")
public class UnitBaseTest {

    public String toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper.writeValueAsString(object);
    }
}
