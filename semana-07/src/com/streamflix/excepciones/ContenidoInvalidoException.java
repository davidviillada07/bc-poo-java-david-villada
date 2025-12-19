package com.streamflix.excepciones;

public class ContenidoInvalidoException extends Exception {
    
    public ContenidoInvalidoException(String mensaje) {
        super(mensaje);
    }
    
    public ContenidoInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

