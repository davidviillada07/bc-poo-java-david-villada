public class Series extends Content {
    private int numberOfSeasons;
    private int episodesPerSeason;
    private String creator;
    
    public Series(String contentCode, String title, String genre, int duration, int releaseYear, int numberOfSeasons, int episodesPerSeason, String creator) {
        super(contentCode, title, genre, duration, releaseYear);
        this.numberOfSeasons = numberOfSeasons;
        this.episodesPerSeason = episodesPerSeason;
        this.creator = creator;
    }
    
    public Series(String title, String genre, int duration, int releaseYear, int numberOfSeasons, int episodesPerSeason, String creator) {
        super(title, genre, duration, releaseYear);
        this.numberOfSeasons = numberOfSeasons;
        this.episodesPerSeason = episodesPerSeason;
        this.creator = creator;
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
    public double calcularDuracionEnHoras() {
        int totalMinutes = numberOfSeasons * episodesPerSeason * duration;
        return totalMinutes / 60.0;
    }
    
    public String obtenerTipo() {
        return "Serie";
    }
}

