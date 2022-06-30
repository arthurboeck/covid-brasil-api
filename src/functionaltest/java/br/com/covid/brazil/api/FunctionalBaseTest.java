package br.com.covid.brazil.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("it")
@AutoConfigureWireMock(port = 0, stubs = "classpath*:/mappings/**/*.json")
@SpringBootTest(classes = Application.class)
public class FunctionalBaseTest {

    @Autowired
    private MockMvc mvc;

    protected MockMvc getMvc() {
        return mvc;
    }
}

