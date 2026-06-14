package pe.edu.utp.granmente.service;

import pe.edu.utp.granmente.dto.ai.ChatResponseDto;

public interface AiService {
    ChatResponseDto reply(String email, String message);
}
