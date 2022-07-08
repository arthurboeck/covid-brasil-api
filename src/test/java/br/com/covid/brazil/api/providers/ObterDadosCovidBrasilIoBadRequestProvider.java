package br.com.covid.brazil.api.providers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static br.com.covid.brazil.api.util.UnitTestConstantes.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ObterDadosCovidBrasilIoBadRequestProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {

        return Stream.of(
                arguments("", ALEGRE_PARAM, UF_NULL_MSG, "Estado Inválido"),
                arguments(" ", ALEGRE_PARAM, UF_NULL_MSG, "Estado Inválido"),
                arguments(RS_PARAM, "", MUNICIPIO_NULL_MSG, "Municipio Inválido"),
                arguments(RS_PARAM, " ", MUNICIPIO_NULL_MSG, "Municipio Inválido")
        );
    }
}