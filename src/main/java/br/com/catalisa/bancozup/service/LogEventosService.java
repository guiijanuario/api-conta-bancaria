package br.com.catalisa.bancozup.service;

import br.com.catalisa.bancozup.enums.TipoLogEvento;
import br.com.catalisa.bancozup.model.ContaBancaria;
import br.com.catalisa.bancozup.model.LogEventos;
import br.com.catalisa.bancozup.repository.LogEventosRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogEventosService {

    private static final Logger logger = LoggerFactory.getLogger(LogEventos.class);

    @Autowired
    LogEventosRepository logEventosRepository;


    public void gerarLogListarAllContas(TipoLogEvento tipoLogEvento) {
        LogEventos listagemLog = new LogEventos();

        listagemLog.setEvento(String.format("Listou todas as contas"));
        listagemLog.setTipoLogEvento(tipoLogEvento);
        logger.info(listagemLog.getEvento());
    }

    public void gerarLogBuscaDeConta(ContaBancaria conta, TipoLogEvento tipoLogEvento) {
        LogEventos listagemBuscaLog = new LogEventos();

        listagemBuscaLog.setEvento(String.format("Conta com número: %s, na agência: %s, titular: %s, foi encontrada.",
                conta.getNumeroConta(),
                conta.getAgencia(),
                conta.getTitularConta()));
        listagemBuscaLog.setTipoLogEvento(tipoLogEvento);
        logger.info(listagemBuscaLog.getEvento());
    }

    public void gerarLogContaCadastrada(ContaBancaria conta, TipoLogEvento tipoLogEvento){
        LogEventos contaCadastradaLog = new LogEventos();

        contaCadastradaLog.setEvento(String.format("Conta com número: %s, na agência: %s, titular: %s, foi cadastrada no sistema.",
                conta.getNumeroConta(),
                conta.getAgencia(),
                conta.getTitularConta()));
        contaCadastradaLog.setTipoLogEvento(tipoLogEvento);

        logEventosRepository.save(contaCadastradaLog);
        logger.info(contaCadastradaLog.getEvento());
    }

    public void gerarLogContaDeletada(ContaBancaria conta, TipoLogEvento tipoLogEvento){
        LogEventos contaCadastradaLog = new LogEventos();

        contaCadastradaLog.setEvento(String.format("Conta com número: %s, na agência: %s, titular: %s, foi deletada no sistema.",
                conta.getNumeroConta(),
                conta.getAgencia(),
                conta.getTitularConta()));
        contaCadastradaLog.setTipoLogEvento(tipoLogEvento);

        logEventosRepository.save(contaCadastradaLog);
        logger.info(contaCadastradaLog.getEvento());
    }

    public void gerarLogSaqueRealizado(ContaBancaria conta, double valorSaque, TipoLogEvento tipoLogEvento) {
        LogEventos saqueRealizadoLog = new LogEventos();

        saqueRealizadoLog.setEvento(String.format("Saque realizado na conta %s - Titular: %s. Valor sacado: %.2f",
                conta.getNumeroConta(),
                conta.getTitularConta(),
                valorSaque));
        saqueRealizadoLog.setTipoLogEvento(tipoLogEvento);
        logger.info(saqueRealizadoLog.getEvento());
    }

    public void gerarLogDepositolizado(ContaBancaria conta, double valorDeposito, TipoLogEvento tipoLogEvento) {
        LogEventos saqueRealizadoLog = new LogEventos();

        saqueRealizadoLog.setEvento(String.format("Deposito realizado na conta %s - Titular: %s. Valor depositado: %.2f",
                conta.getNumeroConta(),
                conta.getTitularConta(),
                valorDeposito));
        saqueRealizadoLog.setTipoLogEvento(tipoLogEvento);
        logger.info(saqueRealizadoLog.getEvento());
    }
}
