package br.com.catalisa.bancozup.controller;


import br.com.catalisa.bancozup.dto.RespostaDepositoDTO;
import br.com.catalisa.bancozup.dto.RespostaSaqueDTO;
import br.com.catalisa.bancozup.enums.TipoLogEvento;
import br.com.catalisa.bancozup.model.ContaBancaria;
import br.com.catalisa.bancozup.service.ContaBancariaService;
import br.com.catalisa.bancozup.service.LogEventosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/contas", produces = {"application/json"})
@Tag(name = "API BancoZup")
public class ContaBancariaController {

    @Autowired
    ContaBancariaService contaBancariaService;

    @Autowired
    LogEventosService logEventosService;

    @GetMapping
    @Operation(summary = " : Lista todas as contas", method = "GET")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    public List<ContaBancaria> listarTodasContas() {
        logEventosService.gerarLogListarAllContas(TipoLogEvento.SAQUE_REALIZADO);
        return contaBancariaService.listarTodasContas();
    }

    @GetMapping("/{id}")
    @Operation(summary = " : Busca um ID especifico dentro do banco de dados", method = "GET")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    public Optional<ContaBancaria> obterContaPorId(@PathVariable Long id) {
        ContaBancaria conta = contaBancariaService.obterContaPorId(id);
        logEventosService.gerarLogBuscaDeConta(conta, TipoLogEvento.BUSCA_CONTA_ESPECIFICA);
        return Optional.ofNullable(contaBancariaService.obterContaPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = " : Cria uma conta", method = "POST")
    @ApiResponse(responseCode = "201", description = "Conta criada com sucesso")
    public ContaBancaria criarConta(@Valid @NotNull @RequestBody ContaBancaria conta) {
        logEventosService.gerarLogContaCadastrada(conta, TipoLogEvento.CONTA_CRIADA);
        return contaBancariaService.criarConta(conta);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = " : Deleta uma conta pelo ID", method = "DELETE")
    @ApiResponse(responseCode = "200", description = "Conta deletada com sucesso")
    public void deletarConta(@PathVariable Long id) {
        ContaBancaria conta = contaBancariaService.obterContaPorId(id);
        logEventosService.gerarLogContaDeletada(conta, TipoLogEvento.CONTA_DELETADA);
        contaBancariaService.deletarConta(id);
    }

    @PutMapping("/deposito/{id}")
    @Operation(summary = "Deposita um valor na conta", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depósito realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    })
    public RespostaDepositoDTO deposito(@PathVariable Long id, @RequestBody double valor) {
        ContaBancaria contaAtualizada = contaBancariaService.depositar(id, valor);
        if (contaAtualizada != null) {
            logEventosService.gerarLogDepositolizado(contaAtualizada, valor, TipoLogEvento.DEPOSITO_REALIZADO);
            return new RespostaDepositoDTO(valor, contaAtualizada.getSaldo());
        }
        return null;
    }

    @PutMapping("/saque/{id}")
    @Operation(summary = "Saca um valor na conta", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saque realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    })
    public RespostaSaqueDTO saque(@PathVariable Long id, @RequestBody double valor) {
        ContaBancaria contaAtualizada = contaBancariaService.sacar(id, valor);
        if (contaAtualizada != null) {
            logEventosService.gerarLogSaqueRealizado(contaAtualizada, valor, TipoLogEvento.SAQUE_REALIZADO);
            return new RespostaSaqueDTO(valor, contaAtualizada.getSaldo());
        }
        return null;
    }
}
