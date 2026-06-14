package pe.edu.utp.granmente.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class VideoRecurso {
    private String titulo;
    private String url;

    public VideoRecurso() {
    }

    public VideoRecurso(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
