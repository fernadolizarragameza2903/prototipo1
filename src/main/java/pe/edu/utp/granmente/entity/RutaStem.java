package pe.edu.utp.granmente.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stem_routes")
public class RutaStem {

    @Id
    private String key;

    private String titulo;
    @Column(length = 1000)
    private String subtitulo;
    @Column(length = 1000)
    private String carrera;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "stem_route_themes", joinColumns = @JoinColumn(name = "route_key"))
    @Column(name = "theme")
    private List<String> temas = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "stem_route_projects", joinColumns = @JoinColumn(name = "route_key"))
    @Column(name = "project")
    private List<String> proyectos = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "stem_route_videos", joinColumns = @JoinColumn(name = "route_key"))
    private List<VideoRecurso> videos = new ArrayList<>();

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getSubtitulo() { return subtitulo; }
    public void setSubtitulo(String subtitulo) { this.subtitulo = subtitulo; }
    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }
    public List<String> getTemas() { return temas; }
    public void setTemas(List<String> temas) { this.temas = temas; }
    public List<String> getProyectos() { return proyectos; }
    public void setProyectos(List<String> proyectos) { this.proyectos = proyectos; }
    public List<VideoRecurso> getVideos() { return videos; }
    public void setVideos(List<VideoRecurso> videos) { this.videos = videos; }
}
