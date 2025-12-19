public class StreamContent {
    private String contentCode;
    private String title;
    private String genre;
    
    public StreamContent(String contentCode, String title, String genre) {
        setContentCode(contentCode);
        setTitle(title);
        setGenre(genre);
    }
    
    public StreamContent(String title, String genre) {
        this("STR-000", title, genre);
    }
    
    public StreamContent(String title) {
        this(title, "Sin género");
    }
    
    public String getContentCode() {
        return contentCode;
    }
    
    public void setContentCode(String contentCode) {
        if (!validarCodigo(contentCode)) {
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
    
    private boolean validarCodigo(String codigo) {
        return codigo != null && codigo.length() >= 3;
    }
    
    public void showInfo() {
        System.out.println("=== Información del Contenido ===");
        System.out.println("Código: " + contentCode);
        System.out.println("Título: " + title);
        System.out.println("Género: " + genre);
        System.out.println("-----------------------------------");
    }
}

