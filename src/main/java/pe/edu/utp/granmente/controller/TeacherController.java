package pe.edu.utp.granmente.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pe.edu.utp.granmente.service.TeacherDashboardService;
import pe.edu.utp.granmente.service.UserService;

@Controller
public class TeacherController {

    private final TeacherDashboardService teacherDashboardService;
    private final UserService userService;

    public TeacherController(TeacherDashboardService teacherDashboardService, UserService userService) {
        this.teacherDashboardService = teacherDashboardService;
        this.userService = userService;
    }

    @GetMapping("/docente")
    public String teacher(Model model, Principal principal) {
        model.addAttribute("currentUser", userService.getCurrentUser(principal.getName()));
        model.addAttribute("dashboard", teacherDashboardService.classroom());
        return "docente";
    }
}
