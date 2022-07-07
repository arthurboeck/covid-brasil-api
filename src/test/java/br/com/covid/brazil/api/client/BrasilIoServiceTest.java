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

import java.nio.ByteBuffer;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BrasilIoServiceTest extends UnitBaseTest {

    @Mock
    private IBrasilIoService iBrasilIoClient;
    @Mock
    private BrasilIoService brasilIoClient;
    @Mock
    private FeignException feignException;

    @Test
    @DisplayName("Deve Retornar Sucesso Feign Client")
    void deveRetornarBrasiolIoComSucesso() throws NotFoundException {
        doReturn(retornoSucesso).when(iBrasilIoClient).obterDadosCovid(anyString(), anyString());

        CovidDataDTO retorno = iBrasilIoClient.obterDadosCovid(anyString(), anyString());
        assertThat(retorno).isEqualTo(retornoSucesso);

        verify(iBrasilIoClient).obterDadosCovid(anyString(), anyString());
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
        doThrow(feignException).when(iBrasilIoClient).obterDadosCovid(anyString(), any());

        FeignException exception = assertThrows(FeignException.class, () -> iBrasilIoClient.obterDadosCovid(anyString(), anyString()));
        assertThat(exception.getMessage()).isEqualTo(mensagem);
        assertThat(exception.responseBody()).isEqualTo(responseBody);
        assertThat(exception.contentUTF8()).isEqualTo(contentUtf8);

        verify(feignException).getMessage();
        verify(feignException).responseBody();
        verify(feignException).contentUTF8();
        verify(iBrasilIoClient).obterDadosCovid(anyString(), anyString());
    }

//    @Test
//    @DisplayName("Deve Retornar Not Found - Empty Result")
//    void deveRetornarNotFoundExceptionReturnEmpty() throws NotFoundException {
//        doReturn(null).when(brasilIoClient).obterDadosCovid(anyString(), anyString());
//
//        NotFoundException exception = assertThrows(NotFoundException.class, () -> iBrasilIoClient.obterDadosCovid(anyString(), anyString()));
//        assertThat(exception.getMessage()).contains("Registro não encontrado:");
//
//    }
}
