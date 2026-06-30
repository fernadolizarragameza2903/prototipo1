package pe.edu.utp.granmente.dto.survey;

import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class SurveySubmitForm {
    @NotEmpty
    private List<Long> optionIds = new ArrayList<>();

    public List<Long> getOptionIds() { return optionIds; }
    public void setOptionIds(List<Long> optionIds) { this.optionIds = optionIds; }
}
