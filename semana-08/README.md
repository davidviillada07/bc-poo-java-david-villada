# Semana 08: Colecciones y Generics

## Descripción

Sistema de gestión de catálogo para StreamFlix refactorizado para usar colecciones profesionales (HashMap, ArrayList) con Generics, agregando operaciones de búsqueda eficiente O(1), filtrado y estadísticas avanzadas.

## Colecciones Utilizadas

### HashMap
- `Map<String, Content> contenidosPorCodigo` - Búsqueda rápida O(1) por código
- `Map<String, List<Content>> contenidosPorGenero` - Agrupación por género

### ArrayList
- `List<Content> historial` - Historial ordenado de contenidos

## Operaciones Implementadas

### CRUD con Colecciones
- Agregar con validación de duplicados usando HashMap
- Buscar por clave O(1) con HashMap
- Eliminar por código (sincroniza HashMap y ArrayList)
- Listar todos los contenidos

### Filtrado
- Filtrar por rango de precio
- Filtrar por rango de años
- Filtrar por género (usando HashMap de agrupación)
- Filtrar por duración

### Estadísticas
- Total de precios
- Promedio de precios
- Contenido más caro/barato
- Duración total y promedio
- Conteo por tipo (Película, Serie, Documental)
- Conteo por género

## Cómo Ejecutar

### Desde la raíz del proyecto:

```bash
cd semana-08
javac -d bin -sourcepath src src/com/streamflix/modelo/*.java src/com/streamflix/excepciones/*.java src/com/streamflix/servicio/*.java src/com/streamflix/*.java
java -cp bin com.streamflix.Main
```

### Alternativa (compilando cada paquete):

```bash
cd semana-08
javac -d bin src/com/streamflix/modelo/*.java
javac -d bin src/com/streamflix/excepciones/*.java
javac -d bin src/com/streamflix/servicio/*.java
javac -d bin src/com/streamflix/*.java
java -cp bin com.streamflix.Main
```

## Características Implementadas

- Migración completa de arrays a ArrayList con Generics
- HashMap para búsqueda O(1) por código
- HashMap para agrupación por género
- Métodos de filtrado (precio, año, género, duración)
- Estadísticas completas (totales, promedios, máximos, mínimos)
- Conteo por categorías usando Map
- Menú interactivo con Scanner
- Demostración de eficiencia HashMap O(1)

## Cambios Aplicados desde Semana 07

1. **HashMap agregado**: Búsqueda O(1) por código en lugar de O(n)
2. **Agrupación por género**: Segundo HashMap para acceso rápido por género
3. **Filtrado**: Múltiples métodos de filtrado implementados
4. **Estadísticas**: Cálculos de totales, promedios, máximos y mínimos
5. **Menú interactivo**: Sistema completo con opciones de usuario
6. **Generics**: Uso de List<E> y Map<K,V> en todas las declaraciones

