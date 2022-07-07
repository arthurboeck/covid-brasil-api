package br.com.covid.brazil.api.client;

import br.com.covid.brazil.api.dto.CovidDataDTO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.lang.String.format;
import static org.springframework.util.StringUtils.capitalize;

@Component
public class BrasilIoService implements IBrasilIoService {

    private final IBrasilIoClient iBrasilIoClient;
    private final String token;

    public BrasilIoService(
//            @Value("Token 49e7a820ece5e243b1c47c8b31087626b685e33e") String token,
            @Value("Token ${service.brasilIoApi.token}") String token,
            IBrasilIoClient iBrasilIoClient) {
        this.token = token;
        this.iBrasilIoClient = iBrasilIoClient;
    }

    @Override
    public CovidDataDTO obterDadosCovid(String uf, String municipio) throws NotFoundException {
        try {
            return iBrasilIoClient.getCovidaData(token, "True", uf.toUpperCase(), capitalize(municipio)).getResults().get(0);
        } catch (Exception e) {
            throw new NotFoundException(format("Registro não encontrado: %s", e.getMessage()));
        }
    }
}
