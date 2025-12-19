package com.streamflix.excepciones;

public class ContenidoNoEncontradoException extends Exception {
    
    public ContenidoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
    
    public ContenidoNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

