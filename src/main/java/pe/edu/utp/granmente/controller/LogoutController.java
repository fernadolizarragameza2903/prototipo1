package pe.edu.utp.granmente.controller;

import java.security.Principal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.utp.granmente.dto.auth.LogoutConfirmForm;
import pe.edu.utp.granmente.service.UserService;

@Controller
public class LogoutController {

    private final UserService userService;

    public LogoutController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/logout-confirm")
    public String confirm(@Valid @ModelAttribute LogoutConfirmForm form, BindingResult bindingResult, Principal principal,
                          HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userService.confirmIdentity(principal.getName(), form.getNombre(), form.getGrado(), form.getPassword())) {
            redirectAttributes.addFlashAttribute("logoutError", "Datos incorrectos. Verifica nombre, grado y contrasena.");
            return "redirect:/dashboard";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return "redirect:/";
    }
}
