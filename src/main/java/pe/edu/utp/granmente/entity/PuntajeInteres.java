package pe.edu.utp.granmente.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "interest_scores")
public class PuntajeInteres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "progress_id")
    private ProgresoAlumno progreso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InterestKey interestKey;

    private int puntaje;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ProgresoAlumno getProgreso() { return progreso; }
    public void setProgreso(ProgresoAlumno progreso) { this.progreso = progreso; }
    public InterestKey getInterestKey() { return interestKey; }
    public void setInterestKey(InterestKey interestKey) { this.interestKey = interestKey; }
    public int getPuntaje() { return puntaje; }
    public void setPuntaje(int puntaje) { this.puntaje = puntaje; }
}
