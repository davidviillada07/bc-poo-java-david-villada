public class PlaybackSession {
    private String sessionId;
    private Subscriber subscriber;
    private StreamContent content;
    private int currentTime;
    private int totalDuration;
    
    public PlaybackSession(String sessionId, Subscriber subscriber, StreamContent content, int currentTime, int totalDuration) {
        this.sessionId = sessionId;
        this.subscriber = subscriber;
        this.content = content;
        this.currentTime = currentTime;
        this.totalDuration = totalDuration;
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    public Subscriber getSubscriber() {
        return subscriber;
    }
    
    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
    
    public StreamContent getContent() {
        return content;
    }
    
    public void setContent(StreamContent content) {
        this.content = content;
    }
    
    public int getCurrentTime() {
        return currentTime;
    }
    
    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }
    
    public int getTotalDuration() {
        return totalDuration;
    }
    
    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
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

