package com.streamflix.modelo;

public interface Recomendable {
    boolean esRecomendablePara(String generoPreferido);
    double calcularPuntuacionRecomendacion();
    String obtenerRazonRecomendacion();
    boolean esContenidoDestacado();
}

