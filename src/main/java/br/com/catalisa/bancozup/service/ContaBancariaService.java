package br.com.catalisa.bancozup.service;


import br.com.catalisa.bancozup.exception.DepositoInvalidoException;
import br.com.catalisa.bancozup.exception.SaqueInvalidoException;
import br.com.catalisa.bancozup.model.ContaBancaria;
import br.com.catalisa.bancozup.repository.ContaBancariaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ContaBancariaService {

    @Autowired
    ContaBancariaRepository contaBancariaRepository;

    @Autowired
    LogEventosService logEventosService;

    public List<ContaBancaria> listarTodasContas() {
        return contaBancariaRepository.findAll();
    }

    public ContaBancaria obterContaPorId(Long id) {
        return contaBancariaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Conta não encontrada com o ID: " + id));
    }

    public ContaBancaria criarConta(ContaBancaria conta) {
        return contaBancariaRepository.save(conta);
    }

    public void deletarConta(Long id) {
        contaBancariaRepository.deleteById(id);
    }

    public ContaBancaria depositar(Long id, double valor) {
        try{
            ContaBancaria conta = contaBancariaRepository.findById(id).orElse(null);
            if (conta != null) {
                double novoSaldo = conta.getSaldo() + valor;
                conta.setSaldo(novoSaldo);
                return contaBancariaRepository.save(conta);
            }
            return null;
        }catch (EntityNotFoundException e) {
            throw new SaqueInvalidoException("Conta não encontrada para saque.");
        }
    }

    public ContaBancaria sacar(Long id, double valor) {
        try{
        ContaBancaria conta = contaBancariaRepository.findById(id).orElse(null);
        if (conta != null) {
            double saldoAtual = conta.getSaldo();
            if (saldoAtual >= valor) {
                double novoSaldo = conta.getSaldo() - valor;
                conta.setSaldo(novoSaldo);
                return contaBancariaRepository.save(conta);
            }
        }
        return null;
        }catch (EntityNotFoundException e) {
            throw new DepositoInvalidoException("Conta não encontrada para saque.");
        }
    }





}
