package br.com.catalisa.bancozup.exception;

public class DepositoInvalidoException extends RuntimeException{
    public DepositoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
