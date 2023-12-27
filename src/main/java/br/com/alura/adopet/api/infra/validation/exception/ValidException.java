package br.com.alura.adopet.api.infra.validation.exception;

public class ValidException extends RuntimeException{
    public ValidException(String message) {
        super(message);
    }
}
