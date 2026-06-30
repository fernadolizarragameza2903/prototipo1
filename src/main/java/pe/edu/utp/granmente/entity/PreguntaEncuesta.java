package pe.edu.utp.granmente.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "survey_questions")
public class PreguntaEncuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer orden;

    @Column(nullable = false)
    private String texto;

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("orden ASC")
    private List<OpcionRespuesta> opciones = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    public List<OpcionRespuesta> getOpciones() { return opciones; }
    public void setOpciones(List<OpcionRespuesta> opciones) { this.opciones = opciones; }
    public void addOpcion(OpcionRespuesta opcion) {
        opciones.add(opcion);
        opcion.setPregunta(this);
    }
}
