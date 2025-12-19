public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   STREAMFLIX - Catálogo de Contenidos");
        System.out.println("   Semana 04 - Herencia y Polimorfismo");
        System.out.println("========================================\n");
        
        Content[] contenidos = new Content[6];
        
        contenidos[0] = new Movie("MOV-001", "Inception", "Ciencia Ficción", 148, 2010, "Christopher Nolan", 8.8);
        contenidos[1] = new Series("SER-001", "Stranger Things", "Ciencia Ficción", 50, 2016, 4, 8, "The Duffer Brothers");
        contenidos[2] = new Documentary("DOC-001", "Nuestro Planeta", "Documental", 50, 2019, "David Attenborough", "Naturaleza", true);
        contenidos[3] = new Movie("MOV-002", "The Dark Knight", "Acción", 152, 2008, "Christopher Nolan", 9.0);
        contenidos[4] = new Series("SER-002", "Breaking Bad", "Drama", 47, 2008, 5, 13, "Vince Gilligan");
        contenidos[5] = new Documentary("DOC-002", "Cosmos", "Documental", 44, 2014, "Neil deGrasse Tyson", "Astronomía", false);
        
        System.out.println("=== Demostración de Polimorfismo ===\n");
        
        for (Content contenido : contenidos) {
            contenido.mostrarInfo();
            System.out.println("Duración en horas: " + String.format("%.2f", contenido.calcularDuracionEnHoras()));
            System.out.println();
        }
        
        System.out.println("=== Información por Tipo ===\n");
        
        int peliculas = 0;
        int series = 0;
        int documentales = 0;
        
        for (Content contenido : contenidos) {
            if (contenido instanceof Movie) {
                peliculas++;
                Movie movie = (Movie) contenido;
                System.out.println(movie.getTitle() + " - " + movie.obtenerTipo() + " - Rating: " + movie.getRating());
            } else if (contenido instanceof Series) {
                series++;
                Series serie = (Series) contenido;
                System.out.println(serie.getTitle() + " - " + serie.obtenerTipo() + " - " + serie.getNumberOfSeasons() + " temporadas");
            } else if (contenido instanceof Documentary) {
                documentales++;
                Documentary doc = (Documentary) contenido;
                System.out.println(doc.getTitle() + " - " + doc.obtenerTipo() + " - Tema: " + doc.getTopic());
            }
        }
        
        System.out.println("\n=== Resumen ===");
        System.out.println("Total de contenidos: " + contenidos.length);
        System.out.println("Películas: " + peliculas);
        System.out.println("Series: " + series);
        System.out.println("Documentales: " + documentales);
        System.out.println("========================================");
    }
}

