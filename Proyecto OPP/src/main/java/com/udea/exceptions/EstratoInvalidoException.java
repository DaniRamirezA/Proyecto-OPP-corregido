package com.udea.exceptions;

public class EstratoInvalidoException extends PropException {
    // Constructor que permite indicar qué estrato es inválido
    public EstratoInvalidoException(int estrato) {
        super("El estrato '" + estrato + "' no es válido. Debe estar entre 1 y 6.");
    }
}