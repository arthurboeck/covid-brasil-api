package br.com.covid.brazil.api.providers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ObterDadosCovidBrasilIoSucessoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {

        return Stream.of(
                arguments("rs", "Alegrete"),
                arguments("RS", "alegrete"),
                arguments("Rs", "ALEGRETE"),
                arguments("rS", "aLEGRETE")
        );
    }
}