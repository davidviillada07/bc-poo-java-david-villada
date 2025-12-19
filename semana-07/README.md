# Semana 07: Paquetes y Excepciones

## Descripción

Sistema de gestión de catálogo para StreamFlix que permite agregar, buscar y gestionar contenido audiovisual (películas, series, documentales) con validaciones robustas mediante excepciones personalizadas y manejo de errores apropiado.

## Estructura de Paquetes

```
com.streamflix/
├── modelo/          - Clases del dominio (Content, Movie, Series, Documentary, Interfaces)
├── servicio/        - Lógica de negocio (GestorCatalogo)
├── excepciones/     - Excepciones personalizadas
└── Main.java        - Punto de entrada del programa
```

## Excepciones Personalizadas

### 1. ContenidoInvalidoException
- **Tipo**: Checked (extends Exception)
- **Cuándo se lanza**: Cuando se intenta agregar contenido con datos inválidos
- **Casos**: Contenido nulo, código inválido, título vacío, duración inválida, código duplicado, capacidad máxima

### 2. ContenidoNoEncontradoException
- **Tipo**: Checked (extends Exception)
- **Cuándo se lanza**: Cuando se busca contenido que no existe en el catálogo
- **Casos**: Código vacío, contenido no encontrado

## Cómo Ejecutar

```bash
cd semana-07
javac -d bin src/com/streamflix/**/*.java src/com/streamflix/*.java
java -cp bin com.streamflix.Main
```

## Funcionalidades Implementadas

- Organización en paquetes (com.streamflix.*)
- 2 excepciones personalizadas (ContenidoInvalidoException, ContenidoNoEncontradoException)
- Validaciones con excepciones en GestorCatalogo
- Try-catch en Main para manejo de excepciones
- Finally para limpieza de recursos
- 10 casos de prueba en Main

## Cambios Aplicados desde Semana 06

1. Reorganización: Código movido a paquetes Java estándar
2. Excepciones: Creadas 2 excepciones personalizadas (checked)
3. Validaciones: Agregadas validaciones robustas en GestorCatalogo
4. Manejo de errores: Try-catch en Main para operaciones críticas
5. Servicio: Creada clase GestorCatalogo para gestionar el catálogo
