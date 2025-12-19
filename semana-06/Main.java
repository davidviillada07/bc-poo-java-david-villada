import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   STREAMFLIX - Plataforma de Streaming");
        System.out.println("   Semana 06 - Abstracción e Interfaces");
        System.out.println("========================================\n");
        
        System.out.println("=== 1. POLIMORFISMO CON CLASE ABSTRACTA ===\n");
        
        Content[] contenidos = new Content[4];
        
        contenidos[0] = new Movie("MOV-001", "Inception", "Ciencia Ficción", 148, 2010, "Christopher Nolan", 8.8);
        contenidos[1] = new Series("SER-001", "Stranger Things", "Ciencia Ficción", 50, 2016, 4, 8, "The Duffer Brothers");
        contenidos[2] = new Documentary("DOC-001", "Nuestro Planeta", "Documental", 50, 2019, "David Attenborough", "Naturaleza", true);
        contenidos[3] = new Movie("MOV-002", "The Dark Knight", "Acción", 152, 2008, "Christopher Nolan", 9.0);
        
        for (Content contenido : contenidos) {
            contenido.mostrarInfo();
            System.out.println("Descripción: " + contenido.obtenerDescripcion());
            System.out.println("Precio: $" + String.format("%.2f", contenido.calcularPrecio()));
            System.out.println("Duración: " + String.format("%.2f", contenido.calcularDuracionEnHoras()) + " horas");
            System.out.println();
        }
        
        System.out.println("\n=== 2. USO DE INTERFACES ===\n");
        
        System.out.println("--- 2.1. Interface Reproducible ---");
        Movie pelicula = new Movie("MOV-003", "Interstellar", "Ciencia Ficción", 169, 2014, "Christopher Nolan", 8.6);
        Series serie = new Series("SER-002", "Breaking Bad", "Drama", 47, 2008, 5, 13, "Vince Gilligan");
        
        Reproducible contenidoReproducible1 = pelicula;
        Reproducible contenidoReproducible2 = serie;
        
        contenidoReproducible1.iniciarReproduccion();
        System.out.println("Estado: " + (contenidoReproducible1.estaReproduciendo() ? "Reproduciendo" : "Detenido"));
        contenidoReproducible1.pausarReproduccion();
        contenidoReproducible1.detenerReproduccion();
        
        System.out.println();
        contenidoReproducible2.iniciarReproduccion();
        System.out.println("Estado: " + (contenidoReproducible2.estaReproduciendo() ? "Reproduciendo" : "Detenido"));
        contenidoReproducible2.detenerReproduccion();
        
        System.out.println("\n--- 2.2. Interface Calificable ---");
        Calificable contenidoCalificable1 = pelicula;
        Calificable contenidoCalificable2 = new Documentary("DOC-002", "Cosmos", "Documental", 44, 2014, "Neil deGrasse Tyson", "Astronomía", false);
        
        contenidoCalificable1.agregarCalificacion(9.0);
        contenidoCalificable1.agregarCalificacion(8.5);
        contenidoCalificable1.agregarCalificacion(9.5);
        
        System.out.println("Calificaciones de " + pelicula.getTitle() + ":");
        System.out.println("Número de calificaciones: " + contenidoCalificable1.obtenerNumeroCalificaciones());
        System.out.println("Promedio: " + String.format("%.2f", contenidoCalificable1.obtenerPromedioCalificaciones()));
        
        contenidoCalificable2.agregarCalificacion(8.0);
        contenidoCalificable2.agregarCalificacion(7.5);
        
        System.out.println("\nCalificaciones de " + ((Documentary) contenidoCalificable2).getTitle() + ":");
        System.out.println("Número de calificaciones: " + contenidoCalificable2.obtenerNumeroCalificaciones());
        System.out.println("Promedio: " + String.format("%.2f", contenidoCalificable2.obtenerPromedioCalificaciones()));
        
        System.out.println("\n--- 2.3. Interface Recomendable ---");
        Recomendable contenidoRecomendable1 = pelicula;
        Recomendable contenidoRecomendable2 = serie;
        Documentary doc3 = new Documentary("DOC-003", "Planeta Tierra", "Documental", 50, 2006, "David Attenborough", "Naturaleza", true);
        Recomendable contenidoRecomendable3 = doc3;
        
        String generoPreferido = "Ciencia Ficción";
        
        System.out.println("¿Es " + pelicula.getTitle() + " recomendable para " + generoPreferido + "?");
        System.out.println(contenidoRecomendable1.esRecomendablePara(generoPreferido) ? "Sí" : "No");
        System.out.println("Puntuación de recomendación: " + String.format("%.2f", contenidoRecomendable1.calcularPuntuacionRecomendacion()));
        System.out.println("Razón: " + contenidoRecomendable1.obtenerRazonRecomendacion());
        System.out.println("¿Es destacado? " + (contenidoRecomendable1.esContenidoDestacado() ? "Sí" : "No"));
        
        System.out.println("\n¿Es " + serie.getTitle() + " recomendable para " + generoPreferido + "?");
        System.out.println(contenidoRecomendable2.esRecomendablePara(generoPreferido) ? "Sí" : "No");
        System.out.println("Puntuación de recomendación: " + String.format("%.2f", contenidoRecomendable2.calcularPuntuacionRecomendacion()));
        System.out.println("Razón: " + contenidoRecomendable2.obtenerRazonRecomendacion());
        
        System.out.println("\n--- 2.4. Múltiple Implementación ---");
        Movie peliculaCompleta = new Movie("MOV-004", "The Matrix", "Ciencia Ficción", 136, 1999, "The Wachowskis", 8.7);
        
        System.out.println("Película: " + peliculaCompleta.getTitle());
        System.out.println("Implementa Reproducible: " + (peliculaCompleta instanceof Reproducible));
        System.out.println("Implementa Calificable: " + (peliculaCompleta instanceof Calificable));
        System.out.println("Implementa Recomendable: " + (peliculaCompleta instanceof Recomendable));
        
        Reproducible repro = peliculaCompleta;
        Calificable calif = peliculaCompleta;
        Recomendable recom = peliculaCompleta;
        
        repro.iniciarReproduccion();
        calif.agregarCalificacion(9.0);
        calif.agregarCalificacion(8.5);
        System.out.println("Promedio de calificaciones: " + String.format("%.2f", calif.obtenerPromedioCalificaciones()));
        System.out.println("Puntuación de recomendación: " + String.format("%.2f", recom.calcularPuntuacionRecomendacion()));
        repro.detenerReproduccion();
        
        System.out.println("\n=== 3. DEMOSTRACIÓN CON ARRAYLIST POLIMÓRFICO ===\n");
        
        ArrayList<Content> catalogo = new ArrayList<>();
        catalogo.add(pelicula);
        catalogo.add(serie);
        catalogo.add(doc3);
        catalogo.add(peliculaCompleta);
        
        System.out.println("Total de contenidos en catálogo: " + catalogo.size());
        System.out.println("\nResumen del catálogo:");
        for (Content c : catalogo) {
            System.out.println("- " + c.obtenerDescripcion());
            System.out.println("  Precio: $" + String.format("%.2f", c.calcularPrecio()));
            if (c instanceof Recomendable) {
                Recomendable r = (Recomendable) c;
                System.out.println("  Puntuación recomendación: " + String.format("%.2f", r.calcularPuntuacionRecomendacion()));
            }
        }
        
        System.out.println("\n========================================");
    }
}

