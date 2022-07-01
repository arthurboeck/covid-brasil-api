package br.com.covid.brazil.api.utiltest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@AutoConfigureWireMock(port = 0, stubs = "classpath*:/mappings/**/*.json")
public class UnitBaseTest {

    @Autowired
    private MockMvc mvc;

    protected MockMvc getMvc() {
        return mvc;
    }

    /**
     * Convert Object to Json String
     *
     * @return Json String
     */
    public static String toJson(Object objeto) throws JsonProcessingException {
        return new ObjectMapper()
                .writer().withDefaultPrettyPrinter()
                .writeValueAsString(objeto);
    }
}
