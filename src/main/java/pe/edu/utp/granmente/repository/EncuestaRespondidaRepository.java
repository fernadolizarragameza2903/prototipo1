package pe.edu.utp.granmente.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.granmente.entity.EncuestaRespondida;
import pe.edu.utp.granmente.entity.Usuario;

public interface EncuestaRespondidaRepository extends JpaRepository<EncuestaRespondida, Long> {
    List<EncuestaRespondida> findTop8ByEstudianteOrderByFechaDesc(Usuario estudiante);
    long countByEstudiante(Usuario estudiante);
}
