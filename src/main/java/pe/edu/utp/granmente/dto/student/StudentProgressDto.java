package pe.edu.utp.granmente.dto.student;

import java.util.List;
import pe.edu.utp.granmente.dto.catalog.PerfilStemDto;
import pe.edu.utp.granmente.dto.recommendation.RecomendacionDto;

public record StudentProgressDto(
        String nombre,
        String grado,
        int encuestas,
        int xp,
        MaturityDto madurez,
        PerfilStemDto perfil,
        List<InterestScoreDto> intereses,
        List<RecomendacionDto> recomendaciones
) {
}
