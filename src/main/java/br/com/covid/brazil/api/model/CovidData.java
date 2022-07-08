package br.com.covid.brazil.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class CovidData {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String uf;

    @Column
    private String municipio;

    @Column
    private Long codigoIbgeMunicipio;

    @Column
    private Long rankingMunicipio;

    @Column
    private LocalDate dataColetaDados;

    @Column
    private String semanaEpidemiologica;

    @Column
    private Long populacaoEstimada;

    @Column
    private Long populacaoEstimada2019;

    @Column
    private Long casosConfirmadosUltimoDia;

    @Column
    private BigDecimal casosConfirmadosUltimoDiaPor100kHabitantes;

    @Column
    private LocalDate dataDado;

    @Column
    private BigDecimal taxaMortalidadeUltimoDia;

    @Column
    private Long mortesUltimoDia;

    @Column
    private Long novosCasos;

    @Column
    private Long novasMortes;
}
