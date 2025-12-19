package com.streamflix;

import com.streamflix.modelo.Content;
import com.streamflix.modelo.Movie;
import com.streamflix.modelo.Series;
import com.streamflix.modelo.Documentary;
import com.streamflix.servicio.GestorCatalogo;
import com.streamflix.excepciones.ContenidoInvalidoException;
import com.streamflix.excepciones.ContenidoNoEncontradoException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static GestorCatalogo gestor = new GestorCatalogo(100);
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   STREAMFLIX - Gestión de Catálogo");
        System.out.println("   Semana 08 - Colecciones y Generics");
        System.out.println("========================================\n");
        
        inicializarDatos();
        
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1: agregarContenido(); break;
                case 2: buscarPorCodigo(); break;
                case 3: listarTodos(); break;
                case 4: filtrarPorPrecio(); break;
                case 5: filtrarPorAño(); break;
                case 6: filtrarPorGenero(); break;
                case 7: mostrarEstadisticas(); break;
                case 8: eliminarContenido(); break;
                case 9: demostrarHashMap(); break;
                case 0: System.out.println("\n¡Hasta luego!"); break;
                default: System.out.println("❌ Opción inválida");
            }
        } while (opcion != 0);
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Agregar contenido");
        System.out.println("2. Buscar por código");
        System.out.println("3. Listar todos los contenidos");
        System.out.println("4. Filtrar por precio");
        System.out.println("5. Filtrar por año");
        System.out.println("6. Filtrar por género");
        System.out.println("7. Ver estadísticas");
        System.out.println("8. Eliminar contenido");
        System.out.println("9. Demostrar HashMap O(1)");
        System.out.println("0. Salir");
    }
    
    private static void inicializarDatos() {
        try {
            gestor.agregarContenido(new Movie("MOV-001", "Inception", "Ciencia Ficción", 148, 2010, "Christopher Nolan", 8.8));
            gestor.agregarContenido(new Movie("MOV-002", "The Dark Knight", "Acción", 152, 2008, "Christopher Nolan", 9.0));
            gestor.agregarContenido(new Series("SER-001", "Stranger Things", "Ciencia Ficción", 50, 2016, 4, 8, "The Duffer Brothers"));
            gestor.agregarContenido(new Series("SER-002", "Breaking Bad", "Drama", 47, 2008, 5, 13, "Vince Gilligan"));
            gestor.agregarContenido(new Documentary("DOC-001", "Nuestro Planeta", "Documental", 50, 2019, "David Attenborough", "Naturaleza", true));
            gestor.agregarContenido(new Documentary("DOC-002", "Cosmos", "Documental", 44, 2014, "Neil deGrasse Tyson", "Astronomía", false));
            System.out.println("✅ Datos iniciales cargados\n");
        } catch (ContenidoInvalidoException e) {
            System.err.println("Error inicializando: " + e.getMessage());
        }
    }
    
    private static void agregarContenido() {
        System.out.println("\n--- Agregar Contenido ---");
        System.out.print("Tipo (1=Película, 2=Serie, 3=Documental): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Código (formato XXX-###): ");
        String codigo = scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        System.out.print("Duración (minutos): ");
        int duracion = scanner.nextInt();
        System.out.print("Año: ");
        int año = scanner.nextInt();
        scanner.nextLine();
        
        try {
            Content contenido = null;
            if (tipo == 1) {
                System.out.print("Director: ");
                String director = scanner.nextLine();
                System.out.print("Rating (0-10): ");
                double rating = scanner.nextDouble();
                scanner.nextLine();
                contenido = new Movie(codigo, titulo, genero, duracion, año, director, rating);
            } else if (tipo == 2) {
                System.out.print("Temporadas: ");
                int temporadas = scanner.nextInt();
                System.out.print("Episodios por temporada: ");
                int episodios = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Creador: ");
                String creador = scanner.nextLine();
                contenido = new Series(codigo, titulo, genero, duracion, año, temporadas, episodios, creador);
            } else if (tipo == 3) {
                System.out.print("Narrador: ");
                String narrador = scanner.nextLine();
                System.out.print("Tema: ");
                String tema = scanner.nextLine();
                System.out.print("¿Es original? (true/false): ");
                boolean original = scanner.nextBoolean();
                scanner.nextLine();
                contenido = new Documentary(codigo, titulo, genero, duracion, año, narrador, tema, original);
            }
            
            if (contenido != null) {
                gestor.agregarContenido(contenido);
            }
        } catch (ContenidoInvalidoException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void buscarPorCodigo() {
        System.out.println("\n--- Buscar por Código ---");
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        
        try {
            Content contenido = gestor.buscarContenido(codigo);
            System.out.println("✅ Encontrado:");
            contenido.mostrarInfo();
        } catch (ContenidoNoEncontradoException e) {
            System.err.println("❌ " + e.getMessage());
        }
    }
    
    private static void listarTodos() {
        System.out.println("\n--- Todos los Contenidos ---");
        List<Content> todos = gestor.obtenerTodos();
        if (todos.isEmpty()) {
            System.out.println("No hay contenidos en el catálogo");
            return;
        }
        for (Content contenido : todos) {
            System.out.println("- " + contenido.obtenerDescripcion());
            System.out.println("  Precio: $" + String.format("%.2f", contenido.calcularPrecio()));
        }
        System.out.println("\nTotal: " + todos.size() + " contenidos");
    }
    
    private static void filtrarPorPrecio() {
        System.out.println("\n--- Filtrar por Precio ---");
        System.out.print("Precio mínimo: $");
        double min = scanner.nextDouble();
        System.out.print("Precio máximo: $");
        double max = scanner.nextDouble();
        scanner.nextLine();
        
        List<Content> resultados = gestor.filtrarPorPrecio(min, max);
        System.out.println("\nResultados (" + resultados.size() + "):");
        for (Content contenido : resultados) {
            System.out.println("- " + contenido.getTitle() + " - $" + String.format("%.2f", contenido.calcularPrecio()));
        }
    }
    
    private static void filtrarPorAño() {
        System.out.println("\n--- Filtrar por Año ---");
        System.out.print("Año mínimo: ");
        int añoMin = scanner.nextInt();
        System.out.print("Año máximo: ");
        int añoMax = scanner.nextInt();
        scanner.nextLine();
        
        List<Content> resultados = gestor.filtrarPorAño(añoMin, añoMax);
        System.out.println("\nResultados (" + resultados.size() + "):");
        for (Content contenido : resultados) {
            System.out.println("- " + contenido.getTitle() + " (" + contenido.getReleaseYear() + ")");
        }
    }
    
    private static void filtrarPorGenero() {
        System.out.println("\n--- Filtrar por Género ---");
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        
        List<Content> resultados = gestor.buscarPorGenero(genero);
        System.out.println("\nResultados (" + resultados.size() + "):");
        for (Content contenido : resultados) {
            System.out.println("- " + contenido.obtenerDescripcion());
        }
    }
    
    private static void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS DEL CATÁLOGO ===");
        System.out.println("Total de contenidos: " + gestor.obtenerCantidadContenidos());
        System.out.println("Total de precios: $" + String.format("%.2f", gestor.calcularTotalPrecios()));
        System.out.println("Promedio de precios: $" + String.format("%.2f", gestor.calcularPromedioPrecio()));
        System.out.println("Duración total: " + String.format("%.2f", gestor.calcularDuracionTotal()) + " horas");
        System.out.println("Promedio de duración: " + String.format("%.2f", gestor.calcularPromedioDuracion()) + " horas");
        
        Content masCaro = gestor.obtenerContenidoMasCaro();
        if (masCaro != null) {
            System.out.println("Más caro: " + masCaro.getTitle() + " - $" + String.format("%.2f", masCaro.calcularPrecio()));
        }
        
        Content masBarato = gestor.obtenerContenidoMasBarato();
        if (masBarato != null) {
            System.out.println("Más barato: " + masBarato.getTitle() + " - $" + String.format("%.2f", masBarato.calcularPrecio()));
        }
        
        System.out.println("\nConteo por tipo:");
        Map<String, Integer> conteoTipo = gestor.contarPorTipo();
        for (Map.Entry<String, Integer> entry : conteoTipo.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        
        System.out.println("\nConteo por género:");
        Map<String, Integer> conteoGenero = gestor.contarPorGenero();
        for (Map.Entry<String, Integer> entry : conteoGenero.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }
    
    private static void eliminarContenido() {
        System.out.println("\n--- Eliminar Contenido ---");
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        
        try {
            Content eliminado = gestor.eliminarContenido(codigo);
            System.out.println("✅ Eliminado: " + eliminado.obtenerDescripcion());
        } catch (ContenidoNoEncontradoException e) {
            System.err.println("❌ " + e.getMessage());
        }
    }
    
    private static void demostrarHashMap() {
        System.out.println("\n=== DEMOSTRACIÓN HASHMAP O(1) ===");
        System.out.println("Búsqueda directa por código usando HashMap:");
        
        String[] codigos = {"MOV-001", "SER-001", "DOC-001", "MOV-999"};
        
        for (String codigo : codigos) {
            long inicio = System.nanoTime();
            boolean existe = gestor.existeContenido(codigo);
            long fin = System.nanoTime();
            long tiempo = fin - inicio;
            
            System.out.println("Código: " + codigo);
            System.out.println("  Existe: " + existe);
            System.out.println("  Tiempo: " + tiempo + " nanosegundos (O(1))");
            
            if (existe) {
                try {
                    Content contenido = gestor.buscarContenido(codigo);
                    System.out.println("  Contenido: " + contenido.getTitle());
                } catch (ContenidoNoEncontradoException e) {
                }
            }
        }
    }
}

