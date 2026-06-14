package pe.edu.utp.granmente.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pe.edu.utp.granmente.service.ProgressService;
import pe.edu.utp.granmente.service.UserService;

@Controller
public class StudentDashboardController {

    private final ProgressService progressService;
    private final UserService userService;

    public StudentDashboardController(ProgressService progressService, UserService userService) {
        this.progressService = progressService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("progress", progressService.getProgress(principal.getName()));
        model.addAttribute("currentUser", userService.getCurrentUser(principal.getName()));
        return "dashboard";
    }
}
