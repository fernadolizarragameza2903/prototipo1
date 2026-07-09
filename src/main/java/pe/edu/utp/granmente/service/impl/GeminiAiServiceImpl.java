package pe.edu.utp.granmente.service.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import pe.edu.utp.granmente.dto.ai.ChatResponseDto;
import pe.edu.utp.granmente.dto.student.StudentProgressDto;
import pe.edu.utp.granmente.repository.UsuarioRepository;
import pe.edu.utp.granmente.service.AiService;
import pe.edu.utp.granmente.service.ProgressService;

@Service
@Primary
@Transactional(readOnly = true)
public class GeminiAiServiceImpl implements AiService {

    private final ProgressService progressService;
    private final UsuarioRepository usuarioRepository;
    private final AiService fallback;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${app.ai.gemini.api-key:}")
    private String apiKey;

    @Value("${app.ai.gemini.model:gemini-2.5-flash}")
    private String model;

    private static final Logger logger = LoggerFactory.getLogger(GeminiAiServiceImpl.class);

    public GeminiAiServiceImpl(ProgressService progressService,
                               UsuarioRepository usuarioRepository,
                               @Qualifier("ruleBasedAiServiceImpl") AiService fallback) {
        this.progressService = progressService;
        this.usuarioRepository = usuarioRepository;
        this.fallback = fallback;
    }

    @Override
    public ChatResponseDto reply(String email, String message) {
        if (apiKey == null || apiKey.isBlank()) {
            logger.info("GEMINI: api-key is empty, delegating to fallback");
            return fallback.reply(email, message);
        }

        String systemInstruction = buildSystemInstruction(email);

        Map<String, Object> body = new HashMap<>();
        Map<String, Object> sys = new HashMap<>();
        sys.put("type", "instruct");
        sys.put("text", systemInstruction);
        body.put("system_instruction", sys);
        body.put("contents", new Object[] { Map.of("type", "text", "text", message) });

        try {
            String url = "https://generativelanguage.googleapis.com/v1beta/models/" + model + ":generateContent?key=" + apiKey;
            logger.info("GEMINI: calling model={} url={}", model, url);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
            Map<String, Object> response = restTemplate.postForObject(new URI(url), request, Map.class);
            logger.info("GEMINI: raw response received");
            if (response == null || response.isEmpty()) {
                logger.info("GEMINI: empty response, delegating to fallback");
                return fallback.reply(email, message);
            }
            var candidates = (List<Map<String, Object>>) response.get("candidates");
            String text = null;
            if (candidates != null && !candidates.isEmpty()) {
                var candidate = candidates.get(0);
                var content = (Map<String, Object>) candidate.get("content");
                if (content != null) {
                    var parts = (List<Map<String, Object>>) content.get("parts");
                    if (parts != null && !parts.isEmpty()) {
                        text = (String) parts.get(0).get("text");
                    }
                }
            }
            if (text == null || text.isBlank()) {
                logger.info("GEMINI: parsed text empty, delegating to fallback");
                return fallback.reply(email, message);
            }
            logger.info("GEMINI: got reply ({} chars) from gemini", text.length());
            return new ChatResponseDto(text, "gemini-" + model);
        } catch (HttpClientErrorException httpEx) {
            logger.error("GEMINI: HTTP error {} - body={}", httpEx.getStatusCode().value(), httpEx.getResponseBodyAsString());
            return fallback.reply(email, message);
        } catch (Exception e) {
            logger.error("GEMINI: unexpected error", e);
            return fallback.reply(email, message);
        }
    }

    private String buildSystemInstruction(String email) {
        try {
            StudentProgressDto progress = progressService.getProgress(email);
            String perfil = progress.perfil().nombre();
            String madurez = progress.madurez().nombre();
            int encuestas = progress.encuestas();
            return "Eres GRAN MENTE IA, el asistente virtual de orientacion vocacional STEM para estudiantes de colegio en Peru. Responde en español, de forma breve (3-4 lineas), cercana y sin tecnicismos innecesarios. No inventes datos ni calificaciones. Datos del usuario: nombre: %s; perfil STEM: %s; nivel de madurez: %s; encuestas completadas: %d. Responde según estos datos."
                    .formatted(progress.nombre(), perfil, madurez, encuestas);
        } catch (Exception ex) {
            try {
                String nombre = usuarioRepository.findByEmailIgnoreCase(email).map(u -> u.getNombre()).orElse("Usuario");
                return "Eres GRAN MENTE IA, el asistente virtual de orientacion vocacional STEM para colegio en Peru. Responde en español, breve (3-4 lineas) y no inventes datos. Usuario: " + nombre;
            } catch (Exception e) {
                return "Eres GRAN MENTE IA, el asistente virtual de orientacion vocacional STEM para colegio en Peru. Responde en español, breve (3-4 lineas) y no inventes datos.";
            }
        }
    }
}
