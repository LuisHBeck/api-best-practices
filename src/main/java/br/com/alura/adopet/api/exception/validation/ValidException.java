package br.com.alura.adopet.api.exception.validation;

public class ValidException extends RuntimeException{
    public ValidException(String message) {
        super(message);
    }
}
