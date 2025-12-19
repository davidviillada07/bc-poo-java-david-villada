public class SubscriptionPlan {
    private String planId;
    private String planName;
    private double price;
    private int maxProfiles;
    private String videoQuality;
    
    public SubscriptionPlan(String planId, String planName, double price, int maxProfiles, String videoQuality) {
        setPlanId(planId);
        setPlanName(planName);
        setPrice(price);
        setMaxProfiles(maxProfiles);
        setVideoQuality(videoQuality);
    }
    
    public SubscriptionPlan(String planName, double price, int maxProfiles, String videoQuality) {
        this("PLAN-000", planName, price, maxProfiles, videoQuality);
    }
    
    public SubscriptionPlan(String planName, double price) {
        this(planName, price, 1, "HD");
    }
    
    public String getPlanId() {
        return planId;
    }
    
    public void setPlanId(String planId) {
        if (!validarCodigo(planId)) {
            throw new IllegalArgumentException("El ID del plan debe tener al menos 3 caracteres");
        }
        this.planId = planId;
    }
    
    public String getPlanName() {
        return planName;
    }
    
    public void setPlanName(String planName) {
        if (planName == null || planName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del plan no puede estar vacío");
        }
        this.planName = planName;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        this.price = price;
    }
    
    public int getMaxProfiles() {
        return maxProfiles;
    }
    
    public void setMaxProfiles(int maxProfiles) {
        if (maxProfiles < 1 || maxProfiles > 10) {
            throw new IllegalArgumentException("El número de perfiles debe estar entre 1 y 10");
        }
        this.maxProfiles = maxProfiles;
    }
    
    public String getVideoQuality() {
        return videoQuality;
    }
    
    public void setVideoQuality(String videoQuality) {
        if (videoQuality == null || videoQuality.trim().isEmpty()) {
            throw new IllegalArgumentException("La calidad de video no puede estar vacía");
        }
        if (!validarCalidad(videoQuality)) {
            throw new IllegalArgumentException("Calidad de video inválida. Use: SD, HD, 4K");
        }
        this.videoQuality = videoQuality;
    }
    
    private boolean validarCodigo(String codigo) {
        return codigo != null && codigo.length() >= 3;
    }
    
    private boolean validarCalidad(String calidad) {
        return calidad.equals("SD") || calidad.equals("HD") || calidad.equals("4K");
    }
    
    public double calcularPrecioAnual() {
        return price * 12 * 0.85;
    }
    
    public String obtenerDetalles() {
        return planName + " - $" + price + "/mes - " + videoQuality + " - " + maxProfiles + " perfiles";
    }
}

