package br.com.covid.brazil.api.controller;

import br.com.covid.brazil.api.client.IBrasilIoClient;
import br.com.covid.brazil.api.dto.CovidDataDTO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.mappers.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Covid", value = "Dados Sobre o Covid por Cidade")
@Validated
public class CovidDataController {

    @Autowired
    private IBrasilIoClient iBrasilIoClient;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/covid")
    @ApiOperation(value = "Dados sobre o covid por cidade")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<CovidDataDTO> getCovidData(
            @ApiParam(value = "UF a ser pesquisado", required = true)
            @NotNull(message = "UF não pode ser null/vazio") @NotBlank(message = "UF não pode ser null/vazio")
            @RequestParam(name = "uf") String uf,

            @ApiParam(value = "Cidade a ser pesquisada", required = true)
            @NotNull(message = "Cidade não pode ser null/vazio") @NotBlank(message = "Cidade não pode ser null/vazio")
            @RequestParam(name = "cidade") String cidade) {
        return ResponseEntity.ok(iBrasilIoClient.getCovidData(uf, cidade));
    }
}