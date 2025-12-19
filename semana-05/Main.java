import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   STREAMFLIX - Catálogo de Contenidos");
        System.out.println("   Semana 05 - Polimorfismo");
        System.out.println("========================================\n");
        
        StreamFlixCatalog catalogo = new StreamFlixCatalog("StreamFlix Premium");
        
        Movie pelicula1 = new Movie("MOV-001", "Inception", "Ciencia Ficción", 148, 2010, "Christopher Nolan", 8.8);
        Movie pelicula2 = new Movie("MOV-002", "The Dark Knight", "Acción", 152, 2008, "Christopher Nolan", 9.0);
        
        Series serie1 = new Series("SER-001", "Stranger Things", "Ciencia Ficción", 50, 2016, 4, 8, "The Duffer Brothers");
        Series serie2 = new Series("SER-002", "Breaking Bad", "Drama", 47, 2008, 5, 13, "Vince Gilligan");
        
        Documentary doc1 = new Documentary("DOC-001", "Nuestro Planeta", "Documental", 50, 2019, "David Attenborough", "Naturaleza", true);
        Documentary doc2 = new Documentary("DOC-002", "Cosmos", "Documental", 44, 2014, "Neil deGrasse Tyson", "Astronomía", false);
        
        catalogo.agregarContenido(pelicula1);
        catalogo.agregarContenido(pelicula2);
        catalogo.agregarContenido(serie1);
        catalogo.agregarContenido(serie2);
        catalogo.agregarContenido(doc1);
        catalogo.agregarContenido(doc2);
        
        catalogo.procesarCatalogoCompleto();
        
        catalogo.generarReporte();
        
        System.out.println("\n=== DEMOSTRACIÓN DE SOBRECARGA ===");
        
        Content encontrado1 = catalogo.buscarContenido("MOV-001");
        if (encontrado1 != null) {
            System.out.println("Búsqueda por código: " + encontrado1.getTitle());
        }
        
        ArrayList<Content> encontrados2 = catalogo.buscarContenido("genero", "Ciencia Ficción");
        System.out.println("\nBúsqueda por género 'Ciencia Ficción': " + encontrados2.size() + " resultados");
        for (Content c : encontrados2) {
            System.out.println("- " + c.getTitle());
        }
        
        ArrayList<Content> encontrados3 = catalogo.buscarContenido(2010, 2020);
        System.out.println("\nBúsqueda por rango de años (2010-2020): " + encontrados3.size() + " resultados");
        for (Content c : encontrados3) {
            System.out.println("- " + c.getTitle() + " (" + c.getReleaseYear() + ")");
        }
        
        System.out.println("\n=== DEMOSTRACIÓN DE POLIMORFISMO DINÁMICO ===");
        
        ArrayList<Content> contenidos = new ArrayList<>();
        contenidos.add(pelicula1);
        contenidos.add(serie1);
        contenidos.add(doc1);
        contenidos.add(pelicula2);
        contenidos.add(serie2);
        contenidos.add(doc2);
        
        for (Content contenido : contenidos) {
            System.out.println(contenido.obtenerDescripcion());
            System.out.println("Duración: " + String.format("%.2f", contenido.calcularDuracionEnHoras()) + " horas");
            System.out.println("Precio: $" + String.format("%.2f", contenido.calcularPrecio()));
            System.out.println("Tipo real: " + contenido.getClass().getSimpleName());
            System.out.println("---");
        }
        
        System.out.println("\n=== DEMOSTRACIÓN DE MÉTODOS POLIMÓRFICOS ===");
        
        procesarContenidoPolimorfico(pelicula1);
        procesarContenidoPolimorfico(serie1);
        procesarContenidoPolimorfico(doc1);
        
        System.out.println("\n========================================");
    }
    
    public static void procesarContenidoPolimorfico(Content contenido) {
        System.out.println("Procesando contenido polimórfico:");
        contenido.mostrarInfo();
        System.out.println("Descripción: " + contenido.obtenerDescripcion());
        System.out.println("Precio calculado: $" + String.format("%.2f", contenido.calcularPrecio()));
        System.out.println();
    }
}

