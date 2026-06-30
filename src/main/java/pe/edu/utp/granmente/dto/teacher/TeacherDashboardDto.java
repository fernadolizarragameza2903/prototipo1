package pe.edu.utp.granmente.dto.teacher;

import java.util.List;

public record TeacherDashboardDto(
        long estudiantes,
        double madurezPromedio,
        int xpAula,
        List<String> perfilesFrecuentes,
        List<String> alertas,
        List<StudentSummaryDto> resumenes
) {
}
