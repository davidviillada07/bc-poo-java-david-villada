package com.streamflix.servicio;

import com.streamflix.modelo.Content;
import com.streamflix.modelo.Movie;
import com.streamflix.modelo.Series;
import com.streamflix.modelo.Documentary;
import com.streamflix.excepciones.ContenidoInvalidoException;
import com.streamflix.excepciones.ContenidoNoEncontradoException;
import java.util.ArrayList;

public class GestorCatalogo {
    private ArrayList<Content> catalogo;
    private int capacidadMaxima;
    
    public GestorCatalogo(int capacidadMaxima) {
        this.catalogo = new ArrayList<>();
        this.capacidadMaxima = capacidadMaxima;
    }
    
    public GestorCatalogo() {
        this(1000);
    }
    
    public void agregarContenido(Content contenido) throws ContenidoInvalidoException {
        try {
            if (contenido == null) {
                throw new ContenidoInvalidoException("El contenido no puede ser nulo");
            }
            
            if (contenido.getContentCode() == null || contenido.getContentCode().trim().isEmpty()) {
                throw new ContenidoInvalidoException("El código del contenido no puede estar vacío");
            }
            
            if (!contenido.getContentCode().matches("[A-Z]{3}-\\d{3}")) {
                throw new ContenidoInvalidoException("El código debe tener formato XXX-### (ejemplo: MOV-001)");
            }
            
            if (contenido.getTitle() == null || contenido.getTitle().trim().isEmpty()) {
                throw new ContenidoInvalidoException("El título no puede estar vacío");
            }
            
            if (contenido.getDuration() <= 0) {
                throw new ContenidoInvalidoException("La duración debe ser mayor a cero");
            }
            
            if (catalogo.size() >= capacidadMaxima) {
                throw new ContenidoInvalidoException("El catálogo ha alcanzado su capacidad máxima: " + capacidadMaxima);
            }
            
            for (Content c : catalogo) {
                if (c.getContentCode().equals(contenido.getContentCode())) {
                    throw new ContenidoInvalidoException("Ya existe un contenido con el código: " + contenido.getContentCode());
                }
            }
            
            catalogo.add(contenido);
            System.out.println("✅ Contenido agregado: " + contenido.obtenerDescripcion());
            
        } catch (IllegalArgumentException e) {
            throw new ContenidoInvalidoException("Error de validación: " + e.getMessage(), e);
        }
    }
    
    public Content buscarContenido(String codigo) throws ContenidoNoEncontradoException {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new ContenidoNoEncontradoException("El código de búsqueda no puede estar vacío");
        }
        
        for (Content contenido : catalogo) {
            if (contenido.getContentCode().equals(codigo)) {
                return contenido;
            }
        }
        
        throw new ContenidoNoEncontradoException("No se encontró contenido con el código: " + codigo);
    }
    
    public ArrayList<Content> buscarPorGenero(String genero) {
        ArrayList<Content> resultados = new ArrayList<>();
        if (genero == null || genero.trim().isEmpty()) {
            return resultados;
        }
        
        for (Content contenido : catalogo) {
            if (contenido.getGenre().equalsIgnoreCase(genero)) {
                resultados.add(contenido);
            }
        }
        return resultados;
    }
    
    public void procesarContenido(String codigo) {
        try {
            Content contenido = buscarContenido(codigo);
            System.out.println("Procesando: " + contenido.obtenerDescripcion());
            System.out.println("Precio: $" + String.format("%.2f", contenido.calcularPrecio()));
            System.out.println("Duración: " + String.format("%.2f", contenido.calcularDuracionEnHoras()) + " horas");
        } catch (ContenidoNoEncontradoException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
    
    public int obtenerCantidadContenidos() {
        return catalogo.size();
    }
    
    public void mostrarResumen() {
        int peliculas = 0;
        int series = 0;
        int documentales = 0;
        
        for (Content contenido : catalogo) {
            if (contenido instanceof Movie) {
                peliculas++;
            } else if (contenido instanceof Series) {
                series++;
            } else if (contenido instanceof Documentary) {
                documentales++;
            }
        }
        
        System.out.println("\n=== Resumen del Catálogo ===");
        System.out.println("Total de contenidos: " + catalogo.size());
        System.out.println("Películas: " + peliculas);
        System.out.println("Series: " + series);
        System.out.println("Documentales: " + documentales);
        System.out.println("Capacidad utilizada: " + catalogo.size() + "/" + capacidadMaxima);
    }
}

