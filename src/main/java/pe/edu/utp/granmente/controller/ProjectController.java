package pe.edu.utp.granmente.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.utp.granmente.entity.Rol;
import pe.edu.utp.granmente.entity.Usuario;
import pe.edu.utp.granmente.service.CatalogService;
import pe.edu.utp.granmente.service.ProgressService;
import pe.edu.utp.granmente.service.UserService;

@Controller
public class ProjectController {

    private final CatalogService catalogService;
    private final ProgressService progressService;
    private final UserService userService;

    public ProjectController(CatalogService catalogService, ProgressService progressService, UserService userService) {
        this.catalogService = catalogService;
        this.progressService = progressService;
        this.userService = userService;
    }

    @GetMapping("/proyectos")
    public String projects(@RequestParam(defaultValue = "recomendados") String filtro, Model model, Principal principal) {
        Usuario currentUser = userService.getCurrentUser(principal.getName());
        boolean isTeacher = currentUser.getRol() == Rol.DOCENTE;
        String effectiveFilter = isTeacher && "recomendados".equals(filtro) ? "todos" : filtro;
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("isTeacher", isTeacher);
        if (!isTeacher) {
            model.addAttribute("progress", progressService.getProgress(principal.getName()));
        }
        model.addAttribute("filtro", effectiveFilter);
        model.addAttribute("proyectos", catalogService.proyectos(effectiveFilter, isTeacher ? null : principal.getName()));
        return "proyectos";
    }
}
