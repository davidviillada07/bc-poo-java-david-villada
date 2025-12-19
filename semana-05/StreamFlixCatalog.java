import java.util.ArrayList;

public class StreamFlixCatalog {
    private String catalogName;
    private ArrayList<Content> contents;
    
    public StreamFlixCatalog(String catalogName) {
        this.catalogName = catalogName;
        this.contents = new ArrayList<>();
    }
    
    public StreamFlixCatalog() {
        this("StreamFlix");
    }
    
    public String getCatalogName() {
        return catalogName;
    }
    
    public void setCatalogName(String catalogName) {
        if (catalogName == null || catalogName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del catálogo no puede estar vacío");
        }
        this.catalogName = catalogName;
    }
    
    public void agregarContenido(Content contenido) {
        if (contenido == null) {
            throw new IllegalArgumentException("El contenido no puede ser nulo");
        }
        contents.add(contenido);
        System.out.println("Contenido agregado: " + contenido.obtenerDescripcion());
    }
    
    public Content buscarContenido(String codigo) {
        for (Content contenido : contents) {
            if (contenido.getContentCode().equals(codigo)) {
                return contenido;
            }
        }
        return null;
    }
    
    public ArrayList<Content> buscarContenido(String campo, String valor) {
        ArrayList<Content> resultados = new ArrayList<>();
        for (Content contenido : contents) {
            if (campo.equalsIgnoreCase("titulo") && contenido.getTitle().toLowerCase().contains(valor.toLowerCase())) {
                resultados.add(contenido);
            } else if (campo.equalsIgnoreCase("genero") && contenido.getGenre().equalsIgnoreCase(valor)) {
                resultados.add(contenido);
            }
        }
        return resultados;
    }
    
    public ArrayList<Content> buscarContenido(int añoInicio, int añoFin) {
        ArrayList<Content> resultados = new ArrayList<>();
        for (Content contenido : contents) {
            if (contenido.getReleaseYear() >= añoInicio && contenido.getReleaseYear() <= añoFin) {
                resultados.add(contenido);
            }
        }
        return resultados;
    }
    
    public void procesarContenido(Content contenido) {
        double precio = contenido.calcularPrecio();
        System.out.println("Procesando: " + contenido.getTitle());
        System.out.println("Descripción: " + contenido.obtenerDescripcion());
        System.out.println("Precio estimado: $" + String.format("%.2f", precio));
    }
    
    public void procesarCatalogoCompleto() {
        System.out.println("\n=== CATÁLOGO COMPLETO DE " + catalogName.toUpperCase() + " ===");
        double totalPrecio = 0;
        for (Content contenido : contents) {
            procesarContenido(contenido);
            totalPrecio += contenido.calcularPrecio();
            System.out.println("---");
        }
        System.out.println("TOTAL ESTIMADO DEL CATÁLOGO: $" + String.format("%.2f", totalPrecio));
    }
    
    public void generarReporte() {
        System.out.println("\n=== REPORTE DE CONTENIDOS ===");
        int peliculas = 0;
        int series = 0;
        int documentales = 0;
        
        for (Content contenido : contents) {
            System.out.println(contenido.obtenerDescripcion());
            System.out.println("Duración: " + String.format("%.2f", contenido.calcularDuracionEnHoras()) + " horas");
            System.out.println("Precio: $" + String.format("%.2f", contenido.calcularPrecio()));
            
            if (contenido instanceof Movie) {
                peliculas++;
            } else if (contenido instanceof Series) {
                series++;
            } else if (contenido instanceof Documentary) {
                documentales++;
            }
            System.out.println();
        }
        
        System.out.println("=== RESUMEN ===");
        System.out.println("Total de contenidos: " + contents.size());
        System.out.println("Películas: " + peliculas);
        System.out.println("Series: " + series);
        System.out.println("Documentales: " + documentales);
    }
    
    public int contarContenidos() {
        return contents.size();
    }
}

