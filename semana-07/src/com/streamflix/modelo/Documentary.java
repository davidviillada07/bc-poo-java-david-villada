package com.streamflix.modelo;

import java.util.ArrayList;

public class Documentary extends Content implements Calificable, Recomendable {
    private String narrator;
    private String topic;
    private boolean isOriginal;
    private ArrayList<Double> calificaciones;
    
    public Documentary(String contentCode, String title, String genre, int duration, int releaseYear, String narrator, String topic, boolean isOriginal) {
        super(contentCode, title, genre, duration, releaseYear);
        this.narrator = narrator;
        this.topic = topic;
        this.isOriginal = isOriginal;
        this.calificaciones = new ArrayList<>();
    }
    
    public Documentary(String title, String genre, int duration, int releaseYear, String narrator, String topic, boolean isOriginal) {
        super(title, genre, duration, releaseYear);
        this.narrator = narrator;
        this.topic = topic;
        this.isOriginal = isOriginal;
        this.calificaciones = new ArrayList<>();
    }
    
    public String getNarrator() {
        return narrator;
    }
    
    public void setNarrator(String narrator) {
        if (narrator == null || narrator.trim().isEmpty()) {
            throw new IllegalArgumentException("El narrador no puede estar vacío");
        }
        this.narrator = narrator;
    }
    
    public String getTopic() {
        return topic;
    }
    
    public void setTopic(String topic) {
        if (topic == null || topic.trim().isEmpty()) {
            throw new IllegalArgumentException("El tema no puede estar vacío");
        }
        this.topic = topic;
    }
    
    public boolean isOriginal() {
        return isOriginal;
    }
    
    public void setOriginal(boolean isOriginal) {
        this.isOriginal = isOriginal;
    }
    
    @Override
    public void mostrarInfo() {
        System.out.println("=== Información de Documental ===");
        System.out.println("Código: " + contentCode);
        System.out.println("Título: " + title);
        System.out.println("Género: " + genre);
        System.out.println("Duración: " + duration + " minutos (" + String.format("%.1f", calcularDuracionEnHoras()) + " horas)");
        System.out.println("Año: " + releaseYear);
        System.out.println("Narrador: " + narrator);
        System.out.println("Tema: " + topic);
        System.out.println("Contenido original StreamFlix: " + (isOriginal ? "Sí" : "No"));
        System.out.println("-----------------------------------");
    }
    
    @Override
    public double calcularPrecio() {
        double precioBase = 2000.0;
        if (isOriginal) {
            return precioBase * 1.5;
        }
        return precioBase;
    }
    
    @Override
    public String obtenerDescripcion() {
        String original = isOriginal ? " (Original StreamFlix)" : "";
        return "Documental: " + title + " narrado por " + narrator + " - Tema: " + topic + original;
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
        return genre.equalsIgnoreCase(generoPreferido) && isOriginal;
    }
    
    @Override
    public double calcularPuntuacionRecomendacion() {
        double puntuacion = 6.0;
        if (isOriginal) {
            puntuacion += 2.0;
        }
        if (releaseYear >= 2020) {
            puntuacion += 0.5;
        }
        return Math.min(puntuacion, 10.0);
    }
    
    @Override
    public String obtenerRazonRecomendacion() {
        if (isOriginal) {
            return "Contenido original de StreamFlix sobre " + topic;
        }
        return "Documental sobre " + topic;
    }
    
    @Override
    public boolean esContenidoDestacado() {
        return isOriginal;
    }
    
    public String obtenerTipo() {
        return "Documental";
    }
}

