package pe.edu.utp.granmente.service;

import java.util.List;
import pe.edu.utp.granmente.dto.recommendation.RecomendacionDto;
import pe.edu.utp.granmente.entity.InterestKey;
import pe.edu.utp.granmente.entity.ProgresoAlumno;

public interface RecommendationService {
    List<RecomendacionDto> programmingRecommendations(ProgresoAlumno progress);
    List<InterestKey> relatedProjectAreas(InterestKey topKey);
}
