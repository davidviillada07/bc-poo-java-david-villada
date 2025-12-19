package com.streamflix.servicio;

import com.streamflix.modelo.Content;
import com.streamflix.modelo.Movie;
import com.streamflix.modelo.Series;
import com.streamflix.modelo.Documentary;
import com.streamflix.excepciones.ContenidoInvalidoException;
import com.streamflix.excepciones.ContenidoNoEncontradoException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class GestorCatalogo {
    private Map<String, Content> contenidosPorCodigo;
    private List<Content> historial;
    private Map<String, List<Content>> contenidosPorGenero;
    private int capacidadMaxima;
    
    public GestorCatalogo(int capacidadMaxima) {
        this.contenidosPorCodigo = new HashMap<>();
        this.historial = new ArrayList<>();
        this.contenidosPorGenero = new HashMap<>();
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
            
            if (historial.size() >= capacidadMaxima) {
                throw new ContenidoInvalidoException("El catálogo ha alcanzado su capacidad máxima: " + capacidadMaxima);
            }
            
            if (contenidosPorCodigo.containsKey(contenido.getContentCode())) {
                throw new ContenidoInvalidoException("Ya existe un contenido con el código: " + contenido.getContentCode());
            }
            
            contenidosPorCodigo.put(contenido.getContentCode(), contenido);
            historial.add(contenido);
            
            String genero = contenido.getGenre();
            if (!contenidosPorGenero.containsKey(genero)) {
                contenidosPorGenero.put(genero, new ArrayList<>());
            }
            contenidosPorGenero.get(genero).add(contenido);
            
            System.out.println("✅ Contenido agregado: " + contenido.obtenerDescripcion());
            
        } catch (IllegalArgumentException e) {
            throw new ContenidoInvalidoException("Error de validación: " + e.getMessage(), e);
        }
    }
    
    public Content buscarContenido(String codigo) throws ContenidoNoEncontradoException {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new ContenidoNoEncontradoException("El código de búsqueda no puede estar vacío");
        }
        
        Content contenido = contenidosPorCodigo.get(codigo);
        if (contenido == null) {
            throw new ContenidoNoEncontradoException("No se encontró contenido con el código: " + codigo);
        }
        
        return contenido;
    }
    
    public boolean existeContenido(String codigo) {
        return contenidosPorCodigo.containsKey(codigo);
    }
    
    public Content eliminarContenido(String codigo) throws ContenidoNoEncontradoException {
        Content contenido = contenidosPorCodigo.remove(codigo);
        if (contenido == null) {
            throw new ContenidoNoEncontradoException("No se encontró contenido con el código: " + codigo);
        }
        
        historial.remove(contenido);
        String genero = contenido.getGenre();
        if (contenidosPorGenero.containsKey(genero)) {
            contenidosPorGenero.get(genero).remove(contenido);
        }
        
        return contenido;
    }
    
    public List<Content> buscarPorGenero(String genero) {
        return contenidosPorGenero.getOrDefault(genero, new ArrayList<>());
    }
    
    public List<Content> obtenerTodos() {
        return new ArrayList<>(historial);
    }
    
    public List<Content> filtrarPorPrecio(double precioMin, double precioMax) {
        List<Content> resultados = new ArrayList<>();
        for (Content contenido : historial) {
            double precio = contenido.calcularPrecio();
            if (precio >= precioMin && precio <= precioMax) {
                resultados.add(contenido);
            }
        }
        return resultados;
    }
    
    public List<Content> filtrarPorAño(int añoMin, int añoMax) {
        List<Content> resultados = new ArrayList<>();
        for (Content contenido : historial) {
            int año = contenido.getReleaseYear();
            if (año >= añoMin && año <= añoMax) {
                resultados.add(contenido);
            }
        }
        return resultados;
    }
    
    public List<Content> filtrarPorDuracion(int duracionMin, int duracionMax) {
        List<Content> resultados = new ArrayList<>();
        for (Content contenido : historial) {
            int duracion = contenido.getDuration();
            if (duracion >= duracionMin && duracion <= duracionMax) {
                resultados.add(contenido);
            }
        }
        return resultados;
    }
    
    public double calcularTotalPrecios() {
        double total = 0;
        for (Content contenido : historial) {
            total += contenido.calcularPrecio();
        }
        return total;
    }
    
    public double calcularPromedioPrecio() {
        if (historial.isEmpty()) {
            return 0.0;
        }
        return calcularTotalPrecios() / historial.size();
    }
    
    public Content obtenerContenidoMasCaro() {
        if (historial.isEmpty()) {
            return null;
        }
        Content masCaro = historial.get(0);
        for (Content contenido : historial) {
            if (contenido.calcularPrecio() > masCaro.calcularPrecio()) {
                masCaro = contenido;
            }
        }
        return masCaro;
    }
    
    public Content obtenerContenidoMasBarato() {
        if (historial.isEmpty()) {
            return null;
        }
        Content masBarato = historial.get(0);
        for (Content contenido : historial) {
            if (contenido.calcularPrecio() < masBarato.calcularPrecio()) {
                masBarato = contenido;
            }
        }
        return masBarato;
    }
    
    public double calcularDuracionTotal() {
        double total = 0;
        for (Content contenido : historial) {
            total += contenido.calcularDuracionEnHoras();
        }
        return total;
    }
    
    public double calcularPromedioDuracion() {
        if (historial.isEmpty()) {
            return 0.0;
        }
        return calcularDuracionTotal() / historial.size();
    }
    
    public Map<String, Integer> contarPorTipo() {
        Map<String, Integer> conteo = new HashMap<>();
        for (Content contenido : historial) {
            String tipo;
            if (contenido instanceof Movie) {
                tipo = "Película";
            } else if (contenido instanceof Series) {
                tipo = "Serie";
            } else if (contenido instanceof Documentary) {
                tipo = "Documental";
            } else {
                tipo = "Desconocido";
            }
            conteo.put(tipo, conteo.getOrDefault(tipo, 0) + 1);
        }
        return conteo;
    }
    
    public Map<String, Integer> contarPorGenero() {
        Map<String, Integer> conteo = new HashMap<>();
        for (Content contenido : historial) {
            String genero = contenido.getGenre();
            conteo.put(genero, conteo.getOrDefault(genero, 0) + 1);
        }
        return conteo;
    }
    
    public int obtenerCantidadContenidos() {
        return historial.size();
    }
    
    public void mostrarResumen() {
        int peliculas = 0;
        int series = 0;
        int documentales = 0;
        
        for (Content contenido : historial) {
            if (contenido instanceof Movie) {
                peliculas++;
            } else if (contenido instanceof Series) {
                series++;
            } else if (contenido instanceof Documentary) {
                documentales++;
            }
        }
        
        System.out.println("\n=== Resumen del Catálogo ===");
        System.out.println("Total de contenidos: " + historial.size());
        System.out.println("Películas: " + peliculas);
        System.out.println("Series: " + series);
        System.out.println("Documentales: " + documentales);
        System.out.println("Capacidad utilizada: " + historial.size() + "/" + capacidadMaxima);
    }
}

