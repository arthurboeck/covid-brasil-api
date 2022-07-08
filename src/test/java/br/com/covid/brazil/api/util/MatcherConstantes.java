package br.com.covid.brazil.api.util;

import br.com.covid.brazil.api.dto.CovidDataDTO;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.format.DateTimeFormatter;

import static br.com.covid.brazil.api.util.UnitTestConstantes.*;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class MatcherConstantes {

    public static <T> ResultMatcher assertBodyDefaultData(CovidDataDTO retornoSucesso) {
        return matchAll(
                (jsonPath(UF_PARAM_BODY, is(retornoSucesso.getUf()))),
                jsonPath(MUNICIPIO_PARAM_BODY, is(retornoSucesso.getMunicipio())),
                jsonPath(CODIGO_IBGE_MUNICIPIO_BODY, is(retornoSucesso.getCodigoIbgeMunicipio().intValue())),
                jsonPath(RANKING_MUNICIPIO_BODY, is(retornoSucesso.getRankingMunicipio().intValue())),
                jsonPath(DATA_COLETA_DADOS_BODY, is(retornoSucesso.getDataColetaDados().format(DateTimeFormatter.ISO_LOCAL_DATE))),
                jsonPath(SEMANA_EPIDEMIOLOGICA_BODY, is(retornoSucesso.getSemanaEpidemiologica())),
                jsonPath(POPULACAO_ESTIMADA_BODY, is(retornoSucesso.getPopulacaoEstimada().intValue())),
                jsonPath(POPULACAO_ESTIMADA_2019_BODY, is(retornoSucesso.getPopulacaoEstimada2019().intValue())),
                jsonPath(CASOS_CONFIRMADOS_ULTIMO_DIA_BODY, is(retornoSucesso.getCasosConfirmadosUltimoDia().intValue())),
                jsonPath(CASOS_CONFIRMADOS_ULTIMO_DIA_POR_100_K_HABITANTES_BODY, is(retornoSucesso.getCasosConfirmadosUltimoDiaPor100kHabitantes().intValue())),
                jsonPath(DATA_DADO_BODY, is(retornoSucesso.getDataDado().format(DateTimeFormatter.ISO_LOCAL_DATE))),
                jsonPath(TAXA_MORTALIDADE_ULTIMO_DIA_BODY, is(retornoSucesso.getTaxaMortalidadeUltimoDia().intValue())),
                jsonPath(MORTES_ULTIMO_DIA_BODY, is(retornoSucesso.getMortesUltimoDia().intValue())),
                jsonPath(NOVOS_CASOS_BODY, is(retornoSucesso.getNovosCasos().intValue())),
                jsonPath(NOVAS_MORTES_BODY, is(retornoSucesso.getNovasMortes().intValue()))
        );
    }

    public static <T> ResultMatcher assertBodyDefaultDataPorPosicao(int posicao, CovidDataDTO retornoSucesso) {
        return matchAll(
                (jsonPath(format("$[%d].%s", posicao, UF_PARAM_BODY), is(retornoSucesso.getUf()))),
                jsonPath(format("$[%d].%s", posicao, MUNICIPIO_PARAM_BODY), is(retornoSucesso.getMunicipio())),
                jsonPath(format("$[%d].%s", posicao, CODIGO_IBGE_MUNICIPIO_BODY), is(retornoSucesso.getCodigoIbgeMunicipio().intValue())),
                jsonPath(format("$[%d].%s", posicao, RANKING_MUNICIPIO_BODY), is(retornoSucesso.getRankingMunicipio().intValue())),
                jsonPath(format("$[%d].%s", posicao, DATA_COLETA_DADOS_BODY), is(retornoSucesso.getDataColetaDados().format(DateTimeFormatter.ISO_LOCAL_DATE))),
                jsonPath(format("$[%d].%s", posicao, SEMANA_EPIDEMIOLOGICA_BODY), is(retornoSucesso.getSemanaEpidemiologica())),
                jsonPath(format("$[%d].%s", posicao, POPULACAO_ESTIMADA_BODY), is(retornoSucesso.getPopulacaoEstimada().intValue())),
                jsonPath(format("$[%d].%s", posicao, POPULACAO_ESTIMADA_2019_BODY), is(retornoSucesso.getPopulacaoEstimada2019().intValue())),
                jsonPath(format("$[%d].%s", posicao, CASOS_CONFIRMADOS_ULTIMO_DIA_BODY), is(retornoSucesso.getCasosConfirmadosUltimoDia().intValue())),
                jsonPath(format("$[%d].%s", posicao, CASOS_CONFIRMADOS_ULTIMO_DIA_POR_100_K_HABITANTES_BODY), is(retornoSucesso.getCasosConfirmadosUltimoDiaPor100kHabitantes().intValue())),
                jsonPath(format("$[%d].%s", posicao, DATA_DADO_BODY), is(retornoSucesso.getDataDado().format(DateTimeFormatter.ISO_LOCAL_DATE))),
                jsonPath(format("$[%d].%s", posicao, TAXA_MORTALIDADE_ULTIMO_DIA_BODY), is(retornoSucesso.getTaxaMortalidadeUltimoDia().intValue())),
                jsonPath(format("$[%d].%s", posicao, MORTES_ULTIMO_DIA_BODY), is(retornoSucesso.getMortesUltimoDia().intValue())),
                jsonPath(format("$[%d].%s", posicao, NOVOS_CASOS_BODY), is(retornoSucesso.getNovosCasos().intValue())),
                jsonPath(format("$[%d].%s", posicao, NOVAS_MORTES_BODY), is(retornoSucesso.getNovasMortes().intValue()))
        );
    }
}
