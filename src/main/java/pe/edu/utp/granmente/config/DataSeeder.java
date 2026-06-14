package pe.edu.utp.granmente.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.edu.utp.granmente.entity.*;
import pe.edu.utp.granmente.repository.*;
import pe.edu.utp.granmente.service.ProgressService;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(
            @Value("${app.seed.enabled:true}") boolean enabled,
            PasswordEncoder encoder,
            UsuarioRepository usuarios,
            PerfilStemRepository perfiles,
            PreguntaEncuestaRepository preguntas,
            RutaStemRepository rutas,
            MiniProyectoRepository proyectos,
            RecomendacionProgramacionRepository recomendaciones,
            ProgressService progressService
    ) {
        return args -> {
            if (!enabled) {
                return;
            }
            seedProfiles(perfiles);
            seedQuestions(preguntas);
            seedRoutes(rutas);
            seedProjects(proyectos);
            seedRecommendations(recomendaciones);
            seedUsers(usuarios, encoder, progressService);
        };
    }

    private void seedUsers(UsuarioRepository usuarios, PasswordEncoder encoder, ProgressService progressService) {
        if (!usuarios.existsByEmailIgnoreCase("fernando@granmente.edu")) {
            Usuario fernando = user("Fernando", "fernando@granmente.edu", "5to de secundaria", Rol.ESTUDIANTE, encoder);
            usuarios.save(fernando);
            progressService.ensureProgress(fernando);
        }
        if (!usuarios.existsByEmailIgnoreCase("docente@granmente.edu")) {
            usuarios.save(user("Docente GRAN MENTE", "docente@granmente.edu", "Docente", Rol.DOCENTE, encoder));
        }
    }

    private Usuario user(String name, String email, String grade, Rol role, PasswordEncoder encoder) {
        Usuario user = new Usuario();
        user.setNombre(name);
        user.setEmail(email);
        user.setGrado(grade);
        user.setRol(role);
        user.setPasswordHash(encoder.encode("12345"));
        return user;
    }

    private void seedProfiles(PerfilStemRepository repository) {
        if (repository.count() > 0) return;
        repository.save(profile(InterestKey.PROGRAMACION, "PC", "Programador del Futuro",
                "Tu creatividad digital puede convertirse en apps, videojuegos e inteligencia artificial.",
                List.of("Ingenieria de Software", "Inteligencia Artificial", "Ciencia de Datos", "Diseno de Videojuegos"),
                "Roblox Studio", "Programacion y videojuegos",
                "Permite crear mundos, scripts y experiencias interactivas mientras aprende logica.",
                "Como crear videojuegos", "Introduccion practica para entender como se empieza a programar juegos.",
                "https://www.youtube.com/results?search_query=como+crear+videojuegos+para+principiantes",
                "Tienes potencial para crear soluciones tecnologicas e innovadoras."));
        repository.save(profile(InterestKey.LOGICA, "LG", "Explorador Tecnologico",
                "Disfrutas analizar patrones, resolver retos y encontrar respuestas con precision.",
                List.of("Ciencia de Datos", "Ingenieria de Software", "Inteligencia Artificial", "Ciberseguridad"),
                "Portal 2", "Logica y resolucion de problemas",
                "Entrena pensamiento espacial, causa y efecto, y solucion de retos complejos.",
                "Que es la Inteligencia Artificial", "Video recomendado para conectar logica, algoritmos y tecnologia inteligente.",
                "https://www.youtube.com/results?search_query=que+es+la+inteligencia+artificial+para+estudiantes",
                "Tu pensamiento logico puede ayudarte a resolver problemas reales."));
        repository.save(profile(InterestKey.CIENCIA, "CI", "Cientifico Creativo",
                "Tu curiosidad te impulsa a experimentar, observar y descubrir como funciona el mundo.",
                List.of("Biotecnologia", "Ciencias Ambientales", "Ciencia de Datos", "Ingenieria Quimica"),
                "Minecraft Education", "Ciencia y experimentacion",
                "Ayuda a explorar ciencia, creatividad y retos educativos mediante actividades interactivas.",
                "Experimentos STEM para estudiantes", "Ideas de experimentos para aprender ciencia de forma visual y practica.",
                "https://www.youtube.com/results?search_query=experimentos+stem+para+estudiantes",
                "Tu curiosidad puede convertirse en descubrimientos utiles."));
        repository.save(profile(InterestKey.INGENIERIA, "IN", "Ingeniero Innovador",
                "Te gusta construir, liderar proyectos y transformar ideas en soluciones tangibles.",
                List.of("Mecatronica", "Ingenieria Aeroespacial", "Robotica", "Ingenieria Industrial"),
                "Scrap Mechanic", "Construccion e ingenieria",
                "Permite crear mecanismos, probar estructuras y pensar como un ingeniero.",
                "Introduccion a la ingenieria y robotica", "Explica como se conectan diseno, maquinas y solucion de problemas.",
                "https://www.youtube.com/results?search_query=introduccion+a+la+ingenieria+y+robotica+para+estudiantes",
                "Tu talento para construir puede crear tecnologias utiles."));
        repository.save(profile(InterestKey.ROBOTICA, "RB", "Constructor Robotico",
                "Te atrae unir mecanica, electronica y programacion para crear maquinas inteligentes.",
                List.of("Robotica", "Mecatronica", "Inteligencia Artificial", "Automatizacion Industrial"),
                "Human Resource Machine", "Algoritmos y automatizacion",
                "Convierte instrucciones y logica en soluciones, como una introduccion a programar maquinas.",
                "Introduccion a la Robotica", "Recurso para entender sensores, actuadores y programacion de robots.",
                "https://www.youtube.com/results?search_query=introduccion+a+la+robotica+para+estudiantes",
                "Puedes combinar imaginacion y tecnologia para crear robots."));
        repository.save(profile(InterestKey.ESPACIO, "SP", "Explorador Espacial",
                "Tu interes por el universo puede llevarte hacia fisica, ingenieria y tecnologia aeroespacial.",
                List.of("Ingenieria Aeroespacial", "Astrofisica", "Ciencia de Datos", "Ingenieria de Software"),
                "Kerbal Space Program", "Espacio, fisica e ingenieria",
                "Permite disenar cohetes, probar misiones y aprender principios de vuelo espacial.",
                "Como funciona la NASA", "Contenido para descubrir misiones espaciales y profesiones STEM conectadas al espacio.",
                "https://www.youtube.com/results?search_query=como+funciona+la+NASA+para+estudiantes",
                "Tu fascinacion por el espacio puede abrir una ruta STEM avanzada."));
    }

    private PerfilStem profile(InterestKey key, String icon, String name, String description, List<String> careers,
                               String game, String gameCategory, String gameDescription, String video,
                               String videoDescription, String videoUrl, String message) {
        PerfilStem profile = new PerfilStem();
        profile.setKey(key);
        profile.setIcono(icon);
        profile.setNombre(name);
        profile.setDescripcion(description);
        profile.setCarreras(careers);
        profile.setVideojuego(game);
        profile.setCategoriaVideojuego(gameCategory);
        profile.setDescripcionVideojuego(gameDescription);
        profile.setVideo(video);
        profile.setDescripcionVideo(videoDescription);
        profile.setVideoUrl(videoUrl);
        profile.setMensaje(message);
        return profile;
    }

    private void seedQuestions(PreguntaEncuestaRepository repository) {
        if (repository.count() > 0) return;
        repository.save(question(1, "Que actividad te gusta mas?",
                option(1, "Crear videojuegos", InterestKey.PROGRAMACION),
                option(2, "Resolver problemas", InterestKey.LOGICA),
                option(3, "Hacer experimentos", InterestKey.CIENCIA),
                option(4, "Construir cosas", InterestKey.INGENIERIA)));
        repository.save(question(2, "Que tema te interesa mas?",
                option(1, "Tecnologia", InterestKey.PROGRAMACION),
                option(2, "Espacio", InterestKey.ESPACIO),
                option(3, "Robots", InterestKey.ROBOTICA),
                option(4, "Ciencia", InterestKey.CIENCIA)));
        repository.save(question(3, "Como prefieres aprender?",
                option(1, "Videos", InterestKey.VISUAL),
                option(2, "Juegos", InterestKey.PROGRAMACION),
                option(3, "Proyectos", InterestKey.INGENIERIA),
                option(4, "Experimentos", InterestKey.CIENCIA)));
        repository.save(question(4, "Que habilidad te representa mas?",
                option(1, "Creatividad", InterestKey.PROGRAMACION),
                option(2, "Logica", InterestKey.LOGICA),
                option(3, "Curiosidad", InterestKey.CIENCIA),
                option(4, "Liderazgo", InterestKey.INGENIERIA)));
        repository.save(question(5, "Que te gustaria crear?",
                option(1, "Una app", InterestKey.PROGRAMACION),
                option(2, "Un robot", InterestKey.ROBOTICA),
                option(3, "Un laboratorio", InterestKey.CIENCIA),
                option(4, "Un videojuego", InterestKey.PROGRAMACION)));
    }

    private PreguntaEncuesta question(int order, String text, OpcionRespuesta... options) {
        PreguntaEncuesta question = new PreguntaEncuesta();
        question.setOrden(order);
        question.setTexto(text);
        for (OpcionRespuesta option : options) question.addOpcion(option);
        return question;
    }

    private OpcionRespuesta option(int order, String label, InterestKey key) {
        OpcionRespuesta option = new OpcionRespuesta();
        option.setOrden(order);
        option.setEtiqueta(label);
        option.setInterestKey(key);
        return option;
    }

    private void seedRoutes(RutaStemRepository repository) {
        if (repository.count() > 0) return;
        repository.save(route("ciencia", "Ciencia", "Biotecnologia, ambiente, salud y experimentos escolares.",
                List.of("Metodo cientifico", "Biologia y genetica", "Quimica basica", "Cuidado ambiental"),
                List.of(new VideoRecurso("Experimentos STEM para secundaria", "https://www.youtube.com/results?search_query=experimentos+stem+para+secundaria"),
                        new VideoRecurso("Introduccion a la biotecnologia", "https://www.youtube.com/results?search_query=biotecnologia+para+estudiantes+secundaria")),
                List.of("Mini invernadero con registro de crecimiento", "Filtro de agua casero", "Reporte de calidad del aire en la escuela"),
                "Carreras relacionadas: Biotecnologia, Medicina, Ciencias Ambientales, Ingenieria Quimica."));
        repository.save(route("tecnologia", "Tecnologia", "Programacion, inteligencia artificial, apps, videojuegos y datos.",
                List.of("HTML, CSS y JavaScript", "Inteligencia artificial", "Ciberseguridad", "Diseno de apps"),
                List.of(new VideoRecurso("Aprender programacion desde cero", "https://www.youtube.com/results?search_query=programacion+desde+cero+para+estudiantes"),
                        new VideoRecurso("Que es la inteligencia artificial", "https://www.youtube.com/results?search_query=que+es+la+inteligencia+artificial+para+estudiantes")),
                List.of("Pagina web personal", "Chatbot con respuestas por palabras clave", "Mini videojuego de reflejos"),
                "Carreras relacionadas: Ingenieria de Software, Ciencia de Datos, IA, Ciberseguridad."));
        repository.save(route("ingenieria", "Ingenieria", "Robotica, estructuras, prototipos, maquinas y solucion de problemas.",
                List.of("Diseno de prototipos", "Robotica basica", "Electronica inicial", "Mecanismos y energia"),
                List.of(new VideoRecurso("Introduccion a la robotica", "https://www.youtube.com/results?search_query=introduccion+a+la+robotica+para+estudiantes"),
                        new VideoRecurso("Proyectos de ingenieria para secundaria", "https://www.youtube.com/results?search_query=proyectos+de+ingenieria+para+secundaria")),
                List.of("Puente de palitos con prueba de resistencia", "Robot simulado en cuadricula", "Sistema de riego automatico maqueta"),
                "Carreras relacionadas: Mecatronica, Robotica, Ingenieria Civil, Ingenieria Industrial."));
        repository.save(route("matematicas", "Matematicas", "Logica, patrones, estadistica, predicciones y analisis de datos.",
                List.of("Estadistica", "Probabilidad", "Algebra aplicada", "Graficos e interpretacion de datos"),
                List.of(new VideoRecurso("Estadistica facil para estudiantes", "https://www.youtube.com/results?search_query=estadistica+facil+para+estudiantes"),
                        new VideoRecurso("Matematicas aplicadas a datos", "https://www.youtube.com/results?search_query=matematicas+aplicadas+a+ciencia+de+datos")),
                List.of("Encuesta escolar con graficos", "Prediccion de notas con promedios", "Dashboard simple de habitos de estudio"),
                "Carreras relacionadas: Ciencia de Datos, Estadistica, Economia, Ingenieria de Sistemas."));
    }

    private RutaStem route(String key, String title, String subtitle, List<String> themes, List<VideoRecurso> videos,
                           List<String> projects, String career) {
        RutaStem route = new RutaStem();
        route.setKey(key);
        route.setTitulo(title);
        route.setSubtitulo(subtitle);
        route.setTemas(themes);
        route.setVideos(videos);
        route.setProyectos(projects);
        route.setCarrera(career);
        return route;
    }

    private void seedProjects(MiniProyectoRepository repository) {
        if (repository.count() > 0) return;
        repository.save(project("Calculadora STEM", InterestKey.PROGRAMACION, "Inicial", "30 min", "Crear una calculadora visual con HTML, CSS y JavaScript.", "Una pagina que suma, resta y muestra resultados."));
        repository.save(project("Mini videojuego de reflejos", InterestKey.PROGRAMACION, "Inicial", "45 min", "Un boton aparece en lugares distintos y el alumno gana puntos al hacer clic.", "Juego con puntos, tiempo y reinicio."));
        repository.save(project("Chatbot de reglas", InterestKey.IA, "Intermedio", "50 min", "Crear un asistente que responde segun palabras clave.", "Chat simple que recomienda una ruta STEM."));
        repository.save(project("Simulador de robot", InterestKey.ROBOTICA, "Intermedio", "60 min", "Mover un robot en una cuadricula usando botones de direccion.", "Robot visual con instrucciones basicas."));
        repository.save(project("Dashboard de habitos", InterestKey.DATOS, "Intermedio", "55 min", "Registrar horas de estudio y mostrar progreso con barras.", "Panel con metricas y recomendaciones."));
        repository.save(project("Experimento de germinacion", InterestKey.CIENCIA, "Inicial", "35 min", "Comparar el crecimiento de semillas con luz, sombra y poca agua.", "Tabla de observacion y conclusion cientifica."));
        repository.save(project("Puente resistente", InterestKey.INGENIERIA, "Inicial", "50 min", "Disenar un puente con palitos y medir cuanto peso soporta.", "Prototipo fisico y registro de prueba."));
        repository.save(project("Mision espacial interactiva", InterestKey.ESPACIO, "Inicial", "40 min", "Elegir combustible, destino y ver si la mision despega.", "Simulador de decisiones con resultado final."));
    }

    private MiniProyecto project(String title, InterestKey area, String level, String time, String description, String deliverable) {
        MiniProyecto project = new MiniProyecto();
        project.setTitulo(title);
        project.setArea(area);
        project.setNivel(level);
        project.setDuracion(time);
        project.setDescripcion(description);
        project.setEntregable(deliverable);
        return project;
    }

    private void seedRecommendations(RecomendacionProgramacionRepository repository) {
        if (repository.count() > 0) return;
        repository.save(recommendation("videojuegos", "Crear tu primer videojuego 2D", "Inicial", "Ideal si le interesa programar construyendo algo jugable.", List.of("Variables y condicionales", "Movimiento de personaje", "Diseno de niveles simples")));
        repository.save(recommendation("app", "Disenar una app escolar", "Inicial", "Conecta creatividad, solucion de problemas y tecnologia util.", List.of("Prototipo en papel", "Pantallas con HTML y CSS", "Botones con JavaScript")));
        repository.save(recommendation("robotica", "Programar un robot basico", "Intermedio", "Perfecto si le interesan robots, sensores y automatizacion.", List.of("Instrucciones secuenciales", "Sensores simulados", "Respuestas automaticas")));
        repository.save(recommendation("logica", "Retos de logica para programadores", "Inicial", "Fortalece la forma de pensar que usan los desarrolladores.", List.of("Resolver patrones", "Crear algoritmos simples", "Depurar errores")));
        repository.save(recommendation("ia", "Mini asistente con IA simulada", "Intermedio", "Ayuda a entender como una app puede responder con reglas inteligentes.", List.of("Lista de respuestas", "Palabras clave", "Recomendaciones concisas")));
    }

    private RecomendacionProgramacion recommendation(String tag, String title, String level, String why, List<String> steps) {
        RecomendacionProgramacion recommendation = new RecomendacionProgramacion();
        recommendation.setTag(tag);
        recommendation.setTitulo(title);
        recommendation.setNivel(level);
        recommendation.setMotivo(why);
        recommendation.setPasos(steps);
        return recommendation;
    }
}
