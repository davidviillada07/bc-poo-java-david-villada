# Semana 03 - Encapsulación y Constructores

## Estructura del Proyecto

- `StreamContent.java` - Contenido audiovisual con encapsulación completa
- `Subscriber.java` - Suscriptor con validaciones de email y nombre
- `SubscriptionPlan.java` - Plan de suscripción con validaciones de precio y calidad
- `PlaybackSession.java` - Sesión de reproducción con validaciones de tiempo
- `StreamFlixPlatform.java` - Plataforma gestora con ArrayList
- `Main.java` - Punto de entrada con pruebas de validaciones
- `MEJORAS.md` - Documentación de mejoras implementadas

## Ejecución

```bash
javac *.java
java Main
```

## Características Implementadas

- Encapsulación completa (atributos private)
- Sobrecarga de constructores (mínimo 2 por clase)
- Validaciones en constructores y setters
- Métodos auxiliares privados para validaciones
