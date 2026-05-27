# GRAN MENTE

**Descubre tu potencial STEM**

GRAN MENTE es un prototipo web educativo para orientar estudiantes hacia carreras STEM mediante una experiencia moderna, responsive y visualmente impactante. La maqueta simula IA, recomendaciones personalizadas, dashboard del alumno, panel docente, arquitectura tecnica, APIs, base de datos y seguridad.

## Resumen de lo realizado

Se desarrollo una maqueta web completa para presentar la idea de GRAN MENTE como plataforma EdTech. El proyecto incluye una landing page, login demo, encuesta vocacional con resultados STEM, recomendaciones de carreras, videojuegos y videos, chatbot IA simulado, dashboard del alumno, mini proyectos, panel docente y una arquitectura simulada con APIs, base de datos y seguridad. Todo funciona de forma estatica con HTML, CSS y JavaScript, usando `localStorage` para simular persistencia de datos.

## Objetivo

Demostrar como una plataforma EdTech podria ayudar a estudiantes de secundaria a descubrir intereses en ciencia, tecnologia, ingenieria y matematicas usando encuestas, gamificacion y recomendaciones dinamicas.

## Funcionalidades

- Landing page con login demo.
- Dashboard del alumno con XP, madurez vocacional e intereses detectados.
- Encuesta IA con resultados STEM.
- Recomendaciones de carreras, videojuegos y videos de YouTube.
- Chatbot IA simulado con respuestas por contexto.
- Mini proyectos filtrables por area STEM.
- Panel docente con metricas de aula, alertas y tabla de estudiantes.
- Arquitectura simulada del sistema.
- APIs simuladas, base de datos propuesta y controles de seguridad.
- Persistencia local con `localStorage`.
- Diseno responsive para laptop, tablet y celular.

## Entregables del trabajo

- [3. Prototipo tipo Balsamiq](docs/prototipo-balsamiq.md): pantallas que se deben presentar y capturas sugeridas.
- [4. Diagrama de arquitectura](docs/arquitectura.md): capas del sistema y explicacion tecnica.
- [5. Estructura del repositorio GitHub](docs/estructura-github.md): organizacion de carpetas y buenas practicas.
- [6. Seleccion tecnologica justificada](docs/seleccion-tecnologica.md): motivo de uso de cada tecnologia.
- [7. Codigo funcional inicial](docs/codigo-funcional.md): funciones que deben demostrarse en la maqueta.
- [8. Enlace GitHub](docs/enlace-github.md): recomendaciones para presentar repositorio, commits y GitHub Pages.

## Tecnologias

- HTML5
- CSS3
- JavaScript Vanilla
- Canvas para particulas animadas
- CSS Grid, Flexbox y media queries
- GitHub Pages como despliegue sugerido

No usa backend, Node.js, frameworks, APIs reales ni IA real. Todo esta simulado desde JavaScript para representar la experiencia final.

## Estructura del proyecto

```text
prototipo1/
|-- index.html
|-- proyectos.html
|-- ia.html
|-- dashboard.html
|-- docente.html
|-- style.css
|-- script.js
|-- css/
|   `-- estilos.css
|-- js/
|   `-- main.js
|-- docs/
|   |-- prototipo-balsamiq.md
|   |-- arquitectura.md
|   |-- estructura-github.md
|   |-- seleccion-tecnologica.md
|   |-- codigo-funcional.md
|   `-- enlace-github.md
|-- img/
|   `-- hero-brain.png
`-- README.md
```

## Flujo de uso

1. Abrir `index.html`.
2. Iniciar sesion con el usuario demo `Fernando` y la contrasena `12345`.
3. Completar la encuesta STEM.
4. Revisar resultados, carreras, videojuego recomendado y video de YouTube.
5. Entrar a `dashboard.html` para ver progreso y madurez.
6. Explorar `proyectos.html` para practicar.
7. Usar `ia.html` para conversar con el chatbot.
8. Abrir `docente.html` para mostrar el panel docente y la arquitectura simulada.

## Modulos simulados

### API

- `POST /api/auth/login`
- `POST /api/survey/analyze`
- `GET /api/student/progress`
- `GET /api/teacher/classroom`

### Base de datos

- `students`
- `surveys`
- `answers`
- `recommendations`
- `audit_logs`

### Seguridad

- Roles separados para estudiante y docente.
- Datos guardados solo en el navegador.
- Formularios con validacion basica.
- Enlaces externos protegidos con `rel="noopener"`.
- Arquitectura preparada para migrar a tokens, backend y base de datos real.

## Despliegue en GitHub Pages

1. Crear un repositorio en GitHub.
2. Subir todos los archivos del proyecto.
3. Entrar a **Settings > Pages**.
4. Seleccionar la rama `main` y la carpeta `/root`.
5. Guardar y abrir la URL generada por GitHub Pages.

## Datos academicos

- Proyecto: GRAN MENTE
- Curso: DISENO DE PRODUCTOS Y SERVICIOS
- Docente: GUEVARA JIMENEZ, JORGE ALFREDO
- Integrantes:
  - Gaslac, Jose Victor - U23247247
  - Lizarraga Meza, Fernando Daniel - U23250629
  - Pacco Gavidia, Iago Luciano - U25267964
- Anio: 2026

## Nota

Este proyecto es una maqueta academica. Las recomendaciones, la IA, el dashboard docente, las APIs y la base de datos son simulados para explicar el funcionamiento de una plataforma educativa real.
