public class Content {
    protected String contentCode;
    protected String title;
    protected String genre;
    protected int duration;
    protected int releaseYear;
    
    public Content(String contentCode, String title, String genre, int duration, int releaseYear) {
        this.contentCode = contentCode;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.releaseYear = releaseYear;
    }
    
    public Content(String title, String genre, int duration, int releaseYear) {
        this("CNT-000", title, genre, duration, releaseYear);
    }
    
    public String getContentCode() {
        return contentCode;
    }
    
    public void setContentCode(String contentCode) {
        if (contentCode == null || contentCode.length() < 3) {
            throw new IllegalArgumentException("El código debe tener al menos 3 caracteres");
        }
        this.contentCode = contentCode;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        this.title = title;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("El género no puede estar vacío");
        }
        this.genre = genre;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public void setDuration(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor a cero");
        }
        this.duration = duration;
    }
    
    public int getReleaseYear() {
        return releaseYear;
    }
    
    public void setReleaseYear(int releaseYear) {
        if (releaseYear < 1900 || releaseYear > 2100) {
            throw new IllegalArgumentException("El año debe estar entre 1900 y 2100");
        }
        this.releaseYear = releaseYear;
    }
    
    public void mostrarInfo() {
        System.out.println("=== Información del Contenido ===");
        System.out.println("Código: " + contentCode);
        System.out.println("Título: " + title);
        System.out.println("Género: " + genre);
        System.out.println("Duración: " + duration + " minutos");
        System.out.println("Año: " + releaseYear);
    }
    
    public double calcularDuracionEnHoras() {
        return duration / 60.0;
    }
}

