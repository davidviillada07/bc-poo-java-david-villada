public class SubscriptionPlan {
    private String planId;
    private String planName;
    private double price;
    private int maxProfiles;
    private String videoQuality;
    
    public SubscriptionPlan(String planId, String planName, double price, int maxProfiles, String videoQuality) {
        this.planId = planId;
        this.planName = planName;
        this.price = price;
        this.maxProfiles = maxProfiles;
        this.videoQuality = videoQuality;
    }
    
    public String getPlanId() {
        return planId;
    }
    
    public void setPlanId(String planId) {
        this.planId = planId;
    }
    
    public String getPlanName() {
        return planName;
    }
    
    public void setPlanName(String planName) {
        this.planName = planName;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getMaxProfiles() {
        return maxProfiles;
    }
    
    public void setMaxProfiles(int maxProfiles) {
        this.maxProfiles = maxProfiles;
    }
    
    public String getVideoQuality() {
        return videoQuality;
    }
    
    public void setVideoQuality(String videoQuality) {
        this.videoQuality = videoQuality;
    }
    
    public double calcularPrecioAnual() {
        return price * 12 * 0.85;
    }
    
    public String obtenerDetalles() {
        return planName + " - $" + price + "/mes - " + videoQuality + " - " + maxProfiles + " perfiles";
    }
}

