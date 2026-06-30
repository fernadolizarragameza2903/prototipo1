package pe.edu.utp.granmente.service.impl;

import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.granmente.dto.student.InterestScoreDto;
import pe.edu.utp.granmente.dto.student.MaturityDto;
import pe.edu.utp.granmente.dto.student.StudentProgressDto;
import pe.edu.utp.granmente.entity.*;
import pe.edu.utp.granmente.exception.ResourceNotFoundException;
import pe.edu.utp.granmente.mapper.CatalogMapper;
import pe.edu.utp.granmente.repository.PerfilStemRepository;
import pe.edu.utp.granmente.repository.ProgresoAlumnoRepository;
import pe.edu.utp.granmente.repository.UsuarioRepository;
import pe.edu.utp.granmente.service.ProgressService;
import pe.edu.utp.granmente.service.RecommendationService;

@Service
@Transactional
public class ProgressServiceImpl implements ProgressService {

    private final ProgresoAlumnoRepository progressRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerfilStemRepository perfilRepository;
    private final RecommendationService recommendationService;

    public ProgressServiceImpl(ProgresoAlumnoRepository progressRepository, UsuarioRepository usuarioRepository,
                               PerfilStemRepository perfilRepository, RecommendationService recommendationService) {
        this.progressRepository = progressRepository;
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.recommendationService = recommendationService;
    }

    @Override
    public ProgresoAlumno ensureProgress(Usuario usuario) {
        return progressRepository.findByEstudiante(usuario).orElseGet(() -> {
            ProgresoAlumno progress = new ProgresoAlumno();
            progress.setEstudiante(usuario);
            progress.setXp(0);
            progress.setEncuestasResueltas(0);
            progress.setPerfilDominante(InterestKey.PROGRAMACION);
            return progressRepository.save(progress);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public StudentProgressDto getProgress(String email) {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado."));
        ProgresoAlumno progress = progressRepository.findByEstudiante(usuario).orElseGet(() -> ensureProgress(usuario));
        InterestKey top = topKey(progress);
        PerfilStem perfil = perfilRepository.findById(top).orElseGet(() -> perfilRepository.findById(InterestKey.PROGRAMACION).orElseThrow());
        int total = progress.getPuntajes().stream().mapToInt(PuntajeInteres::getPuntaje).sum();
        List<InterestScoreDto> intereses = progress.getPuntajes().stream()
                .sorted(Comparator.comparing(PuntajeInteres::getPuntaje).reversed())
                .map(score -> new InterestScoreDto(score.getInterestKey().name().toLowerCase(), score.getPuntaje(),
                        total == 0 ? 0 : Math.round((score.getPuntaje() * 100f) / total)))
                .toList();
        return new StudentProgressDto(usuario.getNombre(), usuario.getGrado(), progress.getEncuestasResueltas(),
                progress.getXp(), maturity(progress.getEncuestasResueltas()), CatalogMapper.toDto(perfil),
                intereses, recommendationService.programmingRecommendations(progress));
    }

    @Override
    public MaturityDto maturity(int surveys) {
        if (surveys >= 8) return new MaturityDto("Madurez Experta", 5, "Recomendaciones muy concretas por patron repetido.");
        if (surveys >= 5) return new MaturityDto("Madurez Avanzada", 4, "La plataforma detecta gustos dominantes con mayor precision.");
        if (surveys >= 3) return new MaturityDto("Madurez Intermedia", 3, "Ya existen preferencias suficientes para recomendar mejor.");
        if (surveys >= 1) return new MaturityDto("Madurez Inicial", 2, "Primeras senales de interes vocacional.");
        return new MaturityDto("Sin diagnostico", 1, "Completa encuestas para mejorar la precision.");
    }

    @Override
    public InterestKey topKey(ProgresoAlumno progress) {
        InterestKey key = progress.getPuntajes().stream()
                .max(Comparator.comparing(PuntajeInteres::getPuntaje))
                .map(PuntajeInteres::getInterestKey)
                .orElse(progress.getPerfilDominante() == null ? InterestKey.PROGRAMACION : progress.getPerfilDominante());
        return key == InterestKey.VISUAL ? InterestKey.PROGRAMACION : key;
    }
}
