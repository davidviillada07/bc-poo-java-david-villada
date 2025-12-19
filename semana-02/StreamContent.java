public class StreamContent {
    private String contentCode;
    private String title;
    private String genre;
    
    public StreamContent(String contentCode, String title, String genre) {
        this.contentCode = contentCode;
        this.title = title;
        this.genre = genre;
    }
    
    public String getContentCode() {
        return contentCode;
    }
    
    public void setContentCode(String contentCode) {
        this.contentCode = contentCode;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public void showInfo() {
        System.out.println("=== Información del Contenido ===");
        System.out.println("Código: " + contentCode);
        System.out.println("Título: " + title);
        System.out.println("Género: " + genre);
        System.out.println("-----------------------------------");
    }
}

