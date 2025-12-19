# Análisis de Diseño - Semana 06: StreamFlix

## 1. Identificación de Abstracciones

### Clase Abstracta: Content

**¿Por qué es abstracta?**

La clase `Content` es abstracta porque:

1. **Representa un concepto general:** Todos los contenidos en StreamFlix (películas, series, documentales) comparten características comunes (código, título, género, duración, año), pero cada tipo tiene comportamientos específicos que deben implementarse de manera diferente.

2. **Comportamiento común:** Todos los contenidos necesitan mostrar información, calcular precio y generar descripción, pero la implementación varía según el tipo:
   - Las películas muestran director y rating
   - Las series muestran temporadas, episodios y creador
   - Los documentales muestran narrador, tema y si es original

3. **Comportamiento variable:** Los métodos `mostrarInfo()`, `calcularPrecio()` y `obtenerDescripcion()` tienen lógica diferente en cada subclase, por lo que deben ser abstractos.

4. **Prevención de instanciación:** No tiene sentido crear un objeto "Content" genérico; solo tiene sentido crear objetos específicos como Movie, Series o Documentary.

**Jerarquía:**
```
        <<abstract>>
         Content
           |
    +------+------+------+
    |      |      |      |
  Movie  Series Documentary
```

**Atributos protegidos compartidos:**
- `contentCode` (String): Código único del contenido
- `title` (String): Título del contenido
- `genre` (String): Género al que pertenece
- `duration` (int): Duración en minutos
- `releaseYear` (int): Año de lanzamiento

**Métodos abstractos:**
- `mostrarInfo()`: Cada tipo muestra información específica
- `calcularPrecio()`: Cada tipo calcula precio según sus características
- `obtenerDescripcion()`: Cada tipo genera descripción única

**Métodos concretos:**
- `calcularDuracionEnHoras()`: Convierte minutos a horas (común a todos)
- `mostrarInformacionBasica()`: Muestra información básica compartida

## 2. Interfaces Implementadas

### Interface 1: Reproducible

**Capacidad que define:** Define la capacidad de ser reproducido, pausado y detenido. Representa el comportamiento de reproducción de contenido audiovisual.

**Clases que la implementan:**
- `Movie`: Las películas pueden reproducirse, pausarse y detenerse
- `Series`: Las series pueden reproducirse episodio por episodio

**¿Por qué tiene sentido?**
- No todos los contenidos son reproducibles de la misma manera (por ejemplo, un documental podría tener un modo de visualización diferente)
- Permite que solo los contenidos que realmente se reproducen implementen esta capacidad
- Facilita agregar nuevos tipos de contenido reproducibles sin modificar código existente

**Métodos:**
- `iniciarReproduccion()`: Inicia la reproducción
- `pausarReproduccion()`: Pausa la reproducción
- `detenerReproduccion()`: Detiene completamente
- `estaReproduciendo()`: Verifica estado
- `obtenerTiempoActual()`: Obtiene tiempo actual

### Interface 2: Calificable

**Capacidad que define:** Define la capacidad de recibir y gestionar calificaciones de usuarios.

**Clases que la implementan:**
- `Movie`: Los usuarios pueden calificar películas
- `Series`: Los usuarios pueden calificar series
- `Documentary`: Los usuarios pueden calificar documentales

**¿Por qué tiene sentido?**
- Todos los tipos de contenido pueden ser calificados, pero no todos son reproducibles
- Permite que cualquier contenido pueda recibir calificaciones independientemente de su tipo
- Facilita agregar funcionalidad de calificaciones sin modificar la jerarquía de herencia

**Métodos:**
- `agregarCalificacion(double calificacion)`: Agrega una calificación
- `obtenerPromedioCalificaciones()`: Calcula promedio
- `obtenerNumeroCalificaciones()`: Obtiene total de calificaciones
- `tieneCalificaciones()`: Verifica si tiene calificaciones

### Interface 3: Recomendable

