package br.com.covid.brazil.api.controller;

import br.com.covid.brazil.api.dto.CovidDataDTO;
import br.com.covid.brazil.api.service.CovidDataService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Dados Covid Brasil", value = "Dados Sobre o Covid por Municipio")
@Validated
public class CovidDataController {

    @Autowired
    CovidDataService covidDataService;

    @Autowired
    ModelMapper mapper;

    @GetMapping("/covid")
    @ApiOperation(value = "Buscar todos os detalhes dos dados sobre o covid por municipio do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public List<CovidDataDTO> getAllCovidData() {
        return mapper.map(covidDataService.getAllCovidData(),
                new TypeToken<List<CovidDataDTO>>() {
                }.getType());
    }

    @GetMapping("/covid/{id}")
    @ApiOperation(value = "Buscar os detalhes de um dado sobre o covid por municipio específico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public CovidDataDTO getCovidData(@PathVariable("id") int id) {
        return mapper.map(covidDataService.getCovidDataById(id), CovidDataDTO.class);
    }

    @DeleteMapping("/covid/{id}")
    @ApiOperation(value = "Exclusão de um dado sobre o covid por municipio específico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public void deleteCovidData(@PathVariable("id") int id) {
        covidDataService.delete(id);
    }

    @PostMapping("/covid")
    @ApiOperation(value = "Salvar os detalhes de um dado sobre o covid por municipio específico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<CovidDataDTO> obterDadosCovid(
            @ApiParam(value = "UF a ser pesquisada", required = true)
            @NotNull(message = "UF não pode ser null/vazia") @NotBlank(message = "UF não pode ser null/vazia")
            @RequestParam(name = "uf") String uf,
            @ApiParam(value = "Municipio a ser pesquisado", required = true)
            @NotNull(message = "Municipio não pode ser null/vazio") @NotBlank(message = "Municipio não pode ser null/vazio")
            @RequestParam(name = "municipio") String municipio) {
        try {
            return ResponseEntity.ok(mapper.map(covidDataService.obterDadosCovid(uf, municipio), CovidDataDTO.class));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}