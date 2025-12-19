package com.streamflix.modelo;

public interface Reproducible {
    void iniciarReproduccion();
    void pausarReproduccion();
    void detenerReproduccion();
    boolean estaReproduciendo();
    int obtenerTiempoActual();
}

