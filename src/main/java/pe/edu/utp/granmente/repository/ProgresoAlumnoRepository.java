package pe.edu.utp.granmente.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.granmente.entity.ProgresoAlumno;
import pe.edu.utp.granmente.entity.Usuario;

public interface ProgresoAlumnoRepository extends JpaRepository<ProgresoAlumno, Long> {
    Optional<ProgresoAlumno> findByEstudiante(Usuario estudiante);
}
