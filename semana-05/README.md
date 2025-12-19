# Semana 05 - Polimorfismo

## Estructura del Proyecto

- `Content.java` - Clase padre con métodos para sobrescribir
- `Movie.java` - Subclase con métodos sobrescritos (@Override)
- `Series.java` - Subclase con métodos sobrescritos (@Override)
- `Documentary.java` - Subclase con métodos sobrescritos (@Override)
- `StreamFlixCatalog.java` - Clase gestora con sobrecarga y métodos polimórficos
- `Main.java` - Demostración completa de polimorfismo
- `POLIMORFISMO.md` - Documentación del polimorfismo

## Ejecución

```bash
javac *.java
java Main
```

## Características Implementadas

- Sobrecarga de métodos (buscarContenido con diferentes parámetros)
- Sobrescritura de métodos con @Override (calcularPrecio, obtenerDescripcion)
- Métodos polimórficos que aceptan Content
- ArrayList polimórfico
- Dynamic binding demostrado