**Capacidad que define:** Define la capacidad de ser recomendado a usuarios según sus preferencias y características del contenido.

**Clases que la implementan:**
- `Movie`: Se recomienda según rating y género
- `Series`: Se recomienda según número de temporadas y género
- `Documentary`: Se recomienda si es original y según género

**¿Por qué tiene sentido?**
- El sistema de recomendaciones es independiente del tipo de contenido
- Permite diferentes criterios de recomendación para cada tipo
- Facilita agregar nuevos algoritmos de recomendación sin modificar clases existentes

**Métodos:**
- `esRecomendablePara(String generoPreferido)`: Verifica si es recomendable
- `calcularPuntuacionRecomendacion()`: Calcula puntuación
- `obtenerRazonRecomendacion()`: Obtiene razón de recomendación
- `esContenidoDestacado()`: Verifica si es destacado

## 3. Decisiones de Diseño

### ¿Por qué Clase Abstracta vs Interface?

#### Elegí clase abstracta para Content porque:

1. **Relación "es-un" clara:** Movie, Series y Documentary SON tipos de Content. Hay una relación jerárquica clara.

2. **Compartir estado:** Necesitaba compartir atributos comunes (contentCode, title, genre, duration, releaseYear) entre todas las subclases. Las interfaces no permiten atributos de instancia.

3. **Comportamiento común implementable:** Hay métodos que tienen la misma implementación para todos (como `calcularDuracionEnHoras()`), que pueden definirse en la clase abstracta.

4. **Constructor común:** Todas las subclases necesitan inicializar los mismos atributos base, lo cual se logra mejor con una clase abstracta.

#### Elegí interfaces para Reproducible, Calificable y Recomendable porque:

1. **Capacidades independientes de jerarquía:** Una capacidad como "ser reproducible" no define una relación "es-un", sino "puede-hacer". No todos los Content son reproducibles (por ejemplo, un futuro tipo "Trailer" podría no serlo).

2. **Múltiple implementación:** Una clase puede implementar múltiples interfaces. Por ejemplo, `Movie` implementa las tres interfaces, mientras que `Documentary` solo implementa `Calificable` y `Recomendable`.

3. **Solo define contrato:** Las interfaces definen QUÉ debe hacer una clase, no CÓMO lo hace. Cada clase implementa los métodos según sus necesidades específicas.

4. **Flexibilidad:** Permite que clases no relacionadas jerárquicamente compartan capacidades. Por ejemplo, en el futuro podríamos tener una clase `Podcast` que también implemente `Reproducible` sin necesidad de extender `Content`.

## 4. Principios SOLID Aplicados

### Single Responsibility Principle (SRP)

**Aplicación:**
- **Content (abstracta):** Responsabilidad única de definir la estructura común de contenido
- **Movie/Series/Documentary:** Responsabilidad única de implementar comportamiento específico de su tipo
- **Reproducible:** Responsabilidad única de definir capacidad de reproducción
- **Calificable:** Responsabilidad única de definir capacidad de calificación
- **Recomendable:** Responsabilidad única de definir capacidad de recomendación

**Ejemplo:**
```java
// Content solo se preocupa por estructura común
public abstract class Content {
    protected String title;
    // ...
}

// Reproducible solo se preocupa por reproducción
public interface Reproducible {
    void iniciarReproduccion();
    // ...
}
```

### Open/Closed Principle (OCP)

**Aplicación:**
- El sistema está **abierto a extensión** pero **cerrado a modificación**
- Se pueden agregar nuevos tipos de contenido (ej: `Podcast extends Content`) sin modificar código existente
- Se pueden agregar nuevas interfaces (ej: `Descargable`) sin afectar clases existentes

**Ejemplo:**
```java
// Agregar nuevo tipo sin modificar código existente
public class Podcast extends Content implements Reproducible, Calificable {
    // Nueva implementación
}
// Todo el código que usa Content[] sigue funcionando
```

### Liskov Substitution Principle (LSP)

