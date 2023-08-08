package br.com.catalisa.bancozup.exception;

public class SaqueInvalidoException extends RuntimeException{
    public SaqueInvalidoException(String mensagem) {
        super(mensagem);
    }
}
