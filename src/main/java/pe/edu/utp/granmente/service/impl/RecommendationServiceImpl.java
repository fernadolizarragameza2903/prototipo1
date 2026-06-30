package pe.edu.utp.granmente.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.granmente.dto.recommendation.RecomendacionDto;
import pe.edu.utp.granmente.entity.InterestKey;
import pe.edu.utp.granmente.entity.ProgresoAlumno;
import pe.edu.utp.granmente.mapper.CatalogMapper;
import pe.edu.utp.granmente.repository.RecomendacionProgramacionRepository;
import pe.edu.utp.granmente.service.RecommendationService;

@Service
@Transactional(readOnly = true)
public class RecommendationServiceImpl implements RecommendationService {

    private final RecomendacionProgramacionRepository repository;

    public RecommendationServiceImpl(RecomendacionProgramacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RecomendacionDto> programmingRecommendations(ProgresoAlumno progress) {
        List<String> tags = progress.getPuntajes().stream()
                .map(score -> score.getInterestKey().name().toLowerCase())
                .toList();
        List<RecomendacionDto> selected = repository.findByTagIn(tags).stream()
                .map(CatalogMapper::toDto)
                .limit(3)
                .toList();
        if (!selected.isEmpty()) {
            return selected;
        }
        return repository.findByTagIn(List.of("videojuegos", "app", "logica")).stream()
                .map(CatalogMapper::toDto)
                .limit(3)
                .toList();
    }

    @Override
    public List<InterestKey> relatedProjectAreas(InterestKey topKey) {
        return switch (topKey) {
            case PROGRAMACION -> List.of(InterestKey.PROGRAMACION, InterestKey.IA, InterestKey.DATOS);
            case LOGICA -> List.of(InterestKey.DATOS, InterestKey.PROGRAMACION, InterestKey.IA);
            case CIENCIA -> List.of(InterestKey.CIENCIA, InterestKey.DATOS);
            case INGENIERIA -> List.of(InterestKey.INGENIERIA, InterestKey.ROBOTICA);
            case ROBOTICA -> List.of(InterestKey.ROBOTICA, InterestKey.INGENIERIA, InterestKey.PROGRAMACION);
            case ESPACIO -> List.of(InterestKey.ESPACIO, InterestKey.INGENIERIA, InterestKey.DATOS);
            default -> new ArrayList<>(List.of(InterestKey.PROGRAMACION, InterestKey.IA));
        };
    }
}
