package pe.edu.utp.granmente.controller;

import java.security.Principal;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.utp.granmente.dto.survey.SurveySubmitForm;
import pe.edu.utp.granmente.service.SurveyService;

@Controller
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping("/encuesta")
    public String submit(@Valid @ModelAttribute("surveyForm") SurveySubmitForm form, BindingResult bindingResult,
                         Principal principal, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("surveyError", "Selecciona una respuesta por pregunta.");
            return "redirect:/#encuesta";
        }
        redirectAttributes.addFlashAttribute("surveyResult", surveyService.submit(principal.getName(), form.getOptionIds()));
        return "redirect:/#encuesta";
    }
}
