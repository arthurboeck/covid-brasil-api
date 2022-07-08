package br.com.covid.brazil.api.util;

public class UnitTestConstantes {

    public static final String UF_PARAM_BODY = "uf";
    public static final String MUNICIPIO_PARAM_BODY = "municipio";
    public static final String CODIGO_IBGE_MUNICIPIO_BODY = "codigoIbgeMunicipio";
    public static final String RANKING_MUNICIPIO_BODY = "rankingMunicipio";
    public static final String DATA_COLETA_DADOS_BODY = "dataColetaDados";
    public static final String SEMANA_EPIDEMIOLOGICA_BODY = "semanaEpidemiologica";
    public static final String POPULACAO_ESTIMADA_BODY = "populacaoEstimada";
    public static final String POPULACAO_ESTIMADA_2019_BODY = "populacaoEstimada2019";
    public static final String CASOS_CONFIRMADOS_ULTIMO_DIA_BODY = "casosConfirmadosUltimoDia";
    public static final String CASOS_CONFIRMADOS_ULTIMO_DIA_POR_100_K_HABITANTES_BODY = "casosConfirmadosUltimoDiaPor100kHabitantes";
    public static final String DATA_DADO_BODY = "dataDado";
    public static final String TAXA_MORTALIDADE_ULTIMO_DIA_BODY = "taxaMortalidadeUltimoDia";
    public static final String MORTES_ULTIMO_DIA_BODY = "mortesUltimoDia";
    public static final String NOVOS_CASOS_BODY = "novosCasos";
    public static final String NOVAS_MORTES_BODY = "novasMortes";
    public static final String ERROR_BODY = "error";

    public static final String ALEGRE_PARAM = "Alegrete";
    public static final String RS_PARAM = "RS";


    public static final String CONSTRAINT_VIOLATION_EXCEPTION_MSG = "javax.validation.ConstraintViolationException";
    public static final String MUNICIPIO_NULL_MSG = "getCovidData.municipio: Municipio não pode ser null/vazio";
    public static final String UF_NULL_MSG = "getCovidData.uf: UF não pode ser null/vazia";
    public static final String UF_REQUIRED_MSG = "Required request parameter 'uf' for method parameter type String is not present";
    public static final String MUNICIPIO_REQUIRED_MSG = "Required request parameter 'municipio' for method parameter type String is not present";
}
