# Jerarquía de Clases - Semana 04

## Diagrama

```
        Content
           |
    +------+------+------+
    |      |      |      |
  Movie  Series Documentary
```

## Justificación

La jerarquía elegida representa los diferentes tipos de contenido audiovisual disponibles en StreamFlix. La clase padre `Content` encapsula las características comunes a todos los contenidos (código, título, género, duración, año), mientras que las subclases `Movie`, `Series` y `Documentary` agregan atributos y comportamientos específicos de cada tipo:

- **Movie**: Tiene director y rating, representa contenido cinematográfico
- **Series**: Tiene temporadas, episodios y creador, representa contenido serializado
- **Documentary**: Tiene narrador, tema y si es original, representa contenido documental

Esta jerarquía permite aprovechar el polimorfismo para tratar todos los contenidos de manera uniforme mientras se mantienen las características específicas de cada tipo.

## Atributos Heredados (protected)

- `contentCode` (String): Código único identificador del contenido
- `title` (String): Título del contenido
- `genre` (String): Género al que pertenece
- `duration` (int): Duración en minutos
- `releaseYear` (int): Año de lanzamiento

## Atributos Específicos

### Movie
- `director` (String): Nombre del director
- `rating` (double): Calificación de 0 a 10

### Series
- `numberOfSeasons` (int): Número de temporadas
- `episodesPerSeason` (int): Episodios por temporada
- `creator` (String): Nombre del creador

### Documentary
- `narrator` (String): Nombre del narrador
- `topic` (String): Tema del documental
- `isOriginal` (boolean): Si es contenido original de StreamFlix

## Métodos Heredados

### mostrarInfo()
Método sobrescrito en todas las subclases para mostrar información específica:
- **Content**: Muestra información básica (código, título, género, duración, año)
- **Movie**: Agrega director y rating, muestra duración en horas
- **Series**: Agrega temporadas, episodios, creador y duración total
- **Documentary**: Agrega narrador, tema y si es original

### calcularDuracionEnHoras()
Método sobrescrito en Series para calcular la duración total considerando todas las temporadas:
- **Content**: Calcula duración de un solo elemento
- **Series**: Calcula duración total de todos los episodios de todas las temporadas

## Métodos Específicos

Cada subclase tiene un método `obtenerTipo()` que retorna el tipo de contenido:
- `Movie.obtenerTipo()` → "Película"
- `Series.obtenerTipo()` → "Serie"
- `Documentary.obtenerTipo()` → "Documental"

## Uso de Polimorfismo

En el `Main.java` se demuestra polimorfismo mediante:
1. **Array polimórfico**: `Content[] contenidos` puede almacenar objetos de cualquier subclase
2. **Llamadas polimórficas**: `contenido.mostrarInfo()` ejecuta la versión específica de cada subclase
3. **instanceof**: Se usa para identificar el tipo específico y acceder a métodos únicos de cada subclase

## Beneficios de la Herencia

1. **Reutilización de código**: Los atributos y métodos comunes se definen una sola vez en Content
2. **Mantenibilidad**: Cambios en la clase padre se reflejan automáticamente en las subclases
3. **Polimorfismo**: Permite tratar diferentes tipos de contenido de manera uniforme
4. **Extensibilidad**: Fácil agregar nuevos tipos de contenido (ej: Podcast, LiveStream) extendiendo Content
5. **Organización**: Estructura clara y lógica que refleja el dominio del problema

