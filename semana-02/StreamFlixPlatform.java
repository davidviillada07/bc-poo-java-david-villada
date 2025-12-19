import java.util.ArrayList;

public class StreamFlixPlatform {
    private String platformName;
    private ArrayList<PlaybackSession> sessions;
    
    public StreamFlixPlatform(String platformName) {
        this.platformName = platformName;
        this.sessions = new ArrayList<>();
    }
    
    public String getPlatformName() {
        return platformName;
    }
    
    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
    
    public void agregarSesion(PlaybackSession session) {
        sessions.add(session);
    }
    
    public void mostrarTodasSesiones() {
        System.out.println("\n=== Sesiones Activas en " + platformName + " ===");
        for (PlaybackSession session : sessions) {
            System.out.println(session.obtenerResumen());
        }
    }
    
    public int contarSesiones() {
        return sessions.size();
    }
    
    public void mostrarResumen() {
        System.out.println("\n=== Resumen de " + platformName + " ===");
        System.out.println("Total de sesiones activas: " + contarSesiones());
    }
}

