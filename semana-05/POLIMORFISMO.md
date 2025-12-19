# Análisis de Polimorfismo - StreamFlix

## 1. Sobrecarga (Overloading)

### Métodos Sobrecargados en StreamFlixCatalog

#### buscarContenido(String codigo)
```java
public Content buscarContenido(String codigo)
```
Busca un contenido específico por su código único.

#### buscarContenido(String campo, String valor)
```java
public ArrayList<Content> buscarContenido(String campo, String valor)
```
Busca contenidos por campo específico (título o género) y valor.

#### buscarContenido(int añoInicio, int añoFin)
```java
public ArrayList<Content> buscarContenido(int añoInicio, int añoFin)
```
Busca contenidos dentro de un rango de años de lanzamiento.

### Justificación

La sobrecarga del método `buscarContenido()` tiene sentido en el dominio de StreamFlix porque:
- Los usuarios pueden buscar contenido de diferentes formas (por código, por género, por año)
- Proporciona una interfaz intuitiva y consistente
- Evita tener múltiples nombres de métodos (buscarPorCodigo, buscarPorGenero, buscarPorAño)
- Facilita la extensión futura (se pueden agregar más variantes sin cambiar el nombre)

## 2. Sobrescritura (Overriding)

### Tabla Comparativa

| Método | Content (Padre) | Movie | Series | Documentary |
|--------|----------------|-------|--------|-------------|
| `calcularPrecio()` | return 0.0 | precioBase + (rating * 500) | precioBase + (temporadas * 2000) | precioBase * 1.5 si es original |
| `obtenerDescripcion()` | "Contenido: título (género)" | "Película: título dirigida por director (Rating: X/10)" | "Serie: título creada por creator (X temporadas)" | "Documental: título narrado por narrator - Tema: topic" |
| `mostrarInfo()` | Info básica | Info + director + rating | Info + temporadas + episodios + creador | Info + narrador + tema + original |
| `calcularDuracionEnHoras()` | duration / 60.0 | duration / 60.0 | (temporadas * episodios * duration) / 60.0 | duration / 60.0 |

### Código Ejemplo

```java
// En Content (clase padre)
public double calcularPrecio() {
    return 0.0;
}

// En Movie (subclase)
@Override
public double calcularPrecio() {
    double precioBase = 5000.0;
    double bonoRating = rating * 500;
    return precioBase + bonoRating;
}

// En Series (subclase)
@Override
public double calcularPrecio() {
    double precioBase = 3000.0;
    double precioPorTemporada = numberOfSeasons * 2000;
    return precioBase + precioPorTemporada;
}

// En Documentary (subclase)
@Override
public double calcularPrecio() {
    double precioBase = 2000.0;
    if (isOriginal) {
        return precioBase * 1.5;
    }
    return precioBase;
}
```

## 3. Polimorfismo Dinámico

### Ejemplo de Dynamic Binding

```java
ArrayList<Content> contenidos = new ArrayList<>();
contenidos.add(new Movie(...));
contenidos.add(new Series(...));
contenidos.add(new Documentary(...));

for (Content contenido : contenidos) {
    // Dynamic binding: Java decide en tiempo de ejecución qué método llamar
    double precio = contenido.calcularPrecio();  // Llama al método de la subclase real
    String desc = contenido.obtenerDescripcion();  // Llama al método de la subclase real
}
```

### Explicación

El polimorfismo dinámico (dynamic binding) funciona de la siguiente manera en StreamFlix:

1. **En tiempo de compilación**: El compilador ve que `contenido` es de tipo `Content` y verifica que los métodos `calcularPrecio()` y `obtenerDescripcion()` existen en la clase padre.

2. **En tiempo de ejecución**: Java determina el tipo real del objeto (Movie, Series, o Documentary) y llama al método correspondiente de esa subclase.

3. **Ventaja**: Un mismo código puede trabajar con diferentes tipos de contenido sin necesidad de verificar el tipo explícitamente con `instanceof` o `if-else`.

### Ejemplo en StreamFlixCatalog

```java
public void procesarContenido(Content contenido) {
    // Este método acepta cualquier subclase de Content
    double precio = contenido.calcularPrecio();  // Polimorfismo dinámico
    System.out.println("Precio: $" + precio);
}

// Se puede llamar con cualquier tipo:
procesarContenido(new Movie(...));      // Calcula precio de película
procesarContenido(new Series(...));     // Calcula precio de serie
procesarContenido(new Documentary(...)); // Calcula precio de documental
```

## 4. Beneficios

### Flexibilidad

**Ejemplo**: El método `procesarCatalogoCompleto()` puede procesar cualquier combinación de películas, series y documentales sin modificar el código:

```java
public void procesarCatalogoCompleto() {
    for (Content contenido : contents) {
        procesarContenido(contenido);  // Funciona con cualquier subclase
    }
}
```

### Extensibilidad

**Ejemplo**: Si en el futuro se agrega una nueva clase `Podcast extends Content`, el código existente seguirá funcionando sin modificaciones:

```java
// Código nuevo
public class Podcast extends Content {
    @Override
    public double calcularPrecio() {
        return 1000.0;  // Precio específico para podcasts
    }
}

// Código existente sigue funcionando
catalogo.agregarContenido(new Podcast(...));  // ✅ Funciona
catalogo.procesarCatalogoCompleto();           // ✅ Funciona
```

### Mantenibilidad

**Ejemplo**: Si se necesita cambiar la lógica de cálculo de precio para películas, solo se modifica la clase `Movie`:

```java
// Cambio solo en Movie.java
@Override
public double calcularPrecio() {
    // Nueva lógica: precio base + rating + bono por año
    double precioBase = 5000.0;
    double bonoRating = rating * 500;
    double bonoAntiguedad = (2024 - releaseYear) * 100;
    return precioBase + bonoRating + bonoAntiguedad;
}

// Todo el código que usa calcularPrecio() automáticamente usa la nueva lógica
```

### Reducción de Código

**Sin polimorfismo** (código repetitivo):
```java
if (contenido instanceof Movie) {
    Movie m = (Movie) contenido;
    double precio = m.calcularPrecioPelicula();
} else if (contenido instanceof Series) {
    Series s = (Series) contenido;
    double precio = s.calcularPrecioSerie();
} else if (contenido instanceof Documentary) {
    Documentary d = (Documentary) contenido;
    double precio = d.calcularPrecioDocumental();
}
```

**Con polimorfismo** (código limpio):
```java
double precio = contenido.calcularPrecio();  // Una línea, funciona con todos
```

## 5. Resumen

El polimorfismo en StreamFlix permite:

1. **Tratar diferentes tipos de contenido de manera uniforme** mediante la clase padre `Content`
2. **Extender el sistema fácilmente** agregando nuevos tipos de contenido sin modificar código existente
3. **Mantener código limpio y legible** evitando múltiples `if-else` o `switch`
4. **Aplicar comportamientos específicos** mediante sobrescritura de métodos
5. **Proporcionar múltiples formas de uso** mediante sobrecarga de métodos

El polimorfismo es fundamental para crear sistemas flexibles, extensibles y mantenibles en Java.