**Aplicación:**
- Cualquier instancia de `Movie`, `Series` o `Documentary` puede sustituir a `Content` sin romper el comportamiento esperado
- Las subclases mantienen el contrato de los métodos abstractos
- Se puede usar polimorfismo con confianza

**Ejemplo:**
```java
Content[] contenidos = new Content[3];
contenidos[0] = new Movie(...);      // ✅ Funciona
contenidos[1] = new Series(...);     // ✅ Funciona
contenidos[2] = new Documentary(...); // ✅ Funciona

// Todos responden a los mismos métodos
for (Content c : contenidos) {
    c.mostrarInfo();  // Cada uno implementa según su tipo
}
```

### Interface Segregation Principle (ISP)

**Aplicación:**
- Las interfaces son específicas y cohesivas
- `Reproducible` solo define métodos de reproducción
- `Calificable` solo define métodos de calificación
- `Recomendable` solo define métodos de recomendación
- Las clases implementan solo las interfaces que necesitan

**Ejemplo:**
```java
// Documentary no necesita Reproducible, solo Calificable y Recomendable
public class Documentary extends Content 
    implements Calificable, Recomendable {
    // No está obligado a implementar Reproducible
}

// Movie implementa las tres porque las necesita todas
public class Movie extends Content 
    implements Reproducible, Calificable, Recomendable {
    // Implementa todas las capacidades
}
```

### Dependency Inversion Principle (DIP)

**Aplicación:**
- El código depende de abstracciones (`Content`, interfaces) en lugar de implementaciones concretas
- Los métodos aceptan tipos abstractos como parámetros
- Facilita el cambio de implementaciones sin afectar el código cliente

**Ejemplo:**
```java
// Depende de abstracción Content, no de Movie/Series específicamente
public void procesarContenido(Content contenido) {
    contenido.mostrarInfo();  // Usa abstracción
}

// Depende de interface Reproducible, no de Movie específicamente
public void reproducir(Reproducible reproducible) {
    reproducible.iniciarReproduccion();  // Usa interface
}
```

## 5. Mejoras Logradas

### Antes (Semana 05)

**Problemas:**
- `Content` era una clase concreta con métodos que retornaban valores por defecto
- No había separación clara entre capacidades (reproducción, calificación, recomendación)
- Difícil agregar nuevas capacidades sin modificar todas las clases
- No había forma de indicar qué contenidos pueden reproducirse vs cuáles no

**Código anterior:**
```java
public class Content {
    public double calcularPrecio() {
        return 0.0;  // Valor por defecto sin sentido
    }
    // No había interfaces para capacidades
}
```

### Después (Semana 06)

**Mejoras:**
- `Content` es abstracta, forzando implementación específica en subclases
- Interfaces separadas para cada capacidad (Reproducible, Calificable, Recomendable)
- Fácil agregar nuevas capacidades mediante interfaces sin modificar código existente
- Claro qué contenidos tienen qué capacidades mediante implementación de interfaces
- Mayor flexibilidad: `Documentary` no implementa `Reproducible` porque no se reproduce igual que películas/series

**Código actual:**
```java
public abstract class Content {
    public abstract double calcularPrecio();  // Debe implementarse
}

public interface Reproducible {
    void iniciarReproduccion();
    // ...
}

// Movie implementa todas las capacidades
public class Movie extends Content 
    implements Reproducible, Calificable, Recomendable {
    // Implementación completa
}
```

**Ventajas del nuevo diseño:**
1. **Claridad:** Es evidente qué capacidades tiene cada tipo de contenido
2. **Extensibilidad:** Fácil agregar nuevos tipos o capacidades
3. **Mantenibilidad:** Cambios en una capacidad no afectan otras
4. **Flexibilidad:** Cada clase implementa solo lo que necesita

## 6. Diagrama de Clases

