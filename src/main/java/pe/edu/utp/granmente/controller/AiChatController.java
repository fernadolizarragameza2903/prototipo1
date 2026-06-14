package pe.edu.utp.granmente.controller;

import java.security.Principal;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.granmente.dto.ai.ChatRequestDto;
import pe.edu.utp.granmente.dto.ai.ChatResponseDto;
import pe.edu.utp.granmente.service.AiService;
import pe.edu.utp.granmente.service.ProgressService;
import pe.edu.utp.granmente.service.UserService;

@Controller
public class AiChatController {

    private final AiService aiService;
    private final ProgressService progressService;
    private final UserService userService;

    public AiChatController(AiService aiService, ProgressService progressService, UserService userService) {
        this.aiService = aiService;
        this.progressService = progressService;
        this.userService = userService;
    }

    @GetMapping("/ia")
    public String chat(Model model, Principal principal) {
        model.addAttribute("currentUser", userService.getCurrentUser(principal.getName()));
        model.addAttribute("progress", progressService.getProgress(principal.getName()));
        return "ia";
    }

    @PostMapping("/api/ai/chat")
    @ResponseBody
    public ResponseEntity<ChatResponseDto> reply(@Valid @RequestBody ChatRequestDto request, Principal principal) {
        return ResponseEntity.ok(aiService.reply(principal.getName(), request.getMessage()));
    }
}
