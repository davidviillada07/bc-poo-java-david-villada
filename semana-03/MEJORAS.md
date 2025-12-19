# Mejoras - Semana 03

## Encapsulación Aplicada

### Clase: StreamContent
- Atributos encapsulados: `contentCode`, `title`, `genre` (todos private)
- Validaciones agregadas:
  - Código debe tener al menos 3 caracteres
  - Título no puede estar vacío o nulo
  - Género no puede estar vacío o nulo
- Método privado auxiliar: `validarCodigo()` para validar formato de códigos

### Clase: SubscriptionPlan
- Atributos encapsulados: `planId`, `planName`, `price`, `maxProfiles`, `videoQuality` (todos private)
- Validaciones agregadas:
  - ID del plan debe tener al menos 3 caracteres
  - Nombre del plan no puede estar vacío
  - Precio debe ser mayor a cero
  - Número de perfiles entre 1 y 10
  - Calidad de video debe ser SD, HD o 4K
- Métodos privados auxiliares: `validarCodigo()` y `validarCalidad()`

### Clase: Subscriber
- Atributos encapsulados: `subscriberId`, `name`, `email`, `plan` (todos private)
- Validaciones agregadas:
  - ID del suscriptor debe tener al menos 3 caracteres
  - Nombre no puede estar vacío y debe tener al menos 2 caracteres
  - Email debe contener @ y un dominio válido (mínimo 5 caracteres)
- Métodos privados auxiliares: `validarCodigo()` y `validarEmail()`

### Clase: PlaybackSession
- Atributos encapsulados: `sessionId`, `subscriber`, `content`, `currentTime`, `totalDuration` (todos private)
- Validaciones agregadas:
  - ID de sesión debe tener al menos 3 caracteres
  - Suscriptor y contenido no pueden ser nulos
  - Tiempo actual no puede ser negativo ni mayor a la duración total
  - Duración total no puede ser negativa ni exceder 24 horas (86400 segundos)
- Método privado auxiliar: `validarCodigo()`

### Clase: StreamFlixPlatform
- Atributos encapsulados: `platformName`, `sessions` (todos private)
- Validaciones agregadas:
  - Nombre de plataforma no puede estar vacío
  - Sesión no puede ser nula al agregar
- Manejo de lista vacía en `mostrarTodasSesiones()`

## Constructores Sobrecargados

### Clase: StreamContent
- Constructor 1: Completo con código, título y género
- Constructor 2: Básico con título y género (asigna código por defecto "STR-000")
- Constructor 3: Mínimo solo con título (asigna código y género por defecto)

### Clase: SubscriptionPlan
- Constructor 1: Completo con todos los parámetros
- Constructor 2: Sin ID (asigna "PLAN-000" por defecto)
- Constructor 3: Mínimo con nombre y precio (asigna 1 perfil y HD por defecto)

### Clase: Subscriber
- Constructor 1: Completo con ID, nombre, email y plan
- Constructor 2: Sin ID (asigna "SUB-000" por defecto)
- Constructor 3: Mínimo con nombre y email (plan puede ser null)

### Clase: PlaybackSession
- Constructor 1: Completo con todos los parámetros
- Constructor 2: Sin ID y tiempo actual (asigna "SES-000" y tiempo 0)
- Constructor 3: Mínimo con suscriptor y contenido (asigna duración de 3600 segundos por defecto)

### Clase: StreamFlixPlatform
- Constructor 1: Con nombre de plataforma
- Constructor 2: Sin parámetros (asigna "StreamFlix" por defecto)

## Beneficios Logrados

- **Seguridad de datos**: Los atributos privados previenen acceso directo no autorizado
- **Integridad de datos**: Las validaciones garantizan que solo se acepten valores válidos
- **Flexibilidad**: Los constructores sobrecargados permiten crear objetos de diferentes formas según el contexto
- **Mantenibilidad**: Los métodos privados de validación centralizan la lógica de validación
- **Robustez**: Las excepciones IllegalArgumentException proporcionan mensajes claros de error
- **Reutilización**: Los constructores que llaman a otros constructores (this()) evitan duplicación de código
- **Prevención de errores**: Validaciones en setters previenen estados inválidos del objeto
- **Claridad**: Cada clase tiene responsabilidades claras y bien definidas

## Ejemplos de Validaciones Implementadas

1. **Validación de Email**: Verifica que contenga @ y un dominio válido
2. **Validación de Precio**: Asegura que sea mayor a cero
3. **Validación de Rango**: Perfiles entre 1 y 10, duración máxima de 24 horas
4. **Validación de Calidad**: Solo acepta SD, HD o 4K
5. **Validación de Strings**: No permite valores nulos o vacíos
6. **Validación de Referencias**: Objetos relacionados no pueden ser nulos cuando es crítico

## Impacto en el Código

- Todas las clases ahora son más robustas y seguras
- El código es más fácil de mantener y extender
- Los errores se detectan tempranamente con mensajes claros
- La creación de objetos es más flexible con múltiples constructores
- El código sigue principios SOLID, especialmente el principio de encapsulación

