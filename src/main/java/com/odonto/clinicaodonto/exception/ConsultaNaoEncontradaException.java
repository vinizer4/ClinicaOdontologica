package com.odonto.clinicaodonto.exception;

public class ConsultaNaoEncontradaException extends RuntimeException {

    public ConsultaNaoEncontradaException() {
        super("Consulta não encontrada.");
    }
}