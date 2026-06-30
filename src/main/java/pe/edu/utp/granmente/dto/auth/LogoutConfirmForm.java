package pe.edu.utp.granmente.dto.auth;

import jakarta.validation.constraints.NotBlank;

public class LogoutConfirmForm {
    @NotBlank
    private String nombre;
    @NotBlank
    private String grado;
    @NotBlank
    private String password;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getGrado() { return grado; }
    public void setGrado(String grado) { this.grado = grado; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
