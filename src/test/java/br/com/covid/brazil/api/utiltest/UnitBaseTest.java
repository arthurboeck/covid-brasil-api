package br.com.covid.brazil.api.utiltest;

import br.com.covid.brazil.api.Application;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ImportAutoConfiguration(FeignAutoConfiguration.class)
@AutoConfigureWireMock(port = 0, stubs = "classpath*:/mappings/**/*.json")
@SpringBootTest(classes = Application.class)
public class UnitBaseTest {

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
