package com.streamflix.modelo;

public interface Calificable {
    void agregarCalificacion(double calificacion);
    double obtenerPromedioCalificaciones();
    int obtenerNumeroCalificaciones();
    boolean tieneCalificaciones();
}

