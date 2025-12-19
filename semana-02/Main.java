public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   STREAMFLIX - Plataforma de Streaming");
        System.out.println("========================================\n");
        
        SubscriptionPlan planIndividual = new SubscriptionPlan("PLAN-001", "Individual", 14900, 1, "HD");
        SubscriptionPlan planFamiliar = new SubscriptionPlan("PLAN-002", "Familiar", 19900, 4, "HD");
        SubscriptionPlan planPremium = new SubscriptionPlan("PLAN-003", "Premium", 24900, 6, "4K");
        
        Subscriber suscriptor1 = new Subscriber("SUB-001", "Juan David Villada", "juan.villada@email.com", planPremium);
        Subscriber suscriptor2 = new Subscriber("SUB-002", "María García", "maria.garcia@email.com", planFamiliar);
        Subscriber suscriptor3 = new Subscriber("SUB-003", "Carlos Rodríguez", "carlos.rodriguez@email.com", planIndividual);
        
        StreamContent contenido1 = new StreamContent("STR-001", "Stranger Things", "Ciencia Ficción");
        StreamContent contenido2 = new StreamContent("STR-002", "The Crown", "Drama Histórico");
        StreamContent contenido3 = new StreamContent("STR-003", "Breaking Bad", "Thriller");
        
        PlaybackSession sesion1 = new PlaybackSession("SES-001", suscriptor1, contenido1, 1250, 3600);
        PlaybackSession sesion2 = new PlaybackSession("SES-002", suscriptor2, contenido2, 1800, 3600);
        PlaybackSession sesion3 = new PlaybackSession("SES-003", suscriptor3, contenido3, 900, 2700);
        PlaybackSession sesion4 = new PlaybackSession("SES-004", suscriptor1, contenido3, 500, 2700);
        
        StreamFlixPlatform plataforma = new StreamFlixPlatform("StreamFlix");
        plataforma.agregarSesion(sesion1);
        plataforma.agregarSesion(sesion2);
        plataforma.agregarSesion(sesion3);
        plataforma.agregarSesion(sesion4);
        
        System.out.println("=== Información de Suscriptores ===");
        System.out.println(suscriptor1.obtenerInformacion());
        System.out.println(suscriptor2.obtenerInformacion());
        System.out.println(suscriptor3.obtenerInformacion());
        
        System.out.println("\n=== Información de Planes ===");
        System.out.println(planIndividual.obtenerDetalles());
        System.out.println("Precio anual: $" + String.format("%.0f", planIndividual.calcularPrecioAnual()));
        System.out.println(planFamiliar.obtenerDetalles());
        System.out.println("Precio anual: $" + String.format("%.0f", planFamiliar.calcularPrecioAnual()));
        System.out.println(planPremium.obtenerDetalles());
        System.out.println("Precio anual: $" + String.format("%.0f", planPremium.calcularPrecioAnual()));
        
        plataforma.mostrarTodasSesiones();
        plataforma.mostrarResumen();
        
        System.out.println("\n========================================");
    }
}

