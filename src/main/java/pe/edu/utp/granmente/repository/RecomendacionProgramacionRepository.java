package pe.edu.utp.granmente.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.granmente.entity.RecomendacionProgramacion;

public interface RecomendacionProgramacionRepository extends JpaRepository<RecomendacionProgramacion, Long> {
    List<RecomendacionProgramacion> findByTagIn(Collection<String> tags);
}
