package com.streamflix;

import com.streamflix.modelo.Content;
import com.streamflix.modelo.Movie;
import com.streamflix.modelo.Series;
import com.streamflix.modelo.Documentary;
import com.streamflix.servicio.GestorCatalogo;
import com.streamflix.excepciones.ContenidoInvalidoException;
import com.streamflix.excepciones.ContenidoNoEncontradoException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   STREAMFLIX - Gestión de Catálogo");
        System.out.println("   Semana 07 - Paquetes y Excepciones");
        System.out.println("========================================\n");
        
        GestorCatalogo gestor = new GestorCatalogo(50);
        
        System.out.println("=== CASO 1: OPERACIÓN EXITOSA ===\n");
        try {
            Movie pelicula1 = new Movie("MOV-001", "Inception", "Ciencia Ficción", 148, 2010, "Christopher Nolan", 8.8);
            gestor.agregarContenido(pelicula1);
            
            Series serie1 = new Series("SER-001", "Stranger Things", "Ciencia Ficción", 50, 2016, 4, 8, "The Duffer Brothers");
            gestor.agregarContenido(serie1);
            
            Documentary doc1 = new Documentary("DOC-001", "Nuestro Planeta", "Documental", 50, 2019, "David Attenborough", "Naturaleza", true);
            gestor.agregarContenido(doc1);
            
            System.out.println("✅ Contenidos agregados exitosamente");
        } catch (ContenidoInvalidoException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        
        System.out.println("\n=== CASO 2: CÓDIGO INVÁLIDO ===\n");
        try {
            Movie peliculaInvalida = new Movie("ABC", "Test", "Acción", 120, 2020, "Director", 7.5);
            gestor.agregarContenido(peliculaInvalida);
        } catch (ContenidoInvalidoException e) {
            System.err.println("❌ Error esperado: " + e.getMessage());
        }
        
        System.out.println("\n=== CASO 3: CONTENIDO NULO ===\n");
        try {
            gestor.agregarContenido(null);
        } catch (ContenidoInvalidoException e) {
            System.err.println("❌ Error esperado: " + e.getMessage());
        }
        
        System.out.println("\n=== CASO 4: CÓDIGO DUPLICADO ===\n");
        try {
            Movie peliculaDuplicada = new Movie("MOV-001", "Otra Película", "Drama", 100, 2021, "Otro Director", 6.5);
            gestor.agregarContenido(peliculaDuplicada);
        } catch (ContenidoInvalidoException e) {
            System.err.println("❌ Error esperado: " + e.getMessage());
        }
        
        System.out.println("\n=== CASO 5: BÚSQUEDA EXITOSA ===\n");
        try {
            Content encontrado = gestor.buscarContenido("MOV-001");
            System.out.println("✅ Contenido encontrado: " + encontrado.obtenerDescripcion());
            encontrado.mostrarInfo();
        } catch (ContenidoNoEncontradoException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        
        System.out.println("\n=== CASO 6: CONTENIDO NO ENCONTRADO ===\n");
        try {
            Content noEncontrado = gestor.buscarContenido("MOV-999");
        } catch (ContenidoNoEncontradoException e) {
            System.err.println("❌ Error esperado: " + e.getMessage());
        }
        
        System.out.println("\n=== CASO 7: BÚSQUEDA CON CÓDIGO VACÍO ===\n");
        try {
            gestor.buscarContenido("");
        } catch (ContenidoNoEncontradoException e) {
            System.err.println("❌ Error esperado: " + e.getMessage());
        }
        
        System.out.println("\n=== CASO 8: RECUPERACIÓN DESPUÉS DE ERROR ===\n");
        try {
            Movie pelicula2 = new Movie("MOV-002", "The Dark Knight", "Acción", 152, 2008, "Christopher Nolan", 9.0);
            gestor.agregarContenido(pelicula2);
            System.out.println("✅ Recuperación exitosa: Contenido agregado después de errores");
        } catch (ContenidoInvalidoException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        
        System.out.println("\n=== CASO 9: PROCESAMIENTO CON TRY-CATCH ===\n");
        gestor.procesarContenido("SER-001");
        gestor.procesarContenido("NO-EXISTE");
        
        System.out.println("\n=== CASO 10: FINALLY PARA LIMPIEZA ===\n");
        ArrayList<Content> recursos = new ArrayList<>();
        try {
            recursos.add(new Movie("MOV-003", "Interstellar", "Ciencia Ficción", 169, 2014, "Christopher Nolan", 8.6));
            recursos.add(new Series("SER-002", "Breaking Bad", "Drama", 47, 2008, 5, 13, "Vince Gilligan"));
            
            System.out.println("✅ Recursos creados: " + recursos.size());
            for (Content c : recursos) {
                System.out.println("- " + c.obtenerDescripcion());
            }
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Error creando recursos: " + e.getMessage());
        } finally {
            System.out.println("🧹 Limpieza: Recursos procesados correctamente");
            recursos.clear();
            System.out.println("✅ Recursos liberados");
        }
        
        gestor.mostrarResumen();
        
        System.out.println("\n========================================");
        System.out.println("✅ Semana 07 completada: Paquetes y Excepciones");
        System.out.println("========================================");
    }
}

