package pe.edu.utp.granmente.service;

import java.util.List;
import pe.edu.utp.granmente.dto.catalog.PreguntaDto;
import pe.edu.utp.granmente.dto.catalog.RutaStemDto;
import pe.edu.utp.granmente.dto.project.MiniProyectoDto;

public interface CatalogService {
    List<PreguntaDto> preguntas();
    List<RutaStemDto> rutas();
    List<MiniProyectoDto> proyectos(String filtro, String studentEmail);
}
