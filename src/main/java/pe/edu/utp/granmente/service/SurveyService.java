package pe.edu.utp.granmente.service;

import java.util.List;
import pe.edu.utp.granmente.dto.survey.SurveyResultDto;

public interface SurveyService {
    SurveyResultDto submit(String email, List<Long> optionIds);
}
