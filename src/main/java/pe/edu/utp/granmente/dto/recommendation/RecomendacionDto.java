package pe.edu.utp.granmente.dto.recommendation;

import java.util.List;

public record RecomendacionDto(String tag, String titulo, String nivel, String motivo, List<String> pasos) {
}
