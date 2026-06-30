package pe.edu.utp.granmente.dto.project;

public record MiniProyectoDto(
        Long id,
        String titulo,
        String area,
        String nivel,
        String duracion,
        String descripcion,
        String entregable
) {
}
