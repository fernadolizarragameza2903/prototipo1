package pe.edu.utp.granmente.controller;

import java.security.Principal;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.utp.granmente.dto.auth.RegisterForm;
import pe.edu.utp.granmente.dto.survey.SurveySubmitForm;
import pe.edu.utp.granmente.entity.Rol;
import pe.edu.utp.granmente.service.CatalogService;
import pe.edu.utp.granmente.service.ProgressService;
import pe.edu.utp.granmente.service.UserService;

@Controller
public class HomeController {

    private final CatalogService catalogService;
    private final ProgressService progressService;
    private final UserService userService;

    public HomeController(CatalogService catalogService, ProgressService progressService, UserService userService) {
        this.catalogService = catalogService;
        this.progressService = progressService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("registerForm", new RegisterForm());
        model.addAttribute("surveyForm", new SurveySubmitForm());
        model.addAttribute("preguntas", catalogService.preguntas());
        model.addAttribute("rutas", catalogService.rutas());
        if (principal != null) {
            model.addAttribute("progress", progressService.getProgress(principal.getName()));
            model.addAttribute("currentUser", userService.getCurrentUser(principal.getName()));
        }
        return "index";
    }

    @PostMapping("/registro")
    public String register(@Valid @ModelAttribute RegisterForm registerForm, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerError", "Completa nombre, correo, grado y una contrasena de al menos 5 caracteres.");
            return "redirect:/";
        }
        try {
            userService.register(registerForm, Rol.ESTUDIANTE);
            redirectAttributes.addFlashAttribute("registerOk", "Registro correcto. Ahora inicia sesion.");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("registerError", ex.getMessage());
        }
        return "redirect:/";
    }

    @ModelAttribute("isAuthenticated")
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal());
    }
}
