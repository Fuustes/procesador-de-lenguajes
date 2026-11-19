# Procesador de Lenguajes (Grupo 90)

Práctica de **Procesadores de Lenguajes** (Grado en Ingeniería Informática, ETSIINF-UPM, curso 2025-26). Es un procesador en **Java** con:

- **Analizador léxico**: autómata finito determinista (`AFD`, `ALex`, tabla de palabras reservadas `TPR`).
- **Analizador sintáctico** (`ASin`) a partir de la gramática de `pdl/gramatica.txt`.
- **Analizador semántico** (`ASem`).
- **Tabla de símbolos** con gestor de ámbitos (`TS`, `TS_Gestor`) y gestión de errores.

## Estructura
- `pdl/PDL/PDL/PDL/src/practicaPDL/`: código fuente.
- `pdl/gramatica.txt`: gramática del lenguaje.
- `pdl/Casos de Prueba.zip`: casos de prueba correctos e incorrectos.
- `pdl/Memoria Procesador.pdf`: memoria del proyecto.

## Ejecución
Importa `pdl/PDL/PDL/PDL` como proyecto Java en Eclipse (o compila con `javac`) y ejecuta `practicaPDL.Main` pasándole el fichero fuente.

## Autores
Mario Fustes González · Miguel Martín Trilla · Janele Ángeles Sandonas Feliz
