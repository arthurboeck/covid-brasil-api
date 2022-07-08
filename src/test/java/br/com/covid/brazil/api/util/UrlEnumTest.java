package br.com.covid.brazil.api.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UrlEnumTest {
    OBTER_DADOS_COVID_BRASIL_IO("/api/v1/covid/externo"),
    PERSISTIR_DADOS_COVID_BRASIL_IO("/api/v1/covid"),
    LISTAR_TODAS_CONSULTAS("/api/v1/covid"),
    LISTAR_CONSULTA_BY_ID("/api/v1/{id}"),
    DELETE_CONSULTA_BY_ID("/api/v1/{id}");

    private final String url;
}