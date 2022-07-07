package br.com.covid.brazil.api.client;

import br.com.covid.brazil.api.dto.CovidDataDTO;
import br.com.covid.brazil.api.util.UnitBaseTest;
import feign.FeignException;
import javassist.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BrasilIoClientTest extends UnitBaseTest {

    @Mock
    private IBrasilIoClient iBrasilIoClient;
    @Mock
    private FeignException feignException;

    @Test
    @DisplayName("Deve Retornar Sucesso Feign Client")
    void deveRetornarBrasiolIoComSucesso() throws NotFoundException {
        doReturn(retornoSucesso).when(iBrasilIoClient).getCovidData(anyString(), anyString());

        CovidDataDTO retorno = iBrasilIoClient.getCovidData(anyString(), anyString());
        assertThat(retorno).isEqualTo(retornoSucesso);

        verify(iBrasilIoClient).getCovidData(anyString(), anyString());
    }

    @Test
    @DisplayName("Deve Retornar Feign Exceptio - Erro no Feign Client")
    void deveRetornarFeignException() throws NotFoundException {
        String mensagem = "errou feign";
        Optional<ByteBuffer> responseBody = Optional.empty();
        String contentUtf8 = "json erro";

        doReturn(mensagem).when(feignException).getMessage();
        doReturn(responseBody).when(feignException).responseBody();
        doReturn(contentUtf8).when(feignException).contentUTF8();
        doThrow(feignException).when(iBrasilIoClient).getCovidData(anyString(), any());

        FeignException exception = assertThrows(FeignException.class, () -> iBrasilIoClient.getCovidData(anyString(), anyString()));
        assertThat(exception.getMessage()).isEqualTo(mensagem);
        assertThat(exception.responseBody()).isEqualTo(responseBody);
        assertThat(exception.contentUTF8()).isEqualTo(contentUtf8);

        verify(feignException).getMessage();
        verify(feignException).responseBody();
        verify(feignException).contentUTF8();
        verify(iBrasilIoClient).getCovidData(anyString(), anyString());
    }
}
