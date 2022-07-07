package br.com.covid.brazil.api.client;

import br.com.covid.brazil.api.dto.CovidDataDTO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static java.lang.String.format;
import static org.springframework.util.StringUtils.capitalize;

@Component
public class BrasilIoClient implements IBrasilIoClient {
    private final IBrasilIoClient iBrasilIoClient;
    private final String token;

    public BrasilIoClient(
//            @Value("Token 49e7a820ece5e243b1c47c8b31087626b685e33e") String token,
            @Value("Token ${service.brasilIoApi.token}") String token,
            IBrasilIoClient iBrasilIoClient) {
        this.token = token;
        this.iBrasilIoClient = iBrasilIoClient;
    }

    @Override
    public CovidDataDTO getCovidData(String uf, String municipio) throws NotFoundException {
        BrasilIoDTO results = iBrasilIoClient.getCovidaData(token, "True", uf.toUpperCase(), capitalize(municipio));
        try {
            return results.getResults().get(0);
        } catch (Exception e) {
            throw new NotFoundException(format("Registro não encontrado: %s", e.getMessage()));
        }
    }

    //    @FeignClient(url = "https://api.brasil.io/v1/dataset/covid19/", value = "brasilIoApi")
    @FeignClient(url = "${service.brasilIoApi.url}", value = "brasilIoApi")
    interface IBrasilIoClient {
        @GetMapping(path = "caso_full/data/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
        BrasilIoDTO getCovidaData(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                  @RequestParam("is_last") String ehUltimaAtualizacao,
                                  @RequestParam("state") String uf,
                                  @RequestParam("city") String municipio);
    }
}
