public class Subscriber {
    private String subscriberId;
    private String name;
    private String email;
    private SubscriptionPlan plan;
    
    public Subscriber(String subscriberId, String name, String email, SubscriptionPlan plan) {
        setSubscriberId(subscriberId);
        setName(name);
        setEmail(email);
        setPlan(plan);
    }
    
    public Subscriber(String name, String email, SubscriptionPlan plan) {
        this("SUB-000", name, email, plan);
    }
    
    public Subscriber(String name, String email) {
        this(name, email, null);
    }
    
    public String getSubscriberId() {
        return subscriberId;
    }
    
    public void setSubscriberId(String subscriberId) {
        if (!validarCodigo(subscriberId)) {
            throw new IllegalArgumentException("El ID del suscriptor debe tener al menos 3 caracteres");
        }
        this.subscriberId = subscriberId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (name.length() < 2) {
            throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres");
        }
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        if (!validarEmail(email)) {
            throw new IllegalArgumentException("Email inválido. Debe contener @ y un dominio válido");
        }
        this.email = email;
    }
    
    public SubscriptionPlan getPlan() {
        return plan;
    }
    
    public void setPlan(SubscriptionPlan plan) {
        this.plan = plan;
    }
    
    private boolean validarCodigo(String codigo) {
        return codigo != null && codigo.length() >= 3;
    }
    
    private boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return email.contains("@") && email.contains(".") && email.length() >= 5;
    }
    
    public String obtenerInformacion() {
        String planInfo = plan != null ? plan.getPlanName() : "Sin plan";
        return "Suscriptor: " + name + " (" + email + ") - Plan: " + planInfo;
    }
}

