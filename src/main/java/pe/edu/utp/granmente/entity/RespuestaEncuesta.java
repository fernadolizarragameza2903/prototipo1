package pe.edu.utp.granmente.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
public class RespuestaEncuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "survey_id")
    private EncuestaRespondida encuesta;

    private String preguntaTexto;
    private String respuestaTexto;

    @Enumerated(EnumType.STRING)
    private InterestKey interestKey;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public EncuestaRespondida getEncuesta() { return encuesta; }
    public void setEncuesta(EncuestaRespondida encuesta) { this.encuesta = encuesta; }
    public String getPreguntaTexto() { return preguntaTexto; }
    public void setPreguntaTexto(String preguntaTexto) { this.preguntaTexto = preguntaTexto; }
    public String getRespuestaTexto() { return respuestaTexto; }
    public void setRespuestaTexto(String respuestaTexto) { this.respuestaTexto = respuestaTexto; }
    public InterestKey getInterestKey() { return interestKey; }
    public void setInterestKey(InterestKey interestKey) { this.interestKey = interestKey; }
}
