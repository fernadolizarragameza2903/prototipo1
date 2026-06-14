package pe.edu.utp.granmente.mapper;

import java.util.Comparator;
import pe.edu.utp.granmente.dto.catalog.OpcionDto;
import pe.edu.utp.granmente.dto.catalog.PerfilStemDto;
import pe.edu.utp.granmente.dto.catalog.PreguntaDto;
import pe.edu.utp.granmente.dto.catalog.RutaStemDto;
import pe.edu.utp.granmente.dto.project.MiniProyectoDto;
import pe.edu.utp.granmente.dto.recommendation.RecomendacionDto;
import pe.edu.utp.granmente.entity.*;

public final class CatalogMapper {
    private CatalogMapper() {
    }

    public static PerfilStemDto toDto(PerfilStem perfil) {
        return new PerfilStemDto(
                perfil.getKey().name().toLowerCase(),
                perfil.getIcono(),
                perfil.getNombre(),
                perfil.getDescripcion(),
                perfil.getCarreras(),
                perfil.getVideojuego(),
                perfil.getCategoriaVideojuego(),
                perfil.getDescripcionVideojuego(),
                perfil.getVideo(),
                perfil.getDescripcionVideo(),
                perfil.getVideoUrl(),
                perfil.getMensaje()
        );
    }

    public static PreguntaDto toDto(PreguntaEncuesta pregunta) {
        return new PreguntaDto(
                pregunta.getId(),
                pregunta.getOrden(),
                pregunta.getTexto(),
                pregunta.getOpciones().stream()
                        .sorted(Comparator.comparing(OpcionRespuesta::getOrden))
                        .map(CatalogMapper::toDto)
                        .toList()
        );
    }

    public static OpcionDto toDto(OpcionRespuesta opcion) {
        return new OpcionDto(
                opcion.getId(),
                opcion.getOrden(),
                opcion.getEtiqueta(),
                opcion.getInterestKey().name().toLowerCase()
        );
    }

    public static MiniProyectoDto toDto(MiniProyecto proyecto) {
        return new MiniProyectoDto(
                proyecto.getId(),
                proyecto.getTitulo(),
                proyecto.getArea().name().toLowerCase(),
                proyecto.getNivel(),
                proyecto.getDuracion(),
                proyecto.getDescripcion(),
                proyecto.getEntregable()
        );
    }

    public static RutaStemDto toDto(RutaStem ruta) {
        return new RutaStemDto(ruta.getKey(), ruta.getTitulo(), ruta.getSubtitulo(), ruta.getCarrera(),
                ruta.getTemas(), ruta.getVideos(), ruta.getProyectos());
    }

    public static RecomendacionDto toDto(RecomendacionProgramacion recomendacion) {
        return new RecomendacionDto(recomendacion.getTag(), recomendacion.getTitulo(),
                recomendacion.getNivel(), recomendacion.getMotivo(), recomendacion.getPasos());
    }
}
