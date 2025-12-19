# Semana 06: Abstracción e Interfaces

## Descripción

Sistema de gestión de contenido audiovisual que utiliza abstracción mediante clases abstractas e interfaces para modelar diferentes tipos de contenido (películas, series, documentales) con capacidades compartidas como reproducción, calificación y recomendación.

## Clases Abstractas Implementadas

### Content
- **Propósito:** Representa el concepto general de contenido audiovisual en StreamFlix
- **Métodos abstractos:** mostrarInfo(), calcularPrecio(), obtenerDescripcion()
- **Métodos concretos:** calcularDuracionEnHoras(), mostrarInformacionBasica()
- **Subclases:** Movie, Series, Documentary

## Interfaces Implementadas

### Reproducible
- **Capacidad:** Reproducción, pausa y detención de contenido
- **Implementada por:** Movie, Series

### Calificable
- **Capacidad:** Recepción y gestión de calificaciones de usuarios
- **Implementada por:** Movie, Series, Documentary

### Recomendable
- **Capacidad:** Recomendación a usuarios según preferencias
- **Implementada por:** Movie, Series, Documentary

## Jerarquía de Clases

```
       <<abstract>>
         Content
           |
    +------+------+------+
    |      |      |      |
  Movie  Series Documentary
    |      |
    +-- implements Reproducible
    +-- implements Calificable
    +-- implements Recomendable
```

## Compilación y Ejecución

```bash
javac abstractas/*.java interfaces/*.java implementaciones/*.java Main.java
java Main
```

## Características Implementadas

- Clase abstracta Content con métodos abstractos
- 3 interfaces (Reproducible, Calificable, Recomendable)
- Múltiple implementación (Movie implementa las 3 interfaces)
- Polimorfismo con clase abstracta
- Uso de referencias de tipo interface
