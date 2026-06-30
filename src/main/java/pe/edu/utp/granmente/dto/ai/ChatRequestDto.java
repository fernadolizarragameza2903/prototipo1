package pe.edu.utp.granmente.dto.ai;

import jakarta.validation.constraints.NotBlank;

public class ChatRequestDto {
    @NotBlank
    private String message;

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
