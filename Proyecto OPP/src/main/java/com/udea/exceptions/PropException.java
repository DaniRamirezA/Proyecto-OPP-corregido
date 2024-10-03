package com.udea.exceptions;

public class PropException extends RuntimeException {
    // Constructor que permite definir un mensaje personalizado
    public PropException(String mensaje) {
        super(mensaje);
    }

    // Constructor que permite definir un mensaje y una causa
    public PropException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}