package pe.edu.utp.granmente.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student_progress")
public class ProgresoAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "student_id", unique = true)
    private Usuario estudiante;

    private int xp;
    private int encuestasResueltas;

    @Enumerated(EnumType.STRING)
    private InterestKey perfilDominante;

    @OneToMany(mappedBy = "progreso", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PuntajeInteres> puntajes = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Usuario getEstudiante() { return estudiante; }
    public void setEstudiante(Usuario estudiante) { this.estudiante = estudiante; }
    public int getXp() { return xp; }
    public void setXp(int xp) { this.xp = xp; }
    public int getEncuestasResueltas() { return encuestasResueltas; }
    public void setEncuestasResueltas(int encuestasResueltas) { this.encuestasResueltas = encuestasResueltas; }
    public InterestKey getPerfilDominante() { return perfilDominante; }
    public void setPerfilDominante(InterestKey perfilDominante) { this.perfilDominante = perfilDominante; }
    public List<PuntajeInteres> getPuntajes() { return puntajes; }
    public void setPuntajes(List<PuntajeInteres> puntajes) { this.puntajes = puntajes; }
}
