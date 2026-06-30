package pe.edu.utp.granmente.service.impl;

import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.granmente.dto.ai.ChatResponseDto;
import pe.edu.utp.granmente.dto.project.MiniProyectoDto;
import pe.edu.utp.granmente.dto.student.StudentProgressDto;
import pe.edu.utp.granmente.entity.Rol;
import pe.edu.utp.granmente.entity.Usuario;
import pe.edu.utp.granmente.repository.UsuarioRepository;
import pe.edu.utp.granmente.service.AiService;
import pe.edu.utp.granmente.service.CatalogService;
import pe.edu.utp.granmente.service.ProgressService;

@Service
@Transactional(readOnly = true)
public class RuleBasedAiServiceImpl implements AiService {

    private final ProgressService progressService;
    private final CatalogService catalogService;
    private final UsuarioRepository usuarioRepository;

    public RuleBasedAiServiceImpl(ProgressService progressService, CatalogService catalogService, UsuarioRepository usuarioRepository) {
        this.progressService = progressService;
        this.catalogService = catalogService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public ChatResponseDto reply(String email, String message) {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(email).orElse(null);
        if (usuario != null && usuario.getRol() == Rol.DOCENTE) {
            return teacherReply(message);
        }
        StudentProgressDto progress = progressService.getProgress(email);
        String value = message.toLowerCase(Locale.ROOT);
        String answer;
        if (value.contains("madurez") || value.contains("nivel")) {
            answer = "%s. Has resuelto %d encuesta(s). Mientras mas encuestas completes, mas precisa sera tu ruta."
                    .formatted(progress.madurez().nombre(), progress.encuestas());
        } else if (value.contains("proyecto") || value.contains("hacer")) {
            MiniProyectoDto project = catalogService.proyectos("recomendados", email).stream().findFirst().orElse(null);
            answer = project == null
                    ? "Completa una encuesta para priorizar mini proyectos."
                    : "Proyecto recomendado: %s. Duracion: %s. Entregable: %s"
                    .formatted(project.titulo(), project.duracion(), project.entregable());
        } else if (value.contains("carrera") || value.contains("estudio")) {
            answer = "Ruta concisa: %s. Tu perfil actual es %s."
                    .formatted(String.join(", ", progress.perfil().carreras().stream().limit(3).toList()), progress.perfil().nombre());
        } else if (value.contains("program") || value.contains("app") || value.contains("videojuego")) {
            answer = "Empieza con HTML, CSS y JavaScript. Luego crea un mini juego o una app escolar visible.";
        } else if (value.contains("robot")) {
            answer = "Para robotica: logica, sensores simulados y automatizacion. JavaScript puede servir para prototipos.";
        } else if (value.contains("hola")) {
            answer = "Hola. Dime si te interesan apps, videojuegos, robots, datos o espacio.";
        } else {
            answer = "Respuesta breve: tu tendencia actual es %s. Recomiendo practicar programacion con proyectos pequenos."
                    .formatted(progress.perfil().nombre());
        }
        return new ChatResponseDto(answer, "fallback-reglas");
    }

    private ChatResponseDto teacherReply(String message) {
        String value = message.toLowerCase(Locale.ROOT);
        String answer;
        if (value.contains("rubrica") || value.contains("evalu")) {
            answer = "Rubrica breve: define logro tecnico, creatividad, explicacion del proceso y mejora iterativa. Usa 4 niveles y una evidencia concreta por criterio.";
        } else if (value.contains("retroaliment")) {
            answer = "Retroalimentacion util: empieza con un acierto observable, senala una mejora especifica y cierra con una siguiente accion medible para el alumno.";
        } else if (value.contains("herramient")) {
            answer = "Herramientas sugeridas: simuladores STEM, hojas de calculo para datos, editores visuales de codigo y tableros de seguimiento por equipo.";
        } else if (value.contains("actividad") || value.contains("ia")) {
            answer = "Actividad con IA: pide a cada equipo explicar su proyecto, generar una hipotesis de mejora y validar si la respuesta de la IA coincide con evidencias.";
        } else {
            answer = "Puedo ayudarte con rubricas, actividades, herramientas y frases de retroalimentacion para orientar mejor a tus alumnos.";
        }
        return new ChatResponseDto(answer, "fallback-docente");
    }
}
