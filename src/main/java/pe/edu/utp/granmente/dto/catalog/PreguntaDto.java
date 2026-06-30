package pe.edu.utp.granmente.dto.catalog;

import java.util.List;

public record PreguntaDto(Long id, int orden, String texto, List<OpcionDto> opciones) {
}
