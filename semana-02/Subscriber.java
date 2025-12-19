public class Subscriber {
    private String subscriberId;
    private String name;
    private String email;
    private SubscriptionPlan plan;
    
    public Subscriber(String subscriberId, String name, String email, SubscriptionPlan plan) {
        this.subscriberId = subscriberId;
        this.name = name;
        this.email = email;
        this.plan = plan;
    }
    
    public String getSubscriberId() {
        return subscriberId;
    }
    
    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public SubscriptionPlan getPlan() {
        return plan;
    }
    
    public void setPlan(SubscriptionPlan plan) {
        this.plan = plan;
    }
    
    public String obtenerInformacion() {
        return "Suscriptor: " + name + " (" + email + ") - Plan: " + plan.getPlanName();
    }
}

