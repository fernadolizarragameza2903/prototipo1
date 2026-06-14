package pe.edu.utp.granmente.dto.catalog;

import java.util.List;
import pe.edu.utp.granmente.entity.VideoRecurso;

public record RutaStemDto(
        String key,
        String titulo,
        String subtitulo,
        String carrera,
        List<String> temas,
        List<VideoRecurso> videos,
        List<String> proyectos
) {
}
