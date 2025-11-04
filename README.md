# GestionGimnasio: Sistema de Gestión Orientada a Objetos para Gimnasios 🏋️

## 📄 Descripción General del Proyecto

**GestionGimnasio** es una aplicación de gestión en modo **consola (CLI)** desarrollada íntegramente en **Java**. El objetivo principal es simular y gestionar la operativa diaria de un gimnasio, centrándose en la aplicación avanzada de los principios de la **Programación Orientada a Objetos (POO)**.

Este sistema permite la gestión completa y modular de usuarios y actividades, sirviendo como una base sólida para cualquier aplicación de gestión de clientes.

## ✨ Características y Funcionalidades Clave

El programa principal está diseñado con un menú modular para la gestión de las siguientes áreas:

* **Gestión de Usuarios (Socios y Monitores):**
    * Implementación de **Herencia y Polimorfismo** (clases `Socio`, `SocioPremium`, `Monitor` heredan de `Persona`).
    * Gestión de altas, bajas y la capacidad de **convertir un Socio en Socio Premium**.
    * Funcionalidad para **inactivar automáticamente** a socios inactivos.
* **Gestión de Actividades:**
    * Creación y eliminación de actividades, incluyendo validación para evitar borrar actividades con socios inscritos.
* **Consultas y Estadísticas:**
    * Listado segmentado de personas (Monitores, Socios, Premium).
    * Cálculo y listado de rankings: **N mejores actividades** (por valoración y calorías) y **N mejores monitores**.

## 🛠️ Tecnologías y Conceptos Implementados

Este proyecto demuestra un conocimiento sólido en los siguientes conceptos de Java y desarrollo de software:

* **Lenguaje Core:** **Java** (JDK).
* **POO Avanzada:**
    * Uso de **Clases Abstractas** (`Persona`), **Herencia**, y **Polimorfismo**.
    * Implementación de la **Interfaz `Valorable`** para calcular la puntuación de Monitores y Actividades.
* **Estructura de Datos:** Manejo eficiente de colecciones con **`ArrayList`** para almacenar y gestionar usuarios y actividades.
* **Calidad de Código y Estándares:**
    * Aplicación de **Programación Orientada a Objetos** y principios de **reutilización de código**.
    * Uso de **`equals()`** y **`compareTo()`** (Interfaz `Comparable`) para la gestión de colecciones.
* **Manejo de Errores:**
    * Validación de datos de entrada (DNI, Teléfono, Códigos Postales) mediante **expresiones regulares**.
    * Control y manejo de excepciones (`IllegalArgumentException`) para garantizar la robustez del programa.

## 🚀 Cómo Ejecutar el Proyecto

Este proyecto se ejecuta en cualquier entorno de desarrollo Java estándar (IDE).

1.  **Clonar Repositorio:**
    ```bash
    git clone https://github.com/daaviddieeguez/GestionGimnasio.git
    ```
2.  **Abrir en IDE:** Abrir la carpeta del proyecto en su IDE preferido (ej. IntelliJ IDEA o NetBeans).
3.  **Ejecutar:** Compilar y ejecutar la clase principal (el punto de entrada del programa).
4.  **Datos de Prueba:** El programa incluye una **precarga de datos** inicial para permitir la prueba inmediata de todas las funcionalidades, estadísticas y rankings.
