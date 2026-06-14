package pe.edu.utp.granmente.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.granmente.dto.catalog.PreguntaDto;
import pe.edu.utp.granmente.dto.catalog.RutaStemDto;
import pe.edu.utp.granmente.dto.project.MiniProyectoDto;
import pe.edu.utp.granmente.entity.InterestKey;
import pe.edu.utp.granmente.entity.ProgresoAlumno;
import pe.edu.utp.granmente.entity.Usuario;
import pe.edu.utp.granmente.mapper.CatalogMapper;
import pe.edu.utp.granmente.repository.MiniProyectoRepository;
import pe.edu.utp.granmente.repository.PreguntaEncuestaRepository;
import pe.edu.utp.granmente.repository.RutaStemRepository;
import pe.edu.utp.granmente.repository.UsuarioRepository;
import pe.edu.utp.granmente.service.CatalogService;
import pe.edu.utp.granmente.service.ProgressService;
import pe.edu.utp.granmente.service.RecommendationService;

@Service
@Transactional(readOnly = true)
public class CatalogServiceImpl implements CatalogService {

    private final PreguntaEncuestaRepository preguntaRepository;
    private final RutaStemRepository rutaRepository;
    private final MiniProyectoRepository proyectoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProgressService progressService;
    private final RecommendationService recommendationService;

    public CatalogServiceImpl(PreguntaEncuestaRepository preguntaRepository, RutaStemRepository rutaRepository,
                              MiniProyectoRepository proyectoRepository, UsuarioRepository usuarioRepository,
                              ProgressService progressService, RecommendationService recommendationService) {
        this.preguntaRepository = preguntaRepository;
        this.rutaRepository = rutaRepository;
        this.proyectoRepository = proyectoRepository;
        this.usuarioRepository = usuarioRepository;
        this.progressService = progressService;
        this.recommendationService = recommendationService;
    }

    @Override
    public List<PreguntaDto> preguntas() {
        return preguntaRepository.findAllByOrderByOrdenAsc().stream().map(CatalogMapper::toDto).toList();
    }

    @Override
    public List<RutaStemDto> rutas() {
        return rutaRepository.findAll().stream().map(CatalogMapper::toDto).toList();
    }

    @Override
    public List<MiniProyectoDto> proyectos(String filtro, String studentEmail) {
        String normalized = filtro == null ? "recomendados" : filtro.toLowerCase();
        if ("todos".equals(normalized)) {
            return proyectoRepository.findAll().stream().map(CatalogMapper::toDto).toList();
        }
        if ("recomendados".equals(normalized) && studentEmail != null) {
            Usuario usuario = usuarioRepository.findByEmailIgnoreCase(studentEmail).orElse(null);
            if (usuario != null) {
                ProgresoAlumno progress = progressService.ensureProgress(usuario);
                InterestKey top = progressService.topKey(progress);
                List<InterestKey> areas = recommendationService.relatedProjectAreas(top);
                return proyectoRepository.findAll().stream()
                        .filter(project -> areas.contains(project.getArea()))
                        .map(CatalogMapper::toDto)
                        .toList();
            }
        }
        InterestKey area = parseArea(normalized);
        return proyectoRepository.findByArea(area).stream().map(CatalogMapper::toDto).toList();
    }

    private InterestKey parseArea(String value) {
        return switch (value) {
            case "ia" -> InterestKey.IA;
            case "datos" -> InterestKey.DATOS;
            case "ciencia" -> InterestKey.CIENCIA;
            case "ingenieria" -> InterestKey.INGENIERIA;
            case "robotica" -> InterestKey.ROBOTICA;
            case "espacio" -> InterestKey.ESPACIO;
            default -> InterestKey.PROGRAMACION;
        };
    }
}
