package com.udea.exceptions;

public class TipoPropiedadInvalidaException extends PropException {
    // Constructor que llama al constructor de la clase base con un mensaje específico
    public TipoPropiedadInvalidaException(String tipoPropiedad) {
        super("El tipo de propiedad '" + tipoPropiedad + "' no es válido.");
    }
}