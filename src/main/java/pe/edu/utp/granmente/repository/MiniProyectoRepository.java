package pe.edu.utp.granmente.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.granmente.entity.InterestKey;
import pe.edu.utp.granmente.entity.MiniProyecto;

public interface MiniProyectoRepository extends JpaRepository<MiniProyecto, Long> {
    List<MiniProyecto> findByArea(InterestKey area);
}
