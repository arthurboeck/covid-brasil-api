package br.com.covid.brazil.api.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UrlEnumTest {
    OBTER_DADOS_COVID_ESTADO_MUNICIO_API_EXTERNA("/api/v1/covid/externo");

    private final String url;
}