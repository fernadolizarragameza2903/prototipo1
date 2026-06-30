package pe.edu.utp.granmente.service.impl;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.granmente.dto.survey.SurveyResultDto;
import pe.edu.utp.granmente.entity.*;
import pe.edu.utp.granmente.exception.ResourceNotFoundException;
import pe.edu.utp.granmente.mapper.CatalogMapper;
import pe.edu.utp.granmente.repository.*;
import pe.edu.utp.granmente.service.ProgressService;
import pe.edu.utp.granmente.service.SurveyService;

@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

    private static final int XP_PER_SURVEY = 180;

    private final UsuarioRepository usuarioRepository;
    private final OpcionRespuestaRepository opcionRepository;
    private final PerfilStemRepository perfilRepository;
    private final EncuestaRespondidaRepository encuestaRepository;
    private final ProgresoAlumnoRepository progressRepository;
    private final ProgressService progressService;

    public SurveyServiceImpl(UsuarioRepository usuarioRepository, OpcionRespuestaRepository opcionRepository,
                             PerfilStemRepository perfilRepository, EncuestaRespondidaRepository encuestaRepository,
                             ProgresoAlumnoRepository progressRepository, ProgressService progressService) {
        this.usuarioRepository = usuarioRepository;
        this.opcionRepository = opcionRepository;
        this.perfilRepository = perfilRepository;
        this.encuestaRepository = encuestaRepository;
        this.progressRepository = progressRepository;
        this.progressService = progressService;
    }

    @Override
    public SurveyResultDto submit(String email, List<Long> optionIds) {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado."));
        List<OpcionRespuesta> opciones = opcionRepository.findAllById(optionIds);
        if (opciones.isEmpty()) {
            throw new IllegalArgumentException("Selecciona al menos una opcion.");
        }
        Map<InterestKey, Integer> scores = new EnumMap<>(InterestKey.class);
        opciones.forEach(opcion -> scores.merge(opcion.getInterestKey(), 1, Integer::sum));
        InterestKey result = scores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(InterestKey.PROGRAMACION);
        if (result == InterestKey.VISUAL) {
            result = InterestKey.PROGRAMACION;
        }

        EncuestaRespondida encuesta = new EncuestaRespondida();
        encuesta.setEstudiante(usuario);
        encuesta.setFecha(LocalDateTime.now());
        encuesta.setPerfilResultado(result);
        encuesta.setXpGanado(XP_PER_SURVEY);
        for (OpcionRespuesta opcion : opciones) {
            RespuestaEncuesta respuesta = new RespuestaEncuesta();
            respuesta.setPreguntaTexto(opcion.getPregunta().getTexto());
            respuesta.setRespuestaTexto(opcion.getEtiqueta());
            respuesta.setInterestKey(opcion.getInterestKey());
            encuesta.addRespuesta(respuesta);
        }
        encuestaRepository.save(encuesta);

        ProgresoAlumno progress = progressService.ensureProgress(usuario);
        progress.setEncuestasResueltas(progress.getEncuestasResueltas() + 1);
        progress.setXp(progress.getXp() + XP_PER_SURVEY);
        progress.setPerfilDominante(result);
        scores.forEach((key, value) -> addScore(progress, key, value));
        progressRepository.save(progress);

        PerfilStem perfil = perfilRepository.findById(result)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil STEM no encontrado."));
        return new SurveyResultDto(CatalogMapper.toDto(perfil),
                opciones.stream().map(OpcionRespuesta::getEtiqueta).toList(), progress.getXp());
    }

    private void addScore(ProgresoAlumno progress, InterestKey key, int value) {
        PuntajeInteres score = progress.getPuntajes().stream()
                .filter(item -> item.getInterestKey() == key)
                .findFirst()
                .orElseGet(() -> {
                    PuntajeInteres created = new PuntajeInteres();
                    created.setProgreso(progress);
                    created.setInterestKey(key);
                    progress.getPuntajes().add(created);
                    return created;
                });
        score.setPuntaje(score.getPuntaje() + value);
    }
}
