package br.com.covid.brazil.api.controller;

import br.com.covid.brazil.api.client.IBrasilIoService;
import br.com.covid.brazil.api.dto.CovidDataDTO;
import br.com.covid.brazil.api.model.CovidData;
import br.com.covid.brazil.api.service.ICovidDataService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Dados Covid Brasil", value = "Dados Sobre o Covid por Municipio")
@Validated
public class CovidDataController {

    @Autowired
    ICovidDataService iCovidDataService;

    @Autowired
    IBrasilIoService iBrasilIoService;

    @Autowired
    ModelMapper mapper;

    private static Logger log;

    @GetMapping("/covid")
    @ApiOperation(value = "Buscar todos os dados sobre o covid por municipio do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<List<CovidDataDTO>> getAllCovidData() {
        try {
            List<CovidDataDTO> resultList = mapper.map(iCovidDataService.getAllCovidData(),
                    new TypeToken<List<CovidDataDTO>>() {
                    }.getType());

            return ResponseEntity.ok(resultList);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/covid/{id}")
    @ApiOperation(value = "Buscar os dados sobre o covid por municipio específico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<CovidDataDTO> getCovidDataById(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(mapper.map(iCovidDataService.getCovidDataById(id), CovidDataDTO.class));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/covid/{id}")
    @ApiOperation(value = "Exclusão de um dado sobre o covid por municipio específico")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity deleteCovidData(@PathVariable("id") int id) {
        try {
            iCovidDataService.delete(id);
        } catch (Exception e) {
            log.warning(format("Alguma coisa deu errado, ao realizar delete: ID %d", id));
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/covid/externo")
    @ApiOperation(value = "Obter dados sobre o covid por municipio específico - BrasilIo Data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<CovidDataDTO> obterDadosCovidBrasilIO(
            @ApiParam(value = "UF a ser pesquisada", required = true)
            @NotNull(message = "UF não pode ser null/vazia") @NotBlank(message = "UF não pode ser null/vazia")
            @RequestParam(name = "uf") String uf,
            @ApiParam(value = "Municipio a ser pesquisado", required = true)
            @NotNull(message = "Municipio não pode ser null/vazio") @NotBlank(message = "Municipio não pode ser null/vazio")
            @RequestParam(name = "municipio") String municipio) {
        try {
            final var covidDataDTO = iBrasilIoService.obterDadosCovid(uf, municipio);
            CovidData persisted = iCovidDataService.salvarHistoricoConsulta(covidDataDTO);
            return ResponseEntity.ok(mapper.map(persisted, CovidDataDTO.class));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/covid")
    @ApiOperation(value = "Salvar os dados sobre o covid por municipio específico")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error")})
    public ResponseEntity<CovidDataDTO> salvarDadosCovid(
            @ApiParam(value = "UF a ser pesquisada", required = true)
            @NotNull(message = "UF não pode ser null/vazia") @NotBlank(message = "UF não pode ser null/vazia")
            @RequestParam(name = "uf") String uf,
            @ApiParam(value = "Municipio a ser pesquisado", required = true)
            @NotNull(message = "Municipio não pode ser null/vazio") @NotBlank(message = "Municipio não pode ser null/vazio")
            @RequestParam(name = "municipio") String municipio) {
        try {
            final var covidDataDTO = iBrasilIoService.obterDadosCovid(uf, municipio);
            CovidData persisted = iCovidDataService.salvarHistoricoConsulta(covidDataDTO);
            return ResponseEntity.created(URI.create(format("/covid/%d", persisted.getId()))).body(mapper.map(persisted, CovidDataDTO.class));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}