```
        <<abstract>>
         Content
    +------------------+
    | +contentCode     |
    | +title           |
    | +genre           |
    | +duration        |
    | +releaseYear     |
    +------------------+
    | +mostrarInfo()   | (abstract)
    | +calcularPrecio()| (abstract)
    | +obtenerDescripcion()| (abstract)
    | +calcularDuracionEnHoras()| (concrete)
    +------------------+
           |
    +------+------+------+
    |      |      |      |
  Movie  Series Documentary
    |      |
    +-- implements <<interface>> Reproducible
    +-- implements <<interface>> Calificable
    +-- implements <<interface>> Recomendable
```

**Interfaces:**
```
<<interface>>
Reproducible
+ iniciarReproduccion()
+ pausarReproduccion()
+ detenerReproduccion()
+ estaReproduciendo()
+ obtenerTiempoActual()

<<interface>>
Calificable
+ agregarCalificacion(double)
+ obtenerPromedioCalificaciones()
+ obtenerNumeroCalificaciones()
+ tieneCalificaciones()

<<interface>>
Recomendable
+ esRecomendablePara(String)
+ calcularPuntuacionRecomendacion()
+ obtenerRazonRecomendacion()
+ esContenidoDestacado()
```

## 7. Desafíos y Soluciones

### Desafío 1: Decidir qué hacer abstracto vs interface

**Problema:** Inicialmente no estaba claro si `Content` debía ser abstracta o si las capacidades debían ser interfaces.

**Solución:** 
- Analicé la relación: Content tiene relación "es-un" con sus subclases → Clase abstracta
- Las capacidades (reproducción, calificación) son "puede-hacer" → Interfaces
- Content comparte estado (atributos) → Necesita clase abstracta
- Las capacidades solo definen comportamiento → Interfaces son suficientes

### Desafío 2: Documentary no implementa Reproducible

**Problema:** Inicialmente pensé que todos los contenidos deberían ser reproducibles, pero los documentales podrían tener un modo de visualización diferente.

**Solución:**
- `Documentary` solo implementa `Calificable` y `Recomendable`
- Si en el futuro necesitamos reproducir documentales, simplemente agregamos `implements Reproducible`
- Esto demuestra la flexibilidad del diseño con interfaces

### Desafío 3: Múltiple implementación en Main

**Problema:** Al usar referencias de tipo interface, perdía acceso a métodos de otras interfaces o de la clase abstracta.

**Solución:**
- Usar casting cuando sea necesario: `(Content) contenidoRecomendable`
- Crear variables de diferentes tipos cuando se necesiten múltiples capacidades
- Usar `instanceof` para verificar tipos antes de casting

## 8. Próximos Pasos

### Mejoras Potenciales

1. **Agregar más tipos de contenido:**
   - `Podcast extends Content implements Reproducible, Calificable`
   - `LiveStream extends Content implements Reproducible`

2. **Agregar más interfaces:**
   - `Descargable`: Para contenido que se puede descargar
   - `Compartible`: Para contenido que se puede compartir en redes sociales
   - `Subtitulable`: Para contenido con subtítulos

3. **Sistema de recomendaciones avanzado:**
   - Algoritmo de machine learning para recomendaciones
   - Análisis de preferencias de usuario
   - Recomendaciones colaborativas

4. **Persistencia:**
   - Guardar calificaciones en base de datos
   - Guardar progreso de reproducción
   - Sincronización entre dispositivos

5. **Sistema de reproducción mejorado:**
   - Guardar punto de reproducción
   - Reproducción automática del siguiente episodio
   - Calidad adaptativa según conexión

### Extensión del Sistema

El diseño actual permite fácil extensión:

```java
// Nuevo tipo de contenido
public class Podcast extends Content 
    implements Reproducible, Calificable {
    // Implementación específica
}

// Nueva capacidad
public interface Descargable {
    void descargar();
    boolean estaDescargado();
    double obtenerTamañoMB();
}

// Agregar capacidad a clase existente
public class Movie extends Content 
    implements Reproducible, Calificable, Recomendable, Descargable {
    // Implementa nueva interface
}
```

**Sin modificar código existente**, el sistema se extiende naturalmente.

