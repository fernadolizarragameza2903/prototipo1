package pe.edu.utp.granmente.dto.catalog;

import java.util.List;

public record PerfilStemDto(
        String key,
        String icono,
        String nombre,
        String descripcion,
        List<String> carreras,
        String videojuego,
        String categoriaVideojuego,
        String descripcionVideojuego,
        String video,
        String descripcionVideo,
        String videoUrl,
        String mensaje
) {
}
