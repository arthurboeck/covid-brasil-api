package br.com.covid.brazil.api.controller;

import br.com.covid.brazil.api.client.IBrasilIoService;
import br.com.covid.brazil.api.dto.CovidDataDTO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Dados Covid Brasil", value = "Dados Sobre o Covid por Municipio")
@Validated
public class CovidDataController {

    @Autowired
    private IBrasilIoService iBrasilIoClient;

    @GetMapping("/covid")
    @ApiOperation(value = "Dados sobre o covid por municipio")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<CovidDataDTO> getCovidData(
            @ApiParam(value = "UF a ser pesquisada", required = true)
            @NotNull(message = "UF não pode ser null/vazia") @NotBlank(message = "UF não pode ser null/vazia")
            @RequestParam(name = "uf") String uf,

            @ApiParam(value = "Municipio a ser pesquisado", required = true)
            @NotNull(message = "Municipio não pode ser null/vazio") @NotBlank(message = "Municipio não pode ser null/vazio")
            @RequestParam(name = "municipio") String municipio) {
        try {
            return ResponseEntity.ok(iBrasilIoClient.obterDadosCovid(uf, municipio));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}