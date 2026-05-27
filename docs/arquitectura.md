# 4. Diagrama de Arquitectura

## Que se debe presentar

Un diagrama visual que explique como se conectan las partes del sistema. Para GRAN MENTE, la arquitectura es simulada porque el prototipo no usa backend real.

## Arquitectura propuesta

```text
Frontend Web
HTML / CSS / JavaScript
        |
        v
Controlador JavaScript
Eventos, formularios, navegacion y renderizado dinamico
        |
        v
Servicios Simulados IA
Analisis de respuestas, chatbot y recomendaciones
        |
        v
Datos de perfiles STEM
Carreras, videojuegos, videos, proyectos y rutas
        |
        v
Persistencia local
localStorage del navegador
        |
        v
APIs externas simuladas
YouTube, videojuegos y recursos educativos
```

## Especificacion por capa

- Frontend Web: contiene las pantallas visibles del sistema.
- Controlador JavaScript: procesa clicks, formularios, encuesta, chatbot y dashboard.
- Servicios Simulados IA: generan perfiles, recomendaciones y respuestas automáticas.
- Datos de perfiles STEM: arreglos JavaScript con carreras, videos, videojuegos y proyectos.
- Persistencia local: guarda progreso del alumno con `localStorage`.
- APIs externas simuladas: enlaces a YouTube y busquedas de videojuegos.

## Como presentarlo en GitHub

Guardar el diagrama como imagen en:

```text
docs/diagramas/arquitectura-gran-mente.png
```

Tambien se puede dejar este archivo Markdown como explicacion tecnica.