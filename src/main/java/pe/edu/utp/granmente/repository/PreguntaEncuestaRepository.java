package pe.edu.utp.granmente.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.granmente.entity.PreguntaEncuesta;

public interface PreguntaEncuestaRepository extends JpaRepository<PreguntaEncuesta, Long> {
    List<PreguntaEncuesta> findAllByOrderByOrdenAsc();
}
