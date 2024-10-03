package com.udea.exceptions;

public class PropiedadNoEncontradaException extends PropException {
    // Constructor que permite indicar qué matrícula no fue encontrada
    public PropiedadNoEncontradaException(int numeroDeMatricula) {
        super("No se encontró ninguna propiedad con la matrícula número: " + numeroDeMatricula);
    }
}