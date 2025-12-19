public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   STREAMFLIX - Plataforma de Streaming");
        System.out.println("   Semana 03 - Encapsulación y Validaciones");
        System.out.println("========================================\n");
        
        try {
            SubscriptionPlan planIndividual = new SubscriptionPlan("PLAN-001", "Individual", 14900, 1, "HD");
            SubscriptionPlan planFamiliar = new SubscriptionPlan("Familiar", 19900, 4, "HD");
            SubscriptionPlan planPremium = new SubscriptionPlan("Premium", 24900);
            
            Subscriber suscriptor1 = new Subscriber("SUB-001", "Juan David Villada", "juan.villada@email.com", planPremium);
            Subscriber suscriptor2 = new Subscriber("María García", "maria.garcia@email.com", planFamiliar);
            Subscriber suscriptor3 = new Subscriber("Carlos Rodríguez", "carlos.rodriguez@email.com");
            
            StreamContent contenido1 = new StreamContent("STR-001", "Stranger Things", "Ciencia Ficción");
            StreamContent contenido2 = new StreamContent("The Crown", "Drama Histórico");
            StreamContent contenido3 = new StreamContent("Breaking Bad");
            
            PlaybackSession sesion1 = new PlaybackSession("SES-001", suscriptor1, contenido1, 1250, 3600);
            PlaybackSession sesion2 = new PlaybackSession(suscriptor2, contenido2, 3600);
            PlaybackSession sesion3 = new PlaybackSession(suscriptor3, contenido3);
            
            StreamFlixPlatform plataforma = new StreamFlixPlatform("StreamFlix");
            plataforma.agregarSesion(sesion1);
            plataforma.agregarSesion(sesion2);
            plataforma.agregarSesion(sesion3);
            
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
            
            System.out.println("\n=== Prueba de Validaciones ===");
            try {
                StreamContent contenidoInvalido = new StreamContent("", "Test", "Género");
            } catch (IllegalArgumentException e) {
                System.out.println("Validación exitosa: " + e.getMessage());
            }
            
            try {
                SubscriptionPlan planInvalido = new SubscriptionPlan("PLAN-002", "Test", -100, 1, "HD");
            } catch (IllegalArgumentException e) {
                System.out.println("Validación exitosa: " + e.getMessage());
            }
            
            try {
                Subscriber suscriptorInvalido = new Subscriber("SUB-999", "Juan", "email-invalido", planIndividual);
            } catch (IllegalArgumentException e) {
                System.out.println("Validación exitosa: " + e.getMessage());
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n========================================");
    }
}

