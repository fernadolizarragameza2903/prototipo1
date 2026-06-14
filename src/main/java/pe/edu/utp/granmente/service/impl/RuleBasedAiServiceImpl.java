package pe.edu.utp.granmente.service.impl;

import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.granmente.dto.ai.ChatResponseDto;
import pe.edu.utp.granmente.dto.project.MiniProyectoDto;
import pe.edu.utp.granmente.dto.student.StudentProgressDto;
import pe.edu.utp.granmente.service.AiService;
import pe.edu.utp.granmente.service.CatalogService;
import pe.edu.utp.granmente.service.ProgressService;

@Service
@Transactional(readOnly = true)
public class RuleBasedAiServiceImpl implements AiService {

    private final ProgressService progressService;
    private final CatalogService catalogService;

    public RuleBasedAiServiceImpl(ProgressService progressService, CatalogService catalogService) {
        this.progressService = progressService;
        this.catalogService = catalogService;
    }

    @Override
    public ChatResponseDto reply(String email, String message) {
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
}
