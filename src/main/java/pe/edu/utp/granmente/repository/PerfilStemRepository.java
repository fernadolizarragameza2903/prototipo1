package pe.edu.utp.granmente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.granmente.entity.InterestKey;
import pe.edu.utp.granmente.entity.PerfilStem;

public interface PerfilStemRepository extends JpaRepository<PerfilStem, InterestKey> {
}
