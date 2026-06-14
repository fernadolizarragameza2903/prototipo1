package pe.edu.utp.granmente.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stem_profiles")
public class PerfilStem {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "profile_key")
    private InterestKey key;

    private String icono;
    @Column(nullable = false)
    private String nombre;
    @Column(length = 1000)
    private String descripcion;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "stem_profile_careers", joinColumns = @JoinColumn(name = "profile_key"))
    @Column(name = "career")
    private List<String> carreras = new ArrayList<>();

    private String videojuego;
    private String categoriaVideojuego;
    @Column(length = 1000)
    private String descripcionVideojuego;
    private String video;
    @Column(length = 1000)
    private String descripcionVideo;
    @Column(length = 1000)
    private String videoUrl;
    @Column(length = 1000)
    private String mensaje;

    public InterestKey getKey() { return key; }
    public void setKey(InterestKey key) { this.key = key; }
    public String getIcono() { return icono; }
    public void setIcono(String icono) { this.icono = icono; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public List<String> getCarreras() { return carreras; }
    public void setCarreras(List<String> carreras) { this.carreras = carreras; }
    public String getVideojuego() { return videojuego; }
    public void setVideojuego(String videojuego) { this.videojuego = videojuego; }
    public String getCategoriaVideojuego() { return categoriaVideojuego; }
    public void setCategoriaVideojuego(String categoriaVideojuego) { this.categoriaVideojuego = categoriaVideojuego; }
    public String getDescripcionVideojuego() { return descripcionVideojuego; }
    public void setDescripcionVideojuego(String descripcionVideojuego) { this.descripcionVideojuego = descripcionVideojuego; }
    public String getVideo() { return video; }
    public void setVideo(String video) { this.video = video; }
    public String getDescripcionVideo() { return descripcionVideo; }
    public void setDescripcionVideo(String descripcionVideo) { this.descripcionVideo = descripcionVideo; }
    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}
