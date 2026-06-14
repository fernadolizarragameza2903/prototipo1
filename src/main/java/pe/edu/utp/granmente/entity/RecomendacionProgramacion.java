package pe.edu.utp.granmente.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "programming_recommendations")
public class RecomendacionProgramacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tag;
    private String titulo;
    private String nivel;
    @Column(length = 1000)
    private String motivo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "programming_recommendation_steps", joinColumns = @JoinColumn(name = "recommendation_id"))
    @Column(name = "step")
    private List<String> pasos = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public List<String> getPasos() { return pasos; }
    public void setPasos(List<String> pasos) { this.pasos = pasos; }
}
