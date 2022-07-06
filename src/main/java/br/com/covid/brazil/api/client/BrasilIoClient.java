package br.com.covid.brazil.api.client;

import br.com.covid.brazil.api.dto.CovidDataDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class BrasilIoClient implements IBrasilIoClient {
    private final IBrasilIoClient iBrasilIoClient;
    private final String token;

    public BrasilIoClient(
            //@Value("Token ${service.brasilIoApi.token}") String token,
            @Value("Token 49e7a820ece5e243b1c47c8b31087626b685e33e") String token,
            IBrasilIoClient iBrasilIoClient) {
        this.token = token;
        this.iBrasilIoClient = iBrasilIoClient;
    }

    @Override
    public CovidDataDTO getCovidData(String uf, String cidade) {
        return iBrasilIoClient.getCovidaData(token, "True", uf, cidade);
    }

    //@FeignClient(url = "${service.brasilIoApi.url}", value = "brasilIoApi")
    @FeignClient(url = "https://api.brasil.io/v1/dataset/covid19/", value = "brasilIoApi")
    interface IBrasilIoClient {
        @GetMapping(path = "caso_full/data/")
        CovidDataDTO getCovidaData(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                   @RequestParam("is_last") String ehUltimaAtualizacao,
                                   @RequestParam("state") String uf,
                                   @RequestParam("city") String cidade);
    }
}
