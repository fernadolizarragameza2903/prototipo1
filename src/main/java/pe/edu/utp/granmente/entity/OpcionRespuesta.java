package pe.edu.utp.granmente.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "answer_options")
public class OpcionRespuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id")
    private PreguntaEncuesta pregunta;

    @Column(nullable = false)
    private Integer orden;

    @Column(nullable = false)
    private String etiqueta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InterestKey interestKey;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public PreguntaEncuesta getPregunta() { return pregunta; }
    public void setPregunta(PreguntaEncuesta pregunta) { this.pregunta = pregunta; }
    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }
    public String getEtiqueta() { return etiqueta; }
    public void setEtiqueta(String etiqueta) { this.etiqueta = etiqueta; }
    public InterestKey getInterestKey() { return interestKey; }
    public void setInterestKey(InterestKey interestKey) { this.interestKey = interestKey; }
}
