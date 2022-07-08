package br.com.covid.brazil.api.client;

import br.com.covid.brazil.api.dto.CovidDataDTO;
import br.com.covid.brazil.api.util.UnitBaseTest;
import feign.FeignException;
import javassist.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BrasilIoServiceTest extends UnitBaseTest {

    @Mock
    private IBrasilIoClient iBrasilIoClient;

    @Autowired
    private IBrasilIoService iBrasilIoService;

    @Mock
    private FeignException feignException;

    //    @Test
    @DisplayName("Deve Retornar Sucesso Feign Client")
    void deveRetornarBrasiolIoComSucesso() throws NotFoundException {
        BrasilIoDTO retornoFeignClient = new BrasilIoDTO(List.of(retornoSucesso));
        doReturn(retornoFeignClient).when(iBrasilIoClient).getCovidData(any(), any(), any(), any());

        CovidDataDTO retorno = iBrasilIoService.obterDadosCovid("A", "A");
        assertEquals(retorno.getUf(), retornoSucesso.getUf());
        assertEquals(retorno.getMunicipio(), retornoSucesso.getMunicipio());
        assertEquals(retorno.getCodigoIbgeMunicipio(), retornoSucesso.getCodigoIbgeMunicipio());
        assertEquals(retorno.getRankingMunicipio(), retornoSucesso.getRankingMunicipio());
        assertEquals(retorno.getDataColetaDados(), retornoSucesso.getDataColetaDados());
        assertEquals(retorno.getSemanaEpidemiologica(), retornoSucesso.getSemanaEpidemiologica());
        assertEquals(retorno.getPopulacaoEstimada(), retornoSucesso.getPopulacaoEstimada());
        assertEquals(retorno.getPopulacaoEstimada2019(), retornoSucesso.getPopulacaoEstimada2019());
        assertEquals(retorno.getCasosConfirmadosUltimoDia(), retornoSucesso.getCasosConfirmadosUltimoDia());
        assertEquals(retorno.getCasosConfirmadosUltimoDiaPor100kHabitantes(), retornoSucesso.getCasosConfirmadosUltimoDiaPor100kHabitantes());
        assertEquals(retorno.getDataDado(), retornoSucesso.getDataDado());
        assertEquals(retorno.getTaxaMortalidadeUltimoDia(), retornoSucesso.getTaxaMortalidadeUltimoDia());
        assertEquals(retorno.getMortesUltimoDia(), retornoSucesso.getMortesUltimoDia());
        assertEquals(retorno.getNovosCasos(), retornoSucesso.getNovosCasos());
        assertEquals(retorno.getNovasMortes(), retornoSucesso.getNovasMortes());

        verify(iBrasilIoService).obterDadosCovid(anyString(), anyString());
    }

    //    @Test
    @DisplayName("Deve Retornar Not Found - Lista Vazia")
    void deveRetornarNotFoundBrasiolIoListaVazia() {
        doReturn(new BrasilIoDTO(Collections.emptyList())).when(iBrasilIoClient).getCovidData(anyString(), anyString(), anyString(), anyString());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> iBrasilIoService.obterDadosCovid(anyString(), anyString()));
        assertThat(exception.getMessage()).contains();
    }

    //    @Test
    @DisplayName("Deve Retornar Feign Exceptio - Erro no Feign Client")
    void deveRetornarFeignException() {
        String mensagem = "errou feign";
        Optional<ByteBuffer> responseBody = Optional.empty();
        String contentUtf8 = "json erro";

        doReturn(mensagem).when(feignException).getMessage();
        doReturn(responseBody).when(feignException).responseBody();
        doReturn(contentUtf8).when(feignException).contentUTF8();
        doThrow(RuntimeException.class).when(iBrasilIoClient).getCovidData(anyString(), anyString(), anyString(), anyString());

        FeignException exception = assertThrows(FeignException.class, () -> iBrasilIoService.obterDadosCovid(anyString(), anyString()));
        assertThat(exception.getMessage()).isEqualTo(mensagem);
        assertThat(exception.responseBody()).isEqualTo(responseBody);
        assertThat(exception.contentUTF8()).isEqualTo(contentUtf8);

        verify(feignException).getMessage();
        verify(feignException).responseBody();
        verify(feignException).contentUTF8();
        verify(iBrasilIoClient).getCovidData(anyString(), anyString(), anyString(), anyString());
    }
}
