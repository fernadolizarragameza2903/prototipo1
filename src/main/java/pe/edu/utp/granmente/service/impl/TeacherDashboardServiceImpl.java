package pe.edu.utp.granmente.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.granmente.dto.student.MaturityDto;
import pe.edu.utp.granmente.dto.teacher.StudentSummaryDto;
import pe.edu.utp.granmente.dto.teacher.TeacherDashboardDto;
import pe.edu.utp.granmente.entity.PerfilStem;
import pe.edu.utp.granmente.entity.ProgresoAlumno;
import pe.edu.utp.granmente.entity.Rol;
import pe.edu.utp.granmente.repository.PerfilStemRepository;
import pe.edu.utp.granmente.repository.ProgresoAlumnoRepository;
import pe.edu.utp.granmente.repository.UsuarioRepository;
import pe.edu.utp.granmente.service.ProgressService;
import pe.edu.utp.granmente.service.TeacherDashboardService;

@Service
@Transactional(readOnly = true)
public class TeacherDashboardServiceImpl implements TeacherDashboardService {

    private final UsuarioRepository usuarioRepository;
    private final ProgresoAlumnoRepository progressRepository;
    private final PerfilStemRepository perfilRepository;
    private final ProgressService progressService;

    public TeacherDashboardServiceImpl(UsuarioRepository usuarioRepository, ProgresoAlumnoRepository progressRepository,
                                       PerfilStemRepository perfilRepository, ProgressService progressService) {
        this.usuarioRepository = usuarioRepository;
        this.progressRepository = progressRepository;
        this.perfilRepository = perfilRepository;
        this.progressService = progressService;
    }

    @Override
    public TeacherDashboardDto classroom() {
        List<StudentSummaryDto> resumenes = usuarioRepository.findAll().stream()
                .filter(user -> user.getRol() == Rol.ESTUDIANTE)
                .map(user -> {
                    ProgresoAlumno progress = progressService.ensureProgress(user);
                    PerfilStem perfil = perfilRepository.findById(progressService.topKey(progress)).orElse(null);
                    MaturityDto maturity = progressService.maturity(progress.getEncuestasResueltas());
                    String estado = progress.getEncuestasResueltas() == 0 ? "Completar encuesta"
                            : progress.getXp() > 1000 ? "Alto interes" : "Activo";
                    return new StudentSummaryDto(user.getNombre(), user.getGrado(),
                            perfil == null ? "Programador del Futuro" : perfil.getNombre(),
                            maturity.nivel(), progress.getXp(), estado);
                })
                .toList();
        double average = resumenes.stream().mapToInt(StudentSummaryDto::madurez).average().orElse(0);
        int totalXp = resumenes.stream().mapToInt(StudentSummaryDto::xp).sum();
        List<String> topProfiles = resumenes.stream()
                .collect(Collectors.groupingBy(StudentSummaryDto::perfil, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .limit(3)
                .toList();
        List<String> alertas = List.of(
                "Completar encuesta inicial en estudiantes sin diagnostico.",
                "Formar equipos por intereses dominantes para mini proyectos.",
                "Reforzar evidencia practica en perfiles con madurez baja."
        );
        return new TeacherDashboardDto(resumenes.size(), Math.round(average * 10) / 10.0,
                totalXp, topProfiles, alertas, resumenes);
    }
}
