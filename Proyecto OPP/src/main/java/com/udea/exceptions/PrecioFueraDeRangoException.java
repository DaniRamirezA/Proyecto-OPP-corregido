package com.udea.exceptions;

public class PrecioFueraDeRangoException extends PropException {
    // Constructor que permite indicar cuál es el precio fuera de rango
    public PrecioFueraDeRangoException(int precio) {
        super("El precio '" + precio + "' está fuera del rango permitido.");
    }
}