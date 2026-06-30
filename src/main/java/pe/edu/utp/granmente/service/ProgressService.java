package pe.edu.utp.granmente.service;

import pe.edu.utp.granmente.dto.student.MaturityDto;
import pe.edu.utp.granmente.dto.student.StudentProgressDto;
import pe.edu.utp.granmente.entity.InterestKey;
import pe.edu.utp.granmente.entity.ProgresoAlumno;
import pe.edu.utp.granmente.entity.Usuario;

public interface ProgressService {
    ProgresoAlumno ensureProgress(Usuario usuario);
    StudentProgressDto getProgress(String email);
    MaturityDto maturity(int surveys);
    InterestKey topKey(ProgresoAlumno progress);
}
