package pe.edu.utp.granmente.dto.survey;

import java.util.List;
import pe.edu.utp.granmente.dto.catalog.PerfilStemDto;

public record SurveyResultDto(PerfilStemDto perfil, List<String> respuestas, int xpTotal) {
}
