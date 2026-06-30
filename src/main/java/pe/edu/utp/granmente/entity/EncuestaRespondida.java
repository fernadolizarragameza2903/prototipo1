package pe.edu.utp.granmente.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "surveys")
public class EncuestaRespondida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Usuario estudiante;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private InterestKey perfilResultado;

    private int xpGanado;

    @OneToMany(mappedBy = "encuesta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RespuestaEncuesta> respuestas = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Usuario getEstudiante() { return estudiante; }
    public void setEstudiante(Usuario estudiante) { this.estudiante = estudiante; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public InterestKey getPerfilResultado() { return perfilResultado; }
    public void setPerfilResultado(InterestKey perfilResultado) { this.perfilResultado = perfilResultado; }
    public int getXpGanado() { return xpGanado; }
    public void setXpGanado(int xpGanado) { this.xpGanado = xpGanado; }
    public List<RespuestaEncuesta> getRespuestas() { return respuestas; }
    public void setRespuestas(List<RespuestaEncuesta> respuestas) { this.respuestas = respuestas; }
    public void addRespuesta(RespuestaEncuesta respuesta) {
        respuestas.add(respuesta);
        respuesta.setEncuesta(this);
    }
}
