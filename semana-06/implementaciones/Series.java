import java.util.ArrayList;

public class Series extends Content implements Reproducible, Calificable, Recomendable {
    private int numberOfSeasons;
    private int episodesPerSeason;
    private String creator;
    private boolean reproduciendo;
    private int tiempoActual;
    private ArrayList<Double> calificaciones;
    
    public Series(String contentCode, String title, String genre, int duration, int releaseYear, int numberOfSeasons, int episodesPerSeason, String creator) {
        super(contentCode, title, genre, duration, releaseYear);
        this.numberOfSeasons = numberOfSeasons;
        this.episodesPerSeason = episodesPerSeason;
        this.creator = creator;
        this.reproduciendo = false;
        this.tiempoActual = 0;
        this.calificaciones = new ArrayList<>();
    }
    
    public Series(String title, String genre, int duration, int releaseYear, int numberOfSeasons, int episodesPerSeason, String creator) {
        super(title, genre, duration, releaseYear);
        this.numberOfSeasons = numberOfSeasons;
        this.episodesPerSeason = episodesPerSeason;
        this.creator = creator;
        this.reproduciendo = false;
        this.tiempoActual = 0;
        this.calificaciones = new ArrayList<>();
    }
    
    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }
    
    public void setNumberOfSeasons(int numberOfSeasons) {
        if (numberOfSeasons <= 0) {
            throw new IllegalArgumentException("El número de temporadas debe ser mayor a cero");
        }
        this.numberOfSeasons = numberOfSeasons;
    }
    
    public int getEpisodesPerSeason() {
        return episodesPerSeason;
    }
    
    public void setEpisodesPerSeason(int episodesPerSeason) {
        if (episodesPerSeason <= 0) {
            throw new IllegalArgumentException("El número de episodios por temporada debe ser mayor a cero");
        }
        this.episodesPerSeason = episodesPerSeason;
    }
    
    public String getCreator() {
        return creator;
    }
    
    public void setCreator(String creator) {
        if (creator == null || creator.trim().isEmpty()) {
            throw new IllegalArgumentException("El creador no puede estar vacío");
        }
        this.creator = creator;
    }
    
    @Override
    public void mostrarInfo() {
        System.out.println("=== Información de Serie ===");
        System.out.println("Código: " + contentCode);
        System.out.println("Título: " + title);
        System.out.println("Género: " + genre);
        System.out.println("Duración por episodio: " + duration + " minutos");
        System.out.println("Año de estreno: " + releaseYear);
        System.out.println("Temporadas: " + numberOfSeasons);
        System.out.println("Episodios por temporada: " + episodesPerSeason);
        System.out.println("Total de episodios: " + (numberOfSeasons * episodesPerSeason));
        System.out.println("Creador: " + creator);
        System.out.println("Duración total aproximada: " + (numberOfSeasons * episodesPerSeason * duration) + " minutos");
        System.out.println("-----------------------------------");
    }
    
    @Override
    public double calcularPrecio() {
        double precioBase = 3000.0;
        double precioPorTemporada = numberOfSeasons * 2000;
        return precioBase + precioPorTemporada;
    }
    
    @Override
    public String obtenerDescripcion() {
        return "Serie: " + title + " creada por " + creator + " (" + numberOfSeasons + " temporadas)";
    }
    
    @Override
    public double calcularDuracionEnHoras() {
        int totalMinutes = numberOfSeasons * episodesPerSeason * duration;
        return totalMinutes / 60.0;
    }
    
    @Override
    public void iniciarReproduccion() {
        reproduciendo = true;
        tiempoActual = 0;
        System.out.println("Reproduciendo serie: " + title + " - Episodio 1");
    }
    
    @Override
    public void pausarReproduccion() {
        if (reproduciendo) {
            reproduciendo = false;
            System.out.println("Pausado: " + title + " en minuto " + tiempoActual);
        }
    }
    
    @Override
    public void detenerReproduccion() {
        reproduciendo = false;
        tiempoActual = 0;
        System.out.println("Detenido: " + title);
    }
    
    @Override
    public boolean estaReproduciendo() {
        return reproduciendo;
    }
    
    @Override
    public int obtenerTiempoActual() {
        return tiempoActual;
    }
    
    @Override
    public void agregarCalificacion(double calificacion) {
        if (calificacion < 0 || calificacion > 10) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 10");
        }
        calificaciones.add(calificacion);
    }
    
    @Override
    public double obtenerPromedioCalificaciones() {
        if (calificaciones.isEmpty()) {
            return 0.0;
        }
        double suma = 0;
        for (Double cal : calificaciones) {
            suma += cal;
        }
        return suma / calificaciones.size();
    }
    
    @Override
    public int obtenerNumeroCalificaciones() {
        return calificaciones.size();
    }
    
    @Override
    public boolean tieneCalificaciones() {
        return !calificaciones.isEmpty();
    }
    
    @Override
    public boolean esRecomendablePara(String generoPreferido) {
        return genre.equalsIgnoreCase(generoPreferido) && numberOfSeasons >= 3;
    }
    
    @Override
    public double calcularPuntuacionRecomendacion() {
        double puntuacion = 5.0;
        if (numberOfSeasons >= 3) {
            puntuacion += 2.0;
        }
        if (numberOfSeasons >= 5) {
            puntuacion += 1.0;
        }
        if (releaseYear >= 2020) {
            puntuacion += 0.5;
        }
        return Math.min(puntuacion, 10.0);
    }
    
    @Override
    public String obtenerRazonRecomendacion() {
        if (numberOfSeasons >= 5) {
            return "Serie consolidada con " + numberOfSeasons + " temporadas";
        }
        return "Serie con " + numberOfSeasons + " temporadas";
    }
    
    @Override
    public boolean esContenidoDestacado() {
        return numberOfSeasons >= 4;
    }
    
    public String obtenerTipo() {
        return "Serie";
    }
}

