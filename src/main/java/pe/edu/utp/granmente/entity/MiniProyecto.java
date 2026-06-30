package pe.edu.utp.granmente.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mini_projects")
public class MiniProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    @Enumerated(EnumType.STRING)
    private InterestKey area;
    private String nivel;
    private String duracion;
    @Column(length = 1000)
    private String descripcion;
    @Column(length = 1000)
    private String entregable;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public InterestKey getArea() { return area; }
    public void setArea(InterestKey area) { this.area = area; }
    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public String getDuracion() { return duracion; }
    public void setDuracion(String duracion) { this.duracion = duracion; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getEntregable() { return entregable; }
    public void setEntregable(String entregable) { this.entregable = entregable; }
}
