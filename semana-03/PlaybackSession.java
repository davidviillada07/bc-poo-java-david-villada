public class PlaybackSession {
    private String sessionId;
    private Subscriber subscriber;
    private StreamContent content;
    private int currentTime;
    private int totalDuration;
    
    public PlaybackSession(String sessionId, Subscriber subscriber, StreamContent content, int currentTime, int totalDuration) {
        setSessionId(sessionId);
        setSubscriber(subscriber);
        setContent(content);
        setTotalDuration(totalDuration);
        setCurrentTime(currentTime);
    }
    
    public PlaybackSession(Subscriber subscriber, StreamContent content, int totalDuration) {
        this("SES-000", subscriber, content, 0, totalDuration);
    }
    
    public PlaybackSession(Subscriber subscriber, StreamContent content) {
        this(subscriber, content, 3600);
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(String sessionId) {
        if (!validarCodigo(sessionId)) {
            throw new IllegalArgumentException("El ID de sesión debe tener al menos 3 caracteres");
        }
        this.sessionId = sessionId;
    }
    
    public Subscriber getSubscriber() {
        return subscriber;
    }
    
    public void setSubscriber(Subscriber subscriber) {
        if (subscriber == null) {
            throw new IllegalArgumentException("El suscriptor no puede ser nulo");
        }
        this.subscriber = subscriber;
    }
    
    public StreamContent getContent() {
        return content;
    }
    
    public void setContent(StreamContent content) {
        if (content == null) {
            throw new IllegalArgumentException("El contenido no puede ser nulo");
        }
        this.content = content;
    }
    
    public int getCurrentTime() {
        return currentTime;
    }
    
    public void setCurrentTime(int currentTime) {
        if (currentTime < 0) {
            throw new IllegalArgumentException("El tiempo actual no puede ser negativo");
        }
        if (totalDuration > 0 && currentTime > totalDuration) {
            throw new IllegalArgumentException("El tiempo actual no puede ser mayor a la duración total");
        }
        this.currentTime = currentTime;
    }
    
    public int getTotalDuration() {
        return totalDuration;
    }
    
    public void setTotalDuration(int totalDuration) {
        if (totalDuration < 0) {
            throw new IllegalArgumentException("La duración total no puede ser negativa");
        }
        if (totalDuration > 86400) {
            throw new IllegalArgumentException("La duración no puede exceder 24 horas (86400 segundos)");
        }
        this.totalDuration = totalDuration;
    }
    
    private boolean validarCodigo(String codigo) {
        return codigo != null && codigo.length() >= 3;
    }
    
    public double calcularProgreso() {
        if (totalDuration == 0) return 0;
        return (currentTime * 100.0) / totalDuration;
    }
    
    public String obtenerResumen() {
        return subscriber.getName() + " está viendo: " + content.getTitle() + 
               " (" + String.format("%.1f", calcularProgreso()) + "% completado)";
    }
}

