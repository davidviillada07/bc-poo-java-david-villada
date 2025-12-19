package com.streamflix.modelo;

import java.util.ArrayList;

public class Movie extends Content implements Reproducible, Calificable, Recomendable {
    private String director;
    private double rating;
    private boolean reproduciendo;
    private int tiempoActual;
    private ArrayList<Double> calificaciones;
    
    public Movie(String contentCode, String title, String genre, int duration, int releaseYear, String director, double rating) {
        super(contentCode, title, genre, duration, releaseYear);
        this.director = director;
        this.rating = rating;
        this.reproduciendo = false;
        this.tiempoActual = 0;
        this.calificaciones = new ArrayList<>();
    }
    
    public Movie(String title, String genre, int duration, int releaseYear, String director, double rating) {
        super(title, genre, duration, releaseYear);
        this.director = director;
        this.rating = rating;
        this.reproduciendo = false;
        this.tiempoActual = 0;
        this.calificaciones = new ArrayList<>();
    }
    
    public String getDirector() {
        return director;
    }
    
    public void setDirector(String director) {
        if (director == null || director.trim().isEmpty()) {
            throw new IllegalArgumentException("El director no puede estar vacío");
        }
        this.director = director;
    }
    
    public double getRating() {
        return rating;
    }
    
    public void setRating(double rating) {
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("El rating debe estar entre 0 y 10");
        }
        this.rating = rating;
    }
    
    @Override
    public void mostrarInfo() {
        System.out.println("=== Información de Película ===");
        System.out.println("Código: " + contentCode);
        System.out.println("Título: " + title);
        System.out.println("Género: " + genre);
        System.out.println("Duración: " + duration + " minutos (" + String.format("%.1f", calcularDuracionEnHoras()) + " horas)");
        System.out.println("Año: " + releaseYear);
        System.out.println("Director: " + director);
        System.out.println("Rating: " + rating + "/10");
        System.out.println("-----------------------------------");
    }
    
    @Override
    public double calcularPrecio() {
        double precioBase = 5000.0;
        double bonoRating = rating * 500;
        return precioBase + bonoRating;
    }
    
    @Override
    public String obtenerDescripcion() {
        return "Película: " + title + " dirigida por " + director + " (Rating: " + rating + "/10)";
    }
    
    @Override
    public void iniciarReproduccion() {
        reproduciendo = true;
        tiempoActual = 0;
        System.out.println("Reproduciendo: " + title);
    }
    
    @Override
    public void pausarReproduccion() {
        if (reproduciendo) {
            reproduciendo = false;
            System.out.println("Pausado: " + title + " en minuto " + tiempoActual);
        }
    }
    
    @Override
    public void detenerReproduccion() {
        reproduciendo = false;
        tiempoActual = 0;
        System.out.println("Detenido: " + title);
    }
    
    @Override
    public boolean estaReproduciendo() {
        return reproduciendo;
    }
    
    @Override
    public int obtenerTiempoActual() {
        return tiempoActual;
    }
    
    @Override
    public void agregarCalificacion(double calificacion) {
        if (calificacion < 0 || calificacion > 10) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 10");
        }
        calificaciones.add(calificacion);
    }
    
    @Override
    public double obtenerPromedioCalificaciones() {
        if (calificaciones.isEmpty()) {
            return 0.0;
        }
        double suma = 0;
        for (Double cal : calificaciones) {
            suma += cal;
        }
        return suma / calificaciones.size();
    }
    
    @Override
    public int obtenerNumeroCalificaciones() {
        return calificaciones.size();
    }
    
    @Override
    public boolean tieneCalificaciones() {
        return !calificaciones.isEmpty();
    }
    
    @Override
    public boolean esRecomendablePara(String generoPreferido) {
        return genre.equalsIgnoreCase(generoPreferido) && rating >= 7.0;
    }
    
    @Override
    public double calcularPuntuacionRecomendacion() {
        double puntuacion = rating;
        if (rating >= 8.0) {
            puntuacion += 1.0;
        }
        if (releaseYear >= 2020) {
            puntuacion += 0.5;
        }
        return Math.min(puntuacion, 10.0);
    }
    
    @Override
    public String obtenerRazonRecomendacion() {
        if (rating >= 8.0) {
            return "Excelente rating (" + rating + "/10)";
        }
        return "Rating: " + rating + "/10";
    }
    
    @Override
    public boolean esContenidoDestacado() {
        return rating >= 8.5;
    }
    
    public String obtenerTipo() {
        return "Película";
    }
}

