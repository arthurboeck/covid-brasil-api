package br.com.covid.brazil.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CovidDataDTO {

    @JsonAlias({"state"})
    private String uf;

    @JsonAlias({"city"})
    private String municipio;

    @JsonAlias({"city_ibge_code"})
    private Long codigoIbgeMunicipio;

    @JsonAlias({"order_for_place"})
    private Long rankingMunicipio;

    @JsonAlias({"date"})
    private LocalDate dataColetaDados;

    @JsonAlias({"epidemiological_week"})
    private String semanaEpidemiologica;

    @JsonAlias({"estimated_population"})
    private Long populacaoEstimada;

    @JsonAlias({"estimated_population_2019"})
    private Long populacaoEstimada2019;

    @JsonAlias({"last_available_confirmed"})
    private Long casosConfirmadosUltimoDia;

    @JsonAlias({"last_available_confirmed_per_100k_inhabitants"})
    private BigDecimal casosConfirmadosUltimoDiaPor100kHabitantes;

    @JsonAlias({"last_available_date"})
    private LocalDate dataDado;

    @JsonAlias({"last_available_death_rate"})
    private BigDecimal taxaMortalidadeUltimoDia;

    @JsonAlias({"last_available_deaths"})
    private Long mortesUltimoDia;

    @JsonAlias({"new_confirmed"})
    private Long novosCasos;

    @JsonAlias({"new_deaths"})
    private Long novasMortes;
}
