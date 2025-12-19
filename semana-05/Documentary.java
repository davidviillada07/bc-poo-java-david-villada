public class Documentary extends Content {
    private String narrator;
    private String topic;
    private boolean isOriginal;
    
    public Documentary(String contentCode, String title, String genre, int duration, int releaseYear, String narrator, String topic, boolean isOriginal) {
        super(contentCode, title, genre, duration, releaseYear);
        this.narrator = narrator;
        this.topic = topic;
        this.isOriginal = isOriginal;
    }
    
    public Documentary(String title, String genre, int duration, int releaseYear, String narrator, String topic, boolean isOriginal) {
        super(title, genre, duration, releaseYear);
        this.narrator = narrator;
        this.topic = topic;
        this.isOriginal = isOriginal;
    }
    
    public String getNarrator() {
        return narrator;
    }
    
    public void setNarrator(String narrator) {
        if (narrator == null || narrator.trim().isEmpty()) {
            throw new IllegalArgumentException("El narrador no puede estar vacío");
        }
        this.narrator = narrator;
    }
    
    public String getTopic() {
        return topic;
    }
    
    public void setTopic(String topic) {
        if (topic == null || topic.trim().isEmpty()) {
            throw new IllegalArgumentException("El tema no puede estar vacío");
        }
        this.topic = topic;
    }
    
    public boolean isOriginal() {
        return isOriginal;
    }
    
    public void setOriginal(boolean isOriginal) {
        this.isOriginal = isOriginal;
    }
    
    @Override
    public void mostrarInfo() {
        System.out.println("=== Información de Documental ===");
        System.out.println("Código: " + contentCode);
        System.out.println("Título: " + title);
        System.out.println("Género: " + genre);
        System.out.println("Duración: " + duration + " minutos (" + String.format("%.1f", calcularDuracionEnHoras()) + " horas)");
        System.out.println("Año: " + releaseYear);
        System.out.println("Narrador: " + narrator);
        System.out.println("Tema: " + topic);
        System.out.println("Contenido original StreamFlix: " + (isOriginal ? "Sí" : "No"));
        System.out.println("-----------------------------------");
    }
    
    @Override
    public double calcularPrecio() {
        double precioBase = 2000.0;
        if (isOriginal) {
            return precioBase * 1.5;
        }
        return precioBase;
    }
    
    @Override
    public String obtenerDescripcion() {
        String original = isOriginal ? " (Original StreamFlix)" : "";
        return "Documental: " + title + " narrado por " + narrator + " - Tema: " + topic + original;
    }
    
    public String obtenerTipo() {
        return "Documental";
    }
}

