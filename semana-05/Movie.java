public class Movie extends Content {
    private String director;
    private double rating;
    
    public Movie(String contentCode, String title, String genre, int duration, int releaseYear, String director, double rating) {
        super(contentCode, title, genre, duration, releaseYear);
        this.director = director;
        this.rating = rating;
    }
    
    public Movie(String title, String genre, int duration, int releaseYear, String director, double rating) {
        super(title, genre, duration, releaseYear);
        this.director = director;
        this.rating = rating;
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
    
    public String obtenerTipo() {
        return "Película";
    }
}

