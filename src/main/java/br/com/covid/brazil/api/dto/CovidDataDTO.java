package br.com.covid.brazil.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CovidDataDTO {

    private String city;
    private Long city_ibge_code;
    private LocalDate date;
    private String epidemiological_week;
    private Long estimated_population;
    private Long estimated_population_2019;
    private Long last_available_confirmed;
    private BigDecimal last_available_confirmed_per_100k_inhabitants;
    private LocalDate last_available_date;
    private BigDecimal last_available_death_rate;
    private Long last_available_deaths;
    private Long new_confirmed;
    private Long new_deaths;
    private Long order_for_place;
    private String state;

}